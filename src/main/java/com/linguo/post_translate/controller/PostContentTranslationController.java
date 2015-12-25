package com.linguo.post_translate.controller;


import com.linguo.common.dto.PageableDTO;
import com.linguo.post_translate.dto.PostContentTranslationDTO;
import com.linguo.post_translate.dto.PostTranslationFilterDTO;
import com.linguo.post_translate.service.PostContentTranslationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/post_content_translations")
public class PostContentTranslationController {

    @Autowired
    PostContentTranslationServiceImpl postContentTranslationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostContentTranslationDTO get(@PathVariable Long id){
        return postContentTranslationService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PostContentTranslationDTO> getPage(PostTranslationFilterDTO filter, PageableDTO pageableDTO){
        return postContentTranslationService.findByPage(filter, pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PostContentTranslationDTO create(@RequestBody PostContentTranslationDTO dto){
        return postContentTranslationService.create(dto);
    }
}
