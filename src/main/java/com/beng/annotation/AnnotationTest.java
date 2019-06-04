package com.beng.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Set;

import org.reflections.Reflections;

/**
 * 注解学习
 * 
 * @author apple
 */
//
// java.lang.annotation 包下，四个元 Annotation
@MyAnnotation
public class AnnotationTest {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        System.out.println(A.class.isAnnotationPresent(MyAnnotation.class));

        // 打印所有的子类信息
        Reflections reflections = new Reflections("java.lang.reflect.*");
        Set<Class<? extends AnnotatedElement>> subTypes = reflections.getSubTypesOf(AnnotatedElement.class);
        subTypes.forEach(x -> System.out.println("AnnotatedElement Impl：" + x.getName()));

        // 遍历所有注释并获取注解的元信息
        Annotation[] array = Class.forName("com.beng.annotation.A").getMethod("say").getAnnotations();
        Arrays.asList(array).forEach(x -> System.out.println("A :" + x.annotationType()));
        for (Annotation a : array) {
            if (a instanceof MyAnnotationMethod) {
                System.out.println("A MyAnnotationMethod name:" + ((MyAnnotationMethod) a).name());
            }
        }
    }

}
