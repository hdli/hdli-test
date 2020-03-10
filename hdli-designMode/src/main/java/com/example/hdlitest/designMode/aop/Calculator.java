package com.example.hdlitest.designMode.aop;

public interface Calculator<T> {

     T add(T a, T b);

     T subtract(T a, T b);

     T multiply(T a, T b);
}
