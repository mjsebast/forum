package com.linguo.translate.service;

import com.linguo.translate.dto.ThreadCommentTranslationDTO;
import com.linguo.translate.dto.ThreadCommentTranslationFilterDTO;
import com.linguo.translate.model.ThreadCommentTranslation;
import com.linguo.translate.repository.ThreadCommentTranslationRepository;
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
public class ThreadCommentTranslationServiceImpl {

    @Autowired
    ThreadCommentTranslationRepository threadCommentTranslationRepository;

    public ThreadCommentTranslationDTO findById(Long id){
        return new ThreadCommentTranslationDTO(threadCommentTranslationRepository.findOne(id));
    }

    public Page<ThreadCommentTranslationDTO> findByPage(ThreadCommentTranslationFilterDTO filter, Pageable pageable){
        Page<ThreadCommentTranslation> translations = filter.getCommentId()!=null ?
                threadCommentTranslationRepository.findByThreadCommentId(filter.getCommentId(), pageable):
                threadCommentTranslationRepository.findAll(pageable);
        List<ThreadCommentTranslationDTO> dtos = translations.getContent().stream()
                .map(ThreadCommentTranslationDTO:: new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, translations.getTotalElements());
    }

    public ThreadCommentTranslationDTO create(ThreadCommentTranslationDTO dto){
        ThreadCommentTranslation translation = new ThreadCommentTranslation();
        translation.setLanguageId(dto.getLanguage());
        UserAccount user = new UserAccount();
        user.setId(1L);
        translation.setUser(user);
        translation.setMessage(dto.getMessage());
        //translation.set
        return new ThreadCommentTranslationDTO(threadCommentTranslationRepository.save(translation));
    }
}
