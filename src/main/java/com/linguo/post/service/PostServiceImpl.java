package com.linguo.post.service;

import com.linguo.forum.Forum;
import com.linguo.post.dto.PostDTO;
import com.linguo.post.model.Post;
import com.linguo.post.model.PostContent;
import com.linguo.post.repository.PostRepository;
import com.linguo.common.dto.TranslationDTO;
import com.linguo.common.util.TranslationService;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl {
    @Autowired
    PostRepository postRepository;

    @Autowired
    TranslationService translationService;

    public PostDTO findById(Long id){
        return new PostDTO(postRepository.findOne(id));
    }

    public Page<PostDTO> findByPage(Pageable pageable){
        Page<Post> threads = postRepository.findAll(pageable);
        List<PostDTO> dtos = threads.getContent().stream()
                .map(PostDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, threads.getTotalElements());
    }

    public PostDTO create(PostDTO dto){
        Post entity = new Post();
        entity.setLanguageId(dto.getLanguage());
        Forum forum = new Forum();
        forum.setId(1L);
        entity.setForum(forum);
        UserAccount user = new UserAccount();
        user.setId(1L);
        entity.setUser(user);
        entity.setUrl(dto.getUrl());
        Set<PostContent> contents = new HashSet<PostContent>();
        for(String key: dto.getContent().keySet()){
            PostContent content = dto.getContent().get(key);
            content.setPost(entity);
            content.setLanguageId(key);
            contents.add(content);

            TranslationDTO translationDTO = new TranslationDTO(key, key.equals("en")?"es":"en");
            translationDTO.addText(content.getMessage());
            translationDTO.addText(content.getTitle());
            String[] translations = translationService.translateContent(translationDTO);

            PostContent secondary = new PostContent();
            secondary.setLanguageId(translationDTO.getTo());
            secondary.setMessage(translations[0]);
            secondary.setTitle(translations[1]);
            secondary.setPost(entity);
            contents.add(secondary);
        }
        entity.setContent(contents);
        return new PostDTO(postRepository.save(entity));
    }


}
