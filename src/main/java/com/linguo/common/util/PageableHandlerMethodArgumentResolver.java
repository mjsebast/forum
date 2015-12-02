package com.linguo.common.util;


import com.linguo.common.dto.PageableDTO;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

@Component("pageableHandlerMethodArgumentResolver")
public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, WebArgumentResolver {
    private static final String PARAM_QUERY = "query";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_LIMIT = "limit";
    private static final String PARAM_SORT = "sort";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PageableDTO.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  NativeWebRequest request) throws Exception {
        return getPagedRequest(request);
    }

    @Override
    public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
                                  NativeWebRequest request, WebDataBinderFactory binderFactory) {
        return getPagedRequest(request);
    }

    private PageableDTO getPagedRequest(NativeWebRequest request) {
        String query = request.getParameter(PARAM_QUERY);
        Integer page = Util.stringToInteger(request.getParameter(PARAM_PAGE));
        Integer limit = Util.stringToInteger(request.getParameter(PARAM_LIMIT));
        Sort sort = getSort(request);

        if (page != null) {
            page = page - 1;
        }

        return new PageableDTO(query, page, limit, sort);
    }

    private Sort getSort(NativeWebRequest request) {
        Sort sort = null;

        String sortParam = request.getParameter(PARAM_SORT);

        if (sortParam != null && !sortParam.isEmpty()) {
            String[] sortProperties = sortParam.split(",");

            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            for (String property : sortProperties) {
                String direction = request.getParameter(property + ".dir");

                if (direction != null && !direction.isEmpty() && "desc".equalsIgnoreCase(direction)) {
                    orders.add(new Sort.Order(Sort.Direction.DESC, property));
                } else {
                    orders.add(new Sort.Order(Sort.Direction.ASC, property));
                }
            }

            sort = new Sort(orders);
        }

        return sort;
    }

}
