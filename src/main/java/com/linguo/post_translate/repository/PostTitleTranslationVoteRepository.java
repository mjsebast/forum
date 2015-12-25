package com.linguo.post_translate.repository;

import com.linguo.post_translate.model.PostTitleTranslationVote;
import org.springframework.data.repository.CrudRepository;


public interface PostTitleTranslationVoteRepository extends CrudRepository<PostTitleTranslationVote, Long> {
    PostTitleTranslationVote findByUserIdAndPostTitleTranslationId(Long userId, Long translationId);
}