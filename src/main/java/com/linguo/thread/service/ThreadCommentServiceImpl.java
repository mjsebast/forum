package com.linguo.thread.service;

import com.linguo.thread.dto.ThreadCommentDTO;
import com.linguo.thread.dto.ThreadCommentFilterDTO;
import com.linguo.thread.model.Thread;
import com.linguo.thread.model.ThreadComment;
import com.linguo.thread.model.ThreadCommentContent;
import com.linguo.thread.repository.ThreadCommentRepository;
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
public class ThreadCommentServiceImpl {
    @Autowired
    ThreadCommentRepository threadCommentRepository;

    @Autowired
    TranslationService translationService;

    public ThreadCommentDTO findById(Long id){
        return new ThreadCommentDTO(threadCommentRepository.findOne(id));
    }

    public Page<ThreadCommentDTO> findByPage(ThreadCommentFilterDTO filter, Pageable pageable){
        Page<ThreadComment> threads = filter.getThreadId()!=null ?
                threadCommentRepository.findByThreadIdAndParentIdIsNull(filter.getThreadId(), pageable):
                threadCommentRepository.findAll(pageable);
        List<ThreadCommentDTO> dtos = threads.getContent().stream()
                .map(ThreadCommentDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, threads.getTotalElements());
    }

    public ThreadCommentDTO create(ThreadCommentDTO dto){
        ThreadComment comment = new ThreadComment();
        comment.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        comment.setUser(user);
        if(dto.getParentId()!=null){
            ThreadComment parent = new ThreadComment();
            parent.setId(dto.getParentId());
            comment.setParent(parent);
        }

        Thread thread = new Thread();
        thread.setId(dto.getThreadId());
        comment.setThread(thread);
        Set<ThreadCommentContent> contents = new HashSet<>();
        ThreadCommentContent content = dto.getContent().get(dto.getLanguage());
        content.setThreadComment(comment);
        content.setLanguageId(dto.getLanguage());
        contents.add(content);

        TranslationDTO translationDTO = new TranslationDTO(dto.getLanguage(),
                dto.getLanguage().equals("en")?"es":"en");
        translationDTO.addText(content.getMessage());
        String[] translations = translationService.translateContent(translationDTO);

        ThreadCommentContent secondary = new ThreadCommentContent();
        secondary.setLanguageId(translationDTO.getTo());
        secondary.setMessage(translations[0]);
        secondary.setThreadComment(comment);
        contents.add(secondary);

        comment.setContent(contents);
        return new ThreadCommentDTO(threadCommentRepository.save(comment));
    }
}
