package com.linguo.thread;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ThreadContentRepository extends PagingAndSortingRepository<ThreadContent, Long> {
}
