package com.linguo.post.repository;


import com.linguo.post.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
