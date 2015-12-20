package com.linguo.thread.repository;


import com.linguo.thread.model.ThreadCommentContent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadCommentContentRepository extends PagingAndSortingRepository<ThreadCommentContent, Long> {
    ThreadCommentContent findFirstByLanguageIdAndThreadCommentId(String languageId, Long commentId);
}
