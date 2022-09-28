package com.leftovers.order.order.exception;

public class BadDtoException extends Exception{
    public BadDtoException(String errorMessage)
    {
        super(errorMessage);
    }
}


//public class IncorrectFileNameException extends Exception {
//    public IncorrectFileNameException(String errorMessage) {
//        super(errorMessage);
//    }