package com.bajaj.webhookapp.service;

import com.bajaj.webhookapp.model.SolutionRequest;
import com.bajaj.webhookapp.model.WebhookRequest;
import com.bajaj.webhookapp.model.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookServiceImpl implements WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookServiceImpl.class);
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    private final RestTemplate restTemplate;

    public WebhookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void processWebhookFlow() {
        try {
            // Step 1: Send POST request to generate webhook
            WebhookResponse webhookResponse = generateWebhook();
            
            if (webhookResponse != null) {
                logger.info("Webhook generated successfully: {}", webhookResponse.getWebhook());
                
                // Step 2: Get the required SQL query
                String sqlSolution = getHighestSalaryNotOnFirstDayQuery();
                
                // Step 3: Submit solution to webhook URL
                submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlSolution);
            }
        } catch (Exception e) {
            logger.error("Error processing webhook flow", e);
        }
    }

    private WebhookResponse generateWebhook() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            WebhookRequest request = new WebhookRequest(
                    "Mohit Gupta",
                    "0827CS221167",
                    "mohitgupta220841@acropolis.in"
            );
            
            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
            
            return restTemplate.postForObject(GENERATE_WEBHOOK_URL, entity, WebhookResponse.class);
        } catch (Exception e) {
            logger.error("Error generating webhook", e);
            return null;
        }
    }
    
    private String getHighestSalaryNotOnFirstDayQuery() {
        return """
        SELECT
            p.AMOUNT AS SALARY,
            CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
            TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE,
            d.DEPARTMENT_NAME
        FROM
            PAYMENTS p
            JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
            JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
        WHERE
            DAY(p.PAYMENT_TIME) <> 1
        ORDER BY
            p.AMOUNT DESC
        LIMIT 1;
        """;
    }
    
    private void submitSolution(String webhookUrl, String accessToken, String sqlSolution) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);
            
            SolutionRequest solutionRequest = new SolutionRequest(sqlSolution);
            HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);
            
            restTemplate.postForObject(webhookUrl, entity, String.class);
            logger.info("Solution submitted successfully");
        } catch (Exception e) {
            logger.error("Error submitting solution", e);
        }
    }
} 