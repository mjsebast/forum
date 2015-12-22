package com.linguo.comment_translate.service;

import com.linguo.comment_translate.model.CommentTranslationVote;
import com.linguo.comment_translate.repository.CommentTranslationVoteRepository;
import com.linguo.comment.model.Comment;
import com.linguo.comment_translate.dto.CommentTranslationDTO;
import com.linguo.comment_translate.dto.CommentTranslationFilterDTO;
import com.linguo.comment_translate.model.CommentTranslation;
import com.linguo.comment_translate.repository.CommentTranslationRepository;
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
public class CommentTranslationServiceImpl {

    @Autowired
    CommentTranslationRepository commentTranslationRepository;
    @Autowired
    CommentTranslationVoteRepository commentTranslationVoteRepository;

    public CommentTranslationDTO findById(Long id){
        return getDTOFromEntity(commentTranslationRepository.findOne(id));
    }

    public Page<CommentTranslationDTO> findByPage(CommentTranslationFilterDTO filter, Pageable pageable){
        Page<CommentTranslation> translations = filter.getCommentId()!=null ?
                commentTranslationRepository.findByCommentId(filter.getCommentId(), pageable):
                commentTranslationRepository.findAll(pageable);
        List<CommentTranslationDTO> dtos = translations.getContent().stream()
                .map(entity -> getDTOFromEntity(entity)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, translations.getTotalElements());
    }

    private CommentTranslationDTO getDTOFromEntity(CommentTranslation entity){
        CommentTranslationDTO dto = new CommentTranslationDTO(entity);
        CommentTranslationVote vote = commentTranslationVoteRepository.findByUserIdAndCommentTranslationId(1L, entity.getId());
        if(vote!=null){
            dto.setUserVote(vote.getVote());
        }
        return dto;
    }

    public CommentTranslationDTO create(CommentTranslationDTO dto){
        CommentTranslation translation = new CommentTranslation();
        translation.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        translation.setUser(user);
        translation.setMessage(dto.getMessage());

        Comment comment = new Comment();
        comment.setId(dto.getCommentId());
        translation.setComment(comment);
        translation.setPoints(0);
        return new CommentTranslationDTO(commentTranslationRepository.save(translation));
    }
}
