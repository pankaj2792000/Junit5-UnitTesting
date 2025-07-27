package org.example;

public class MathUtil {

    public boolean isEven(int number){
        return  number%2==0;
    }

    public  boolean isWeekDay(Days day){
        return day!=Days.SATURDAY && day!=Days.SUNDAY;
    }
    public  long factorial(int n){
        if(n==0 || n==1){
            return 1;
        }
        return n*factorial(n-1);
    }
}
