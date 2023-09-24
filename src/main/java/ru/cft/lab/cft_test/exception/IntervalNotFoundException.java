package ru.cft.lab.cft_test.exception;

public class IntervalNotFoundException extends RuntimeException
{
    public IntervalNotFoundException(){
        super("Interval is empty");
    }
}
