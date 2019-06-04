package com.beng.annotation;

/**
 * 注解学习
 * 
 * @author apple
 */
//
// java.lang.annotation 包下，四个元 Annotation
@MyAnnotation
public class AnnotationTest {

    class A extends AnnotationTest {

    }

    public static void main(String[] args) {
        System.out.println(A.class.isAnnotationPresent(MyAnnotation.class));
    }

}
