package com.beng.entrust;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @desc 委托事件
 * @author apple
 * @date 2019年11月8日
 */
public class Event {

    /**
     * 要执行的方法对象
     */
    private Object object;

    /**
     * 要执行的方法名称
     */
    private String methodName;

    /**
     * 要执行的参数
     */
    private Object[] params;

    /**
     * 要执行的参数类型
     */
    private Class[] paramTypes;

    public Event(Object object, String methodName, Object... args) {
        super();
        this.object = object;
        this.methodName = methodName;
        this.params = args;
        this.createParamTypes(args);
    }

    /**
     * @desc 根据参数数组生成参数类型数组
     * @param params
     */
    private void createParamTypes(Object[] params) {
        this.paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            this.paramTypes[i] = params[i].getClass();
        }
    }

    /**
     * @desc 利用反射调用方法
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void invoke() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = object.getClass().getMethod(this.getMethodName(), this.getParamTypes());
        if (null == method) {
            return;
        }
        method.invoke(this.getObject(), this.getParams());
    }

    public Object getObject() {
        return object;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

}
