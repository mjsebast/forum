package com.linguo.post_translate.service;

import com.linguo.post.model.Post;
import com.linguo.post_translate.dto.PostContentTranslationDTO;
import com.linguo.post_translate.dto.PostTranslationFilterDTO;
import com.linguo.post_translate.model.PostContentTranslation;
import com.linguo.post_translate.model.PostContentTranslationVote;
import com.linguo.post_translate.repository.PostContentTranslationRepository;
import com.linguo.post_translate.repository.PostContentTranslationVoteRepository;
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
public class PostContentTranslationServiceImpl {
    @Autowired
    PostContentTranslationRepository postContentTranslationRepository;
    @Autowired
    PostContentTranslationVoteRepository postContentTranslationVoteRepository;

    public PostContentTranslationDTO findById(Long id){
        return getDTOFromEntity(postContentTranslationRepository.findOne(id));
    }

    public Page<PostContentTranslationDTO> findByPage(PostTranslationFilterDTO filter, Pageable pageable){
        Page<PostContentTranslation> translations = filter.getPostId()!=null ?
                postContentTranslationRepository.findByPostId(filter.getPostId(), pageable):
                postContentTranslationRepository.findAll(pageable);
        List<PostContentTranslationDTO> dtos = translations.getContent().stream()
                .map(entity -> getDTOFromEntity(entity)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, translations.getTotalElements());
    }

    private PostContentTranslationDTO getDTOFromEntity(PostContentTranslation entity){
        PostContentTranslationDTO dto = new PostContentTranslationDTO(entity);

        PostContentTranslationVote vote = postContentTranslationVoteRepository.findByUserIdAndPostContentTranslationId(1L, entity.getId());
        if(vote!=null){
            dto.setUserVote(vote.getVote());
        }
        return dto;
    }

    public PostContentTranslationDTO create(PostContentTranslationDTO dto){
        PostContentTranslation translation = new PostContentTranslation();
        translation.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        translation.setUser(user);
        translation.setMessage(dto.getMessage());

        Post post = new Post();
        post.setId(dto.getPostId());
        translation.setPost(post);
        translation.setPoints(0);
        return new PostContentTranslationDTO(postContentTranslationRepository.save(translation));
    }
}
