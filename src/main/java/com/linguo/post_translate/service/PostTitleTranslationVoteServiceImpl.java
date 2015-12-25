package com.linguo.post_translate.service;

import com.linguo.common.dto.VoteDTO;
import com.linguo.post.model.Post;
import com.linguo.post.model.PostContent;
import com.linguo.post.repository.PostContentRepository;
import com.linguo.post_translate.model.PostTitleTranslation;
import com.linguo.post_translate.model.PostTitleTranslationVote;
import com.linguo.post_translate.repository.PostTitleTranslationRepository;
import com.linguo.post_translate.repository.PostTitleTranslationVoteRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostTitleTranslationVoteServiceImpl {
    @Autowired
    PostTitleTranslationVoteRepository postTitleTranslationVoteRepository;
    @Autowired
    PostTitleTranslationRepository postTitleTranslationRepository;
    @Autowired
    PostContentRepository postContentRepository;

    public VoteDTO create(Long translationId, VoteDTO dto){
        PostTitleTranslationVote vote = postTitleTranslationVoteRepository.findByUserIdAndPostTitleTranslationId(1L, translationId);
        if(vote==null){
            vote = new PostTitleTranslationVote();
            PostTitleTranslation translation = new PostTitleTranslation();
            translation.setId(translationId);
            vote.setPostTitleTranslation(translation);
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
        }

        PostTitleTranslation translation = postTitleTranslationRepository.findOne(translationId);
        if(vote.getVote()==null || vote.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            translation.setPoints(translation.getPoints() + (dto.getVote() * 2));
        }
        postTitleTranslationRepository.save(translation);

        Post post = translation.getPost();
        PostTitleTranslation mostVotedTranslation =
                postTitleTranslationRepository.findFirstByPostIdAndLanguageIdOrderByPointsDesc(post.getId(), translation.getLanguageId());

        PostContent currentContent = postContentRepository.findFirstByLanguageIdAndPostId(translation.getLanguageId(), post.getId());
        if(currentContent==null){
            currentContent = new PostContent();
        }
        currentContent.setLanguageId(translation.getLanguageId());
        currentContent.setPost(post);
        currentContent.setTitle(mostVotedTranslation.getMessage());

        postContentRepository.save(currentContent);

        vote.setVote(dto.getVote());
        postTitleTranslationVoteRepository.save(vote);
        return dto;
    }
}
