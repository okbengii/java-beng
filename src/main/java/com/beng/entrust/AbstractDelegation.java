package com.beng.entrust;

/**
 * @desc 抽象类，不同的委托实现
 * @author apple
 * @date 2019年11月8日
 */
public abstract class AbstractDelegation {

    private EventHandler eventHandler = new EventHandler();

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * @desc 添加事件监听
     * @param object
     * @param methodName
     * @param args
     */
    public abstract void addListener(Object object, String methodName, Object... args);

    /**
     * @desc 执行委托事件
     */
    public abstract void invokeEvent();
}
