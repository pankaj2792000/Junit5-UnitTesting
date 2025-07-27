package org.example;

public class Calculator {

    public int add(int a ,int b){
        return a+b;
    }

    public  int substract(int a,int b){
        return a-b;
    }

    public  int multiply(int a,int b){
        return a*b;
    }

    public  int division(int a,int b){
        if(b==0){
            throw new ArithmeticException("division by zero");
        }
        return a/b;
    }
}
