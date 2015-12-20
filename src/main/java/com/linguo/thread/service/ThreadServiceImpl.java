package com.linguo.thread.service;

import com.linguo.forum.Forum;
import com.linguo.thread.dto.ThreadDTO;
import com.linguo.thread.model.Thread;
import com.linguo.thread.model.ThreadContent;
import com.linguo.thread.repository.ThreadRepository;
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
public class ThreadServiceImpl {
    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    TranslationService translationService;

    public ThreadDTO findById(Long id){
        return new ThreadDTO(threadRepository.findOne(id));
    }

    public Page<ThreadDTO> findByPage(Pageable pageable){
        Page<Thread> threads = threadRepository.findAll(pageable);
        List<ThreadDTO> dtos = threads.getContent().stream()
                .map(ThreadDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, threads.getTotalElements());
    }

    public ThreadDTO create(ThreadDTO dto){
        Thread entity = new Thread();
        entity.setLanguageId(dto.getLanguage());
        Forum forum = new Forum();
        forum.setId(1L);
        entity.setForum(forum);
        UserAccount user = new UserAccount();
        user.setId(1L);
        entity.setUser(user);
        entity.setUrl(dto.getUrl());
        Set<ThreadContent> contents = new HashSet<ThreadContent>();
        for(String key: dto.getContent().keySet()){
            ThreadContent content = dto.getContent().get(key);
            content.setThread(entity);
            content.setLanguageId(key);
            contents.add(content);

            TranslationDTO translationDTO = new TranslationDTO(key, key.equals("en")?"es":"en");
            translationDTO.addText(content.getMessage());
            translationDTO.addText(content.getTitle());
            String[] translations = translationService.translateContent(translationDTO);

            ThreadContent secondary = new ThreadContent();
            secondary.setLanguageId(translationDTO.getTo());
            secondary.setMessage(translations[0]);
            secondary.setTitle(translations[1]);
            secondary.setThread(entity);
            contents.add(secondary);
        }
        entity.setContent(contents);
        return new ThreadDTO(threadRepository.save(entity));
    }


}
