package com.linguo.thread.repository;

import com.linguo.thread.model.ThreadComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ThreadCommentRepository extends PagingAndSortingRepository<ThreadComment, Long> {
    Page<ThreadComment> findByThreadId(Long threadId, Pageable pageable);
}
