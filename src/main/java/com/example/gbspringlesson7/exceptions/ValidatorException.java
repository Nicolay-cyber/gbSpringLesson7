package com.example.gbspringlesson7.exceptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class ValidatorException extends RuntimeException {
    private List<String> errorFieldsMessages;

    public ValidatorException(List<String> errorFieldsMessages) {
        super(errorFieldsMessages.stream().collect(Collectors.joining(", ")));
        this.errorFieldsMessages = errorFieldsMessages;
    }
}

