package com.linguo.commenttranslate.service;

import com.linguo.commenttranslate.dto.CommentTranslationVoteDTO;
import com.linguo.commenttranslate.model.CommentTranslationVote;
import com.linguo.commenttranslate.model.ThreadCommentTranslation;
import com.linguo.commenttranslate.repository.CommentTranslationVoteRepository;
import com.linguo.commenttranslate.repository.ThreadCommentTranslationRepository;
import com.linguo.thread.model.ThreadComment;
import com.linguo.thread.model.ThreadCommentContent;
import com.linguo.thread.repository.ThreadCommentContentRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentTranslationVoteServiceImpl {
    @Autowired CommentTranslationVoteRepository commentTranslationVoteRepository;
    @Autowired ThreadCommentTranslationRepository threadCommentTranslationRepository;
    @Autowired ThreadCommentContentRepository threadCommentContentRepository;

    public CommentTranslationVoteDTO create(Long translationId, CommentTranslationVoteDTO dto){
        CommentTranslationVote vote = commentTranslationVoteRepository.findByUserIdAndThreadCommentTranslationId(1L, translationId);
        if(vote==null){
            vote = new CommentTranslationVote();
            ThreadCommentTranslation translation = new ThreadCommentTranslation();
            translation.setId(translationId);
            vote.setThreadCommentTranslation(translation);
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
        }

        ThreadCommentTranslation translation = threadCommentTranslationRepository.findOne(translationId);
        if(vote.getVote()==null || vote.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (dto.getVote() * 2));
        }
        threadCommentTranslationRepository.save(translation);

        ThreadComment comment = translation.getThreadComment();
        ThreadCommentTranslation mostVotedTranslation =
                threadCommentTranslationRepository.findFirstByThreadCommentIdAndLanguageIdOrderByPointsDesc(comment.getId(), translation.getLanguageId());

        ThreadCommentContent currentContent = threadCommentContentRepository.findFirstByLanguageIdAndThreadCommentId(translation.getLanguageId(), comment.getId());
        if(currentContent==null){
            currentContent = new ThreadCommentContent();
        }
        currentContent.setLanguageId(translation.getLanguageId());
        currentContent.setThreadComment(comment);
        currentContent.setMessage(mostVotedTranslation.getMessage());

        threadCommentContentRepository.save(currentContent);

        vote.setVote(dto.getVote());
        commentTranslationVoteRepository.save(vote);
        return dto;
    }
}
