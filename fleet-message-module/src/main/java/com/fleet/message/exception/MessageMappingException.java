package com.fleet.message.exception;

public class MessageMappingException extends RuntimeException {

    public MessageMappingException(String message, Throwable t) {
        super(message, t);
    }
}
