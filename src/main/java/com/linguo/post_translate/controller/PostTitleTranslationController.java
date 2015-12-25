package com.linguo.post_translate.controller;

import com.linguo.common.dto.PageableDTO;
import com.linguo.post_translate.dto.PostTitleTranslationDTO;
import com.linguo.post_translate.dto.PostTranslationFilterDTO;
import com.linguo.post_translate.model.PostTitleTranslation;
import com.linguo.post_translate.service.PostTitleTranslationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/post_title_translations")
public class PostTitleTranslationController {

    @Autowired
    PostTitleTranslationServiceImpl postTitleTranslationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostTitleTranslationDTO get(@PathVariable Long id){
        return postTitleTranslationService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PostTitleTranslationDTO> getPage(PostTranslationFilterDTO filter, PageableDTO pageableDTO){
        return postTitleTranslationService.findByPage(filter, pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PostTitleTranslationDTO create(@RequestBody PostTitleTranslationDTO dto){
        return postTitleTranslationService.create(dto);
    }
}
