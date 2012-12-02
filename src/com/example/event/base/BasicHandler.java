package com.example.event.base;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 10:49 AM
 */
public interface BasicHandler<T extends SimpleEvent> {
    void handle(T eventObj);
}
