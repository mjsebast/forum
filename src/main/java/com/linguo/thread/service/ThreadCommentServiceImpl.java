package com.linguo.thread.service;

import com.linguo.thread.dto.ThreadCommentDTO;
import com.linguo.thread.dto.ThreadCommentFilterDTO;
import com.linguo.thread.model.ThreadComment;
import com.linguo.thread.repository.ThreadCommentRepository;
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
public class ThreadCommentServiceImpl {
    @Autowired
    ThreadCommentRepository threadCommentRepository;

    public ThreadCommentDTO findById(Long id){
        return new ThreadCommentDTO(threadCommentRepository.findOne(id));
    }

    public Page<ThreadCommentDTO> findByPage(ThreadCommentFilterDTO filter, Pageable pageable){
        Page<ThreadComment> threads = filter.getThreadId()!=null ?
                threadCommentRepository.findByThreadId(filter.getThreadId(), pageable):
                threadCommentRepository.findAll(pageable);
        List<ThreadCommentDTO> dtos = threads.getContent().stream()
                .map(ThreadCommentDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, threads.getTotalElements());
    }
}
