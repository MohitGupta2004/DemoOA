package com.assessment.bajaj.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class RequestDto {

    private List<String> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String file_b64;
}
