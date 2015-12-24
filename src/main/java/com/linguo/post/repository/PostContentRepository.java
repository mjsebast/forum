package com.linguo.post.repository;

import com.linguo.post.model.PostContent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostContentRepository extends PagingAndSortingRepository<PostContent, Long> {
}
