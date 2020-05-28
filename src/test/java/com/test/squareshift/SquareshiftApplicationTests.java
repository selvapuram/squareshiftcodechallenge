package com.test.squareshift;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.squareshift.dto.UserInput;
import com.test.squareshift.exception.ErrorModel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SquareshiftApplication.class)
@ActiveProfiles("test")
class SquareshiftApplicationTests {

    @Autowired
    protected TestRestTemplate template;

    private static final String REQUEST_URL = "/v1/seats";
    
    
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testPing() throws Exception {
        String uri = "/ping";
        ResponseEntity<String> response = template.getForEntity(uri, String.class);
        Assert.assertEquals("Health Check", response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void testSingleCluster() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(3);
        seats.add(2);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(6);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<String> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, String.class);
        String expectedResponse = objectMapper.readValue(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/singleClusterOutput.json"), String.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.CREATED.value());
        Assert.assertNotNull("Null Check", response.getBody());
        //Assert.assertEquals("Size", response.getBody().size(), seatsArr.size());
        Assert.assertEquals(objectMapper.readTree(expectedResponse), objectMapper.readTree(response.getBody()));
    }

    /**
     * @param userInput
     * @return
     */
    private HttpEntity<UserInput> getDefaultEntity(UserInput userInput) {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<UserInput> httpEntity = new HttpEntity<UserInput>(userInput, httpHeader);
        return httpEntity;
    }
    
    @Test
    public void testMultiCluster() throws Exception {
        TypeReference<List<List<Integer>>> typeRef = new TypeReference<List<List<Integer>>>() {
        };
        List<List<Integer>> seatsArr = objectMapper.readValue("[[3,2],[4,3],[2,3],[3,4]]", typeRef);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(30);
        userInput.setSeats(seatsArr);
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<UserInput> httpEntity = new HttpEntity<UserInput>(userInput, httpHeader);
        ResponseEntity<String> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, String.class);
        String expectedResponse = objectMapper.readValue(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/multiClusterOutput.json"), String.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.CREATED.value());
        Assert.assertNotNull("Null Check", response.getBody());
        //Assert.assertEquals("Size", response.getBody().size(), seatsArr.size());
        Assert.assertEquals(objectMapper.readTree(expectedResponse), objectMapper.readTree(response.getBody()));
    }
    
    @Test
    public void testInvalidPassengerCount() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(3);
        seats.add(2);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(0);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testBlockSize() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(3);
        seats.add(2);
        seats.add(4);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(0);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testInvalidSeats() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(2);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testInvalidRow() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(0);
        seats.add(2);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(2);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testInvalidColumn() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(2);
        seats.add(0);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(2);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testInvalidBlock() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(2);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), ErrorModel.ErrorCodes.ERR_INVALID_INPUT.getCode());
        
    }
    
    @Test
    public void testCapacityFull() throws Exception {
        List<List<Integer>> seatsArr = new ArrayList<>();
        List<Integer> seats = new ArrayList<>();
        seats.add(3);
        seats.add(2);
        seatsArr.add(seats);
        UserInput userInput = new UserInput();
        userInput.setNoOfPassengers(7);
        userInput.setSeats(seatsArr);
        HttpEntity<UserInput> httpEntity = getDefaultEntity(userInput);
        ResponseEntity<ErrorModel> response = template.exchange(REQUEST_URL, HttpMethod.POST, httpEntity, ErrorModel.class);
        Assert.assertEquals("Status Code", response.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        Assert.assertEquals("ErrorCode",  response.getBody().getCode(), "1005");
        
    }
    
    

}
