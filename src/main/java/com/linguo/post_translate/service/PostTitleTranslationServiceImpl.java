package com.linguo.post_translate.service;

import com.linguo.post.model.Post;
import com.linguo.post_translate.dto.PostTitleTranslationDTO;
import com.linguo.post_translate.dto.PostTranslationFilterDTO;
import com.linguo.post_translate.model.PostTitleTranslation;
import com.linguo.post_translate.model.PostTitleTranslationVote;
import com.linguo.post_translate.repository.PostTitleTranslationRepository;
import com.linguo.post_translate.repository.PostTitleTranslationVoteRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostTitleTranslationServiceImpl {
    @Autowired
    PostTitleTranslationRepository postTitleTranslationRepository;
    @Autowired
    PostTitleTranslationVoteRepository postTitleTranslationVoteRepository;

    public PostTitleTranslationDTO findById(Long id){
        return getDTOFromEntity(postTitleTranslationRepository.findOne(id));
    }

    public Page<PostTitleTranslationDTO> findByPage(PostTranslationFilterDTO filter, Pageable pageable){
        Page<PostTitleTranslation> translations = filter.getPostId()!=null ?
                postTitleTranslationRepository.findByPostId(filter.getPostId(), pageable):
                postTitleTranslationRepository.findAll(pageable);
        List<PostTitleTranslationDTO> dtos = translations.getContent().stream()
                .map(entity -> getDTOFromEntity(entity)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, translations.getTotalElements());
    }

    private PostTitleTranslationDTO getDTOFromEntity(PostTitleTranslation entity){
        PostTitleTranslationDTO dto = new PostTitleTranslationDTO(entity);

        PostTitleTranslationVote vote = postTitleTranslationVoteRepository.findByUserIdAndPostTitleTranslationId(1L, entity.getId());
        if(vote!=null){
            dto.setUserVote(vote.getVote());
        }
        return dto;
    }

    public PostTitleTranslationDTO create(PostTitleTranslationDTO dto){
        PostTitleTranslation translation = new PostTitleTranslation();
        translation.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        translation.setUser(user);
        translation.setMessage(dto.getMessage());

        Post post = new Post();
        post.setId(dto.getPostId());
        translation.setPost(post);
        translation.setPoints(0);
        return new PostTitleTranslationDTO(postTitleTranslationRepository.save(translation));
    }
}
