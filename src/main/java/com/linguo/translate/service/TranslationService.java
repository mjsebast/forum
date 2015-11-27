package com.linguo.translate.service;

import com.linguo.translate.dto.TranslationDTO;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TranslationService {
    @Autowired
    Environment environment;

    public String[] translateContent(TranslationDTO dto){
        Translate.setClientId(environment.getProperty("microsoft.translator.id"));
        Translate.setClientSecret(environment.getProperty("microsoft.translator.secret"));
        try{
            List<String> texts = dto.getTexts();
            String[] arr = texts.toArray(new String[texts.size()]);
            return Translate.execute(arr,
                    getLanguage(dto.getFrom()), getLanguage(dto.getTo()));
        }catch (Exception e){
            return null;
            //throw new Exception("");
        }
    }

    private Language getLanguage(String language){
        if(language.equals("en")){
            return Language.ENGLISH;
        }
        if(language.equals("es")){
            return Language.SPANISH;
        }
        return Language.ENGLISH;
    }
}
