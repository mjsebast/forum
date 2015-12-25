package com.linguo.post.service;

import com.linguo.common.dto.VoteDTO;
import com.linguo.post.model.Post;
import com.linguo.post.model.PostVote;
import com.linguo.post.repository.PostRepository;
import com.linguo.post.repository.PostVoteRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostVoteServiceImpl {

    @Autowired
    PostVoteRepository postVoteRepository;
    @Autowired
    PostRepository postRepository;

    public VoteDTO create(Long postId, VoteDTO dto){
        PostVote vote = postVoteRepository.findByUserIdAndPostId(1L, postId);
        Post post = postRepository.findOne(postId);
        if(vote==null){
            vote = new PostVote();
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
            vote.setPost(post);
        }

        if(vote.getVote()==null || vote.getVote().equals(0)){
            post.setPoints(post.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            post.setPoints(post.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            post.setPoints(post.getPoints() + (dto.getVote() * 2));
        }
        postRepository.save(post);

        vote.setVote(dto.getVote());
        postVoteRepository.save(vote);
        return dto;
    }
}
