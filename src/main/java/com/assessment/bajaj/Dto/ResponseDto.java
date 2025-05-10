package com.assessment.bajaj.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    @JsonProperty("is_success")
    private boolean is_success;

    private String user_id;
    private String email;
    private String roll_number;
    private List<String> numbers;
    private List<String> alphabets;
    private List<String> highest_lowercase_alphabet;

    @JsonProperty("is_prime_found")
    private boolean is_prime_found;
    private boolean file_valid;
    private String file_type;
    private String file_size_kb;

}
