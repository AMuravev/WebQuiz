package engine.controller;

import engine.Utils.OutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RequestBodyAdviceController implements ResponseBodyAdvice<Object> {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(OutputDTO.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        for (Annotation ann : returnType.getMethodAnnotations()) {
            OutputDTO dtoType = AnnotationUtils.getAnnotation(ann, OutputDTO.class);

            if (dtoType != null) {

                if (dtoType.list() && body instanceof List<?>) {
                    return mapList((List<?>) body, dtoType.value());
                } else {
                    return modelMapper.map(body, dtoType.value());
                }

            }
        }
        throw new RuntimeException();
    }

    private <T> List<T> mapList(List<?> source, Class<T> destinationType) {
        return source
                .stream()
                .map(obj -> modelMapper.map(obj, destinationType))
                .collect(Collectors.toList());
    }

}
