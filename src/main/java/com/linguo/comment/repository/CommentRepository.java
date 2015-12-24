package com.linguo.comment.repository;

import com.linguo.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
    Page<Comment> findByPostIdAndParentIdIsNull(Long postId, Pageable pageable);
}
