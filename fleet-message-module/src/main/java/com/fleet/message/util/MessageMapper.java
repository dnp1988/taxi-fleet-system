package com.fleet.message.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.message.exception.MessageMappingException;

public class MessageMapper {

    private final ObjectMapper objectMapper;

    public MessageMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getMessageAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new MessageMappingException("Failed to map object to message", e);
        }
    }

    public <T> T parseMessage(String message, Class<T> type) {
        try {
            return objectMapper.readValue(message, type);
        } catch (JsonProcessingException e) {
            throw new MessageMappingException("Failed to map message to object", e);
        }
    }
}
