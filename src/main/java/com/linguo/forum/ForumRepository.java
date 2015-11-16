package com.linguo.forum;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ForumRepository extends PagingAndSortingRepository<Forum, Long> {
}
