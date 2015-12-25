package com.linguo.post.repository;

import com.linguo.post.model.PostVote;
import org.springframework.data.repository.CrudRepository;


public interface PostVoteRepository extends CrudRepository<PostVote, Long> {
    PostVote findByUserIdAndPostId(Long userId, Long postId);
}