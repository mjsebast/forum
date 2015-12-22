package com.linguo.comment.service;

import com.linguo.comment_translate.model.CommentTranslation;
import com.linguo.comment_translate.repository.CommentTranslationRepository;
import com.linguo.comment.dto.CommentDTO;
import com.linguo.comment.dto.CommentFilterDTO;
import com.linguo.thread.model.Thread;
import com.linguo.comment.model.Comment;
import com.linguo.comment.model.CommentContent;
import com.linguo.comment.repository.CommentRepository;
import com.linguo.common.dto.TranslationDTO;
import com.linguo.common.util.TranslationService;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TranslationService translationService;

    @Autowired
    CommentTranslationRepository commentTranslationRepository;

    public CommentDTO findById(Long id){
        return new CommentDTO(commentRepository.findOne(id));
    }

    public Page<CommentDTO> findByPage(CommentFilterDTO filter, Pageable pageable){
        Page<Comment> threads = filter.getThreadId()!=null ?
                commentRepository.findByThreadIdAndParentIdIsNull(filter.getThreadId(), pageable):
                commentRepository.findAll(pageable);
        List<CommentDTO> dtos = threads.getContent().stream()
                .map(CommentDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, threads.getTotalElements());
    }

    public CommentDTO create(CommentDTO dto){
        Comment comment = new Comment();
        comment.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        comment.setUser(user);
        if(dto.getParentId()!=null){
            Comment parent = new Comment();
            parent.setId(dto.getParentId());
            comment.setParent(parent);
        }

        Thread thread = new Thread();
        thread.setId(dto.getThreadId());
        comment.setThread(thread);
        Set<CommentContent> contents = new HashSet<>();
        CommentContent content = dto.getContent().get(dto.getLanguage());
        content.setComment(comment);
        content.setLanguageId(dto.getLanguage());
        contents.add(content);

        TranslationDTO translationDTO = new TranslationDTO(dto.getLanguage(),
                dto.getLanguage().equals("en")?"es":"en");
        translationDTO.addText(content.getMessage());
        String[] translations = translationService.translateContent(translationDTO);

        CommentContent secondary = new CommentContent();
        secondary.setLanguageId(translationDTO.getTo());
        secondary.setMessage(translations[0]);
        secondary.setComment(comment);
        contents.add(secondary);

        comment.setContent(contents);
        comment = commentRepository.save(comment);

        CommentTranslation autoTranslation = new CommentTranslation();
        autoTranslation.setComment(comment);
        autoTranslation.setMessage(secondary.getMessage());
        autoTranslation.setLanguageId(secondary.getLanguageId());
        UserAccount autoUser = new UserAccount();
        autoUser.setId(2L);
        autoTranslation.setUser(autoUser);
        autoTranslation.setPoints(0);
        commentTranslationRepository.save(autoTranslation);

        return new CommentDTO(comment);
    }
}
