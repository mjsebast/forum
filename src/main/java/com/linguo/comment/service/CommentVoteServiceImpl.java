package com.linguo.comment.service;

import com.linguo.comment.dto.CommentVoteDTO;
import com.linguo.comment.model.Comment;
import com.linguo.comment.model.CommentVote;
import com.linguo.comment.repository.CommentRepository;
import com.linguo.comment.repository.CommentVoteRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentVoteServiceImpl {

    @Autowired
    CommentVoteRepository commentVoteRepository;
    @Autowired
    CommentRepository commentRepository;

    public CommentVoteDTO create(Long commentId, CommentVoteDTO dto){
        CommentVote vote = commentVoteRepository.findByUserIdAndCommentId(1L, commentId);
        Comment comment = commentRepository.findOne(commentId);
        if(vote==null){
            vote = new CommentVote();
            UserAccount user = new UserAccount();
            user.setId(1L);
            vote.setUser(user);
            vote.setComment(comment);
        }

        if(vote.getVote()==null || vote.getVote().equals(0)){
            comment.setPoints(comment.getPoints() + dto.getVote());
        }
        else if(dto.getVote()==null || dto.getVote().equals(0)){
            comment.setPoints(comment.getPoints() + (vote.getVote() * -1));
        }
        else if(!dto.getVote().equals(0)){
            comment.setPoints(comment.getPoints() + (dto.getVote() * 2));
        }
        commentRepository.save(comment);

        vote.setVote(dto.getVote());
        commentVoteRepository.save(vote);
        return dto;
    }
}
