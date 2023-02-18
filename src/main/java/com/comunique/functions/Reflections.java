package com.comunique.functions;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface Reflections {

    public static int getMethodPosition(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().equals(methodName)) {
                return i;
            }
        }
        return -1;
    }

    public static String getURI(Class<?> classController, String nameMethod)
            throws NoSuchMethodException, SecurityException {
        RequestMapping classMapping = classController.getAnnotation(RequestMapping.class);
        String classUrl = classMapping.value()[0];
        Method[] methodList = classController.getDeclaredMethods();
        String URI = classUrl;
        Method method;
        for (Method method2 : methodList) {
            if (method2.getName().equals(nameMethod)) {
                method = method2;

                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                    URI += mapping.value()[0];
                } else if (method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping mapping = method.getAnnotation(GetMapping.class);
                    URI += mapping.value()[0];
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    PostMapping mapping = method.getAnnotation(PostMapping.class);
                    URI += mapping.value()[0];
                } else if (method.isAnnotationPresent(PutMapping.class)) {
                    PutMapping mapping = method.getAnnotation(PutMapping.class);
                    URI += mapping.value()[0];
                } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                    DeleteMapping mapping = method.getAnnotation(DeleteMapping.class);
                    URI += mapping.value()[0];
                } else if (method.isAnnotationPresent(PatchMapping.class)) {
                    PatchMapping mapping = method.getAnnotation(PatchMapping.class);
                    URI += mapping.value()[0];
                } else {
                    throw new IllegalArgumentException("Método sem anotação de mapeamento HTTP");
                }
            }
        }

        return URI;
    }
}
