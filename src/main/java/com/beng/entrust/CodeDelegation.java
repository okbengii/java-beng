package com.beng.entrust;

import java.lang.reflect.InvocationTargetException;

/**
 * @desc 委托写代码
 * @author apple
 * @date 2019年11月8日
 */
public class CodeDelegation extends AbstractDelegation {

    @Override
    public void addListener(Object object, String methodName, Object... args) {
        System.out.println("add Event Listener ...");
        this.getEventHandler().addEvent(object, methodName, args);
    }

    @Override
    public void invokeEvent() {
        System.out.println("start invoke Event ... ");
        try {
            this.getEventHandler().notifyX();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
