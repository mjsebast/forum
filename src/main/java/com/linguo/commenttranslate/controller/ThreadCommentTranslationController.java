package com.linguo.commenttranslate.controller;


import com.linguo.common.dto.PageableDTO;
import com.linguo.commenttranslate.dto.ThreadCommentTranslationDTO;
import com.linguo.commenttranslate.dto.ThreadCommentTranslationFilterDTO;
import com.linguo.commenttranslate.service.ThreadCommentTranslationServiceImpl;
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
