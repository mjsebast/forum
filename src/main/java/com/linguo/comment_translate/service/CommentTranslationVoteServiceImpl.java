package com.linguo.comment_translate.service;

import com.linguo.comment_translate.dto.CommentTranslationVoteDTO;
import com.linguo.comment_translate.model.CommentTranslationVote;
import com.linguo.comment_translate.model.CommentTranslation;
import com.linguo.comment_translate.repository.CommentTranslationVoteRepository;
import com.linguo.comment_translate.repository.CommentTranslationRepository;
import com.linguo.comment.model.Comment;
import com.linguo.comment.model.CommentContent;
import com.linguo.comment.repository.CommentContentRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentTranslationVoteServiceImpl {
    @Autowired CommentTranslationVoteRepository commentTranslationVoteRepository;
    @Autowired
    CommentTranslationRepository commentTranslationRepository;
    @Autowired
    CommentContentRepository commentContentRepository;

    public CommentTranslationVoteDTO create(Long translationId, CommentTranslationVoteDTO dto){
        CommentTranslationVote vote = commentTranslationVoteRepository.findByUserIdAndCommentTranslationId(1L, translationId);
        if(vote==null){
            vote = new CommentTranslationVote();
            CommentTranslation translation = new CommentTranslation();
            translation.setId(translationId);
            vote.setCommentTranslation(translation);
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
        }

        CommentTranslation translation = commentTranslationRepository.findOne(translationId);
        if(vote.getVote()==null || vote.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (dto.getVote() * 2));
        }
        commentTranslationRepository.save(translation);

        Comment comment = translation.getComment();
        CommentTranslation mostVotedTranslation =
                commentTranslationRepository.findFirstByCommentIdAndLanguageIdOrderByPointsDesc(comment.getId(), translation.getLanguageId());

        CommentContent currentContent = commentContentRepository.findFirstByLanguageIdAndCommentId(translation.getLanguageId(), comment.getId());
        if(currentContent==null){
            currentContent = new CommentContent();
        }
        currentContent.setLanguageId(translation.getLanguageId());
        currentContent.setComment(comment);
        currentContent.setMessage(mostVotedTranslation.getMessage());

        commentContentRepository.save(currentContent);

        vote.setVote(dto.getVote());
        commentTranslationVoteRepository.save(vote);
        return dto;
    }
}
