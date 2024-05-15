package com.example.salesFunctions;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalesDataConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String convertToJson(Object data) throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule().addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter)));
        return objectMapper.writeValueAsString(data);
    }
}

