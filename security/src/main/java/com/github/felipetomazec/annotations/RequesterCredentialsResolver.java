package com.github.felipetomazec.annotations;

import com.github.felipetomazec.dtos.RequesterInfo;
import com.github.felipetomazec.entities.Credentials;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

public class RequesterCredentialsResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Requester.class) != null;
    }

    @Override
    public RequesterInfo resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (supportsParameter(parameter)) {
            var principal = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var id = UUID.fromString(principal.getId());
            return new RequesterInfo(id, principal.getEmail());
        }
        return null;
    }
}
