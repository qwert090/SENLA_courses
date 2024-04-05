package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.exception.JacksonDeserializeException;
import org.example.exception.JacksonSerializeException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Json {
    private final ObjectMapper objectMapper;

    public <T> T deserialize(String json, Class<T> tClass){
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new JacksonDeserializeException(e);
        }
    }

    public <T> String serialize(T object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JacksonSerializeException(e);
        }
    }
}
