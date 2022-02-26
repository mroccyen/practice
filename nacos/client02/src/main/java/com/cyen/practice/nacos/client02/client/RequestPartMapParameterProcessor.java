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
import lombok.SneakyThrows;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link RequestPartMap} parameter processor.
 *
 * @author qingshanpeng
 * @see AnnotatedParameterProcessor
 */
@Component
public class RequestPartMapParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<RequestPartMap> ANNOTATION = RequestPartMap.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @SneakyThrows
    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        int parameterIndex = context.getParameterIndex();
        Class<?>[] parameterTypes = method.getParameterTypes();
        MethodMetadata data = context.getMethodMetadata();
        Class<?> targetType = parameterTypes[parameterIndex];
        String name = targetType.getName();
        data.formParams().add(name);
        data.indexToName().put(parameterIndex, Collections.singleton(name));
        return true;
    }

    public List<String> getFieldNames(Class<?> type) throws IntrospectionException {
        List<String> names = new ArrayList<>();
        for (PropertyDescriptor pd : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
            boolean isGetterMethod = pd.getReadMethod() != null && !"class".equals(pd.getName());
            if (isGetterMethod) {
                Method method = pd.getReadMethod();
                RequestPart alias = method.getAnnotation(RequestPart.class);
                String name = alias != null ? alias.value() : pd.getName();
                names.add(name);
            }
        }
        return names;
    }
}
