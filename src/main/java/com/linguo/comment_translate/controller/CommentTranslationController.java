package com.linguo.comment_translate.controller;


import com.linguo.common.dto.PageableDTO;
import com.linguo.comment_translate.dto.CommentTranslationDTO;
import com.linguo.comment_translate.dto.CommentTranslationFilterDTO;
import com.linguo.comment_translate.service.CommentTranslationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/comment_translations")
public class CommentTranslationController {

    @Autowired
    CommentTranslationServiceImpl threadCommentTranslationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentTranslationDTO get(@PathVariable Long id){
        return threadCommentTranslationService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<CommentTranslationDTO> getPage(CommentTranslationFilterDTO filter, PageableDTO pageableDTO){
        return threadCommentTranslationService.findByPage(filter, pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommentTranslationDTO create(@RequestBody CommentTranslationDTO dto){
        return threadCommentTranslationService.create(dto);
    }
}
