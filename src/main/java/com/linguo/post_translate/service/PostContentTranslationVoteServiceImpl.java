package com.linguo.post_translate.service;

import com.linguo.common.dto.VoteDTO;
import com.linguo.post.model.Post;
import com.linguo.post.model.PostContent;
import com.linguo.post.repository.PostContentRepository;
import com.linguo.post_translate.model.PostContentTranslation;
import com.linguo.post_translate.model.PostContentTranslationVote;
import com.linguo.post_translate.repository.PostContentTranslationRepository;
import com.linguo.post_translate.repository.PostContentTranslationVoteRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostContentTranslationVoteServiceImpl {

    @Autowired
    PostContentTranslationVoteRepository postContentTranslationVoteRepository;
    @Autowired
    PostContentTranslationRepository postContentTranslationRepository;
    @Autowired
    PostContentRepository postContentRepository;

    public VoteDTO create(Long translationId, VoteDTO dto){
        PostContentTranslationVote vote = postContentTranslationVoteRepository.findByUserIdAndPostContentTranslationId(1L, translationId);
        if(vote==null){
            vote = new PostContentTranslationVote();
            PostContentTranslation translation = new PostContentTranslation();
            translation.setId(translationId);
            vote.setPostContentTranslation(translation);
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
        }

        PostContentTranslation translation = postContentTranslationRepository.findOne(translationId);
        if(vote.getVote()==null || vote.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (dto.getVote() * 2));
        }
        postContentTranslationRepository.save(translation);

        Post post = translation.getPost();
        PostContentTranslation mostVotedTranslation =
                postContentTranslationRepository.findFirstByPostIdAndLanguageIdOrderByPointsDesc(post.getId(), translation.getLanguageId());

        PostContent currentContent = postContentRepository.findFirstByLanguageIdAndPostId(translation.getLanguageId(), post.getId());
        if(currentContent==null){
            currentContent = new PostContent();
        }
        currentContent.setLanguageId(translation.getLanguageId());
        currentContent.setPost(post);
        currentContent.setMessage(mostVotedTranslation.getMessage());

        postContentRepository.save(currentContent);

        vote.setVote(dto.getVote());
        postContentTranslationVoteRepository.save(vote);
        return dto;
    }
}
