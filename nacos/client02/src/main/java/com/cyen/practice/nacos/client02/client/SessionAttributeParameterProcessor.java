/*
 * Copyright 2013-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyen.practice.nacos.client02.client;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;

/**
 * @author qingshanpeng
 * @see SessionAttribute annotation processor.
 */
@Component
public class SessionAttributeParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<SessionAttribute> ANNOTATION = SessionAttribute.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return true;
        }

        int parameterIndex = context.getParameterIndex();
        MethodMetadata data = context.getMethodMetadata();
        SessionAttribute sessionAttribute = ANNOTATION.cast(annotation);
        String name = sessionAttribute.value().trim();

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(name);

        checkState(emptyToNull(name) != null, "sessionAttribute.name() was empty on parameter %s", parameterIndex);
        context.setParameterName(name);

        data.template().removeHeader(name);
        Collection<String> header = context.setTemplateParameter(name, data.template().headers().get(name));
        data.template().header(name, header);

        return true;
    }

}
