package com.yjx.demo.quizzes.thoughtwork.service;

@FunctionalInterface
public interface ByTicketCall<T> {

    void call(T t) throws Exception;
}
