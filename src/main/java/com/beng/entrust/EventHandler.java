package com.beng.entrust;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc 委托执行器
 * @author apple
 * @date 2019年11月8日
 */
public class EventHandler {

    /**
     * 委托的事件
     */
    private List<Event> eventList = new ArrayList<>();

    /**
     * @desc 添加事件
     * @param object
     * @param methodName
     * @param objects
     */
    public void addEvent(Object object, String methodName, Object... args) {
        eventList.add(new Event(object, methodName, args));
    }

    /**
     * @desc 通知监听事件执行
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void notifyX() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Event event : eventList) {
            event.invoke();
        }
    }
}
