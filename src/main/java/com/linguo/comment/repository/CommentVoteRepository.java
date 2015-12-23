package com.linguo.comment.repository;

import com.linguo.comment.model.CommentVote;
import org.springframework.data.repository.CrudRepository;


public interface CommentVoteRepository extends CrudRepository<CommentVote, Long> {
    CommentVote findByUserIdAndCommentId(Long userId, Long commentId);
}
