package com.linguo.commenttranslate.service;

import com.linguo.commenttranslate.model.CommentTranslationVote;
import com.linguo.commenttranslate.repository.CommentTranslationVoteRepository;
import com.linguo.thread.model.ThreadComment;
import com.linguo.commenttranslate.dto.ThreadCommentTranslationDTO;
import com.linguo.commenttranslate.dto.ThreadCommentTranslationFilterDTO;
import com.linguo.commenttranslate.model.ThreadCommentTranslation;
import com.linguo.commenttranslate.repository.ThreadCommentTranslationRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThreadCommentTranslationServiceImpl {

    @Autowired
    ThreadCommentTranslationRepository threadCommentTranslationRepository;
    @Autowired
    CommentTranslationVoteRepository commentTranslationVoteRepository;

    public ThreadCommentTranslationDTO findById(Long id){
        return getDTOFromEntity(threadCommentTranslationRepository.findOne(id));
    }

    public Page<ThreadCommentTranslationDTO> findByPage(ThreadCommentTranslationFilterDTO filter, Pageable pageable){
        Page<ThreadCommentTranslation> translations = filter.getCommentId()!=null ?
                threadCommentTranslationRepository.findByThreadCommentId(filter.getCommentId(), pageable):
                threadCommentTranslationRepository.findAll(pageable);
        List<ThreadCommentTranslationDTO> dtos = translations.getContent().stream()
                .map(entity -> getDTOFromEntity(entity)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, translations.getTotalElements());
    }

    private ThreadCommentTranslationDTO getDTOFromEntity(ThreadCommentTranslation entity){
        ThreadCommentTranslationDTO dto = new ThreadCommentTranslationDTO(entity);
        CommentTranslationVote vote = commentTranslationVoteRepository.findByUserIdAndThreadCommentTranslationId(1L, entity.getId());
        if(vote!=null){
            dto.setUserVote(vote.getVote());
        }
        return dto;
    }

    public ThreadCommentTranslationDTO create(ThreadCommentTranslationDTO dto){
        ThreadCommentTranslation translation = new ThreadCommentTranslation();
        translation.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        translation.setUser(user);
        translation.setMessage(dto.getMessage());

        ThreadComment comment = new ThreadComment();
        comment.setId(dto.getCommentId());
        translation.setThreadComment(comment);
        translation.setPoints(0);
        return new ThreadCommentTranslationDTO(threadCommentTranslationRepository.save(translation));
    }
}
