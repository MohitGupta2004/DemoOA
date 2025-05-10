package com.assessment.bajaj.service;

import com.assessment.bajaj.Dto.RequestDto;
import com.assessment.bajaj.Dto.ResponseDto;
import org.springframework.stereotype.Service;
import org.apache.tika.Tika;
import java.util.*;

@Service
public class TaskService {

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public ResponseDto task(RequestDto request) {

        List<String> dataInput = request.getData();
        String fileString = request.getFile_b64();

        boolean fileValid = false;
        String fileMimeType = null;
        String fileSize = null;

        if (fileString != null) {
            try {
                byte[] fileBytes = Base64.getDecoder().decode(fileString);

                fileValid = fileBytes.length > 0;

                if (fileValid) {
                    Tika tika = new Tika();
                    fileMimeType = tika.detect(fileBytes);

                    fileSize = String.format("%.2f KB", fileBytes.length / 1024.0);
                }
            } catch (IllegalArgumentException e) {
                fileValid = false;
            }
        }

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestLowercaseAlphabet = null;
        boolean isPrimeFound = false;

        for (String str : dataInput) {
            if (str.matches("[0-9]+")) {
                numbers.add(str);
                int number = Integer.parseInt(str);
                if (!isPrimeFound) {
                    isPrimeFound = isPrime(number);
                }
            } else if (str.matches("[a-z]+")) {
                alphabets.add(str);
                if (highestLowercaseAlphabet == null || str.compareTo(highestLowercaseAlphabet) > 0) {
                    highestLowercaseAlphabet = str;
                }
            } else {
                alphabets.add(str);
            }
        }

        ResponseDto response = ResponseDto.builder()
                .is_success(true)
                .user_id("Mohit_Gupta_09072004")
                .email("mohit0907gupta@gmail.com")
                .roll_number("0827CS221167")
                .numbers(numbers)
                .alphabets(alphabets)
                .highest_lowercase_alphabet(highestLowercaseAlphabet != null ? List.of(highestLowercaseAlphabet) : new ArrayList<>())
                .is_prime_found(isPrimeFound)
                .file_valid(fileValid)
                .build();

        if (fileValid) {
            response.setFile_type(fileMimeType);
            response.setFile_size_kb(fileSize);
        } else {
            response.setFile_type(null);
            response.setFile_size_kb(null);
        }

        return response;
    }
}
