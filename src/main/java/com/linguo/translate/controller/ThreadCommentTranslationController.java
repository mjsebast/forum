package com.linguo.translate.controller;


import com.linguo.common.dto.PageableDTO;
import com.linguo.translate.dto.ThreadCommentTranslationDTO;
import com.linguo.translate.dto.ThreadCommentTranslationFilterDTO;
import com.linguo.translate.service.ThreadCommentTranslationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/thread_comment_translations")
public class ThreadCommentTranslationController {

    @Autowired
    ThreadCommentTranslationServiceImpl threadCommentTranslationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ThreadCommentTranslationDTO get(@PathVariable Long id){
        return threadCommentTranslationService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<ThreadCommentTranslationDTO> getPage(ThreadCommentTranslationFilterDTO filter, PageableDTO pageableDTO){
        return threadCommentTranslationService.findByPage(filter, pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ThreadCommentTranslationDTO create(@RequestBody ThreadCommentTranslationDTO dto){
        return threadCommentTranslationService.create(dto);
    }
}
