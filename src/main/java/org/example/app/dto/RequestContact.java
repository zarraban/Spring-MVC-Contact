package org.example.app.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestContact(
        Long id,
        String name,
        String surname,
        String phone
) {

}

