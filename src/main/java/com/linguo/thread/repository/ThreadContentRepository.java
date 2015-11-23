package com.linguo.thread.repository;

import com.linguo.thread.model.ThreadContent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadContentRepository extends PagingAndSortingRepository<ThreadContent, Long> {
}
