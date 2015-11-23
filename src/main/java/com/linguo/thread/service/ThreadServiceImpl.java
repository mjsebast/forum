package com.linguo.thread.service;

import com.linguo.forum.Forum;
import com.linguo.thread.dto.ThreadDTO;
import com.linguo.thread.model.Thread;
import com.linguo.thread.model.ThreadContent;
import com.linguo.thread.repository.ThreadRepository;
import com.linguo.users.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ThreadServiceImpl {
    @Autowired
    ThreadRepository threadRepository;

    public ThreadDTO findById(Long id){
        return new ThreadDTO(threadRepository.findOne(id));
    }

    public Page<ThreadDTO> findByPage(Pageable pageable){
        Page<Thread> threads = threadRepository.findAll(pageable);
        List<ThreadDTO> dtos = new ArrayList<ThreadDTO>();
        for(Thread thread: threads){
            dtos.add(new ThreadDTO(thread));
        }
        Page<ThreadDTO> pagedDtos = new PageImpl<ThreadDTO>(dtos,pageable, threads.getTotalElements());
        return pagedDtos;
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
        Set<ThreadContent> contents = new HashSet<ThreadContent>();
        for(String key: dto.getContent().keySet()){
            ThreadContent content = dto.getContent().get(key);
            content.setThread(entity);
            contents.add(content);
        }
        entity.setContent(contents);
        return new ThreadDTO(threadRepository.save(entity));
    }
}
