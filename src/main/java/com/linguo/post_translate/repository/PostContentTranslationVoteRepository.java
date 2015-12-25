package com.linguo.post_translate.repository;


import com.linguo.post_translate.model.PostContentTranslationVote;
import org.springframework.data.repository.CrudRepository;

public interface PostContentTranslationVoteRepository  extends CrudRepository<PostContentTranslationVote, Long> {
    PostContentTranslationVote findByUserIdAndPostContentTranslationId(Long userId, Long translationId);
}
