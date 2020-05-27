package com.test.squareshift.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.squareshift.dto.UserInput;
import com.test.squareshift.model.Cluster;
import com.test.squareshift.service.ISeatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "Seating Service", version = "v1.0", description = "Seating Service"))
@Api(value = "seats", tags = "seats")
@RestController
@RequestMapping("/")
public class SeatingController {
    
    private ISeatService seatService;
    
    @Value("${seatservice.implementation.strategy:default}")
    private String strategy;
    
    @ApiOperation(value = "create seats",
            notes = "create seats",
            nickname = "create seats")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{version}/seats/")
    public ResponseEntity<List<Cluster>> createSeats(@ApiParam(defaultValue = "v1") @PathVariable String version, @RequestBody UserInput userInput) {
        seatService = ISeatService.getDefaultSeatService(strategy); 
        return new ResponseEntity<>(seatService.process(userInput), HttpStatus.CREATED);
    }
    
    /**
     * Ping.
     *
     * @return the string with current timestamp
     */
    @ApiOperation(value = "${swagger.ping.value}",
      notes = "${swagger.ping.notes}", nickname = "ping")
    @GetMapping(value = "/ping")
    public String ping() {
      return "{\"operation\": \"Seating service ping\", \"timeStamp\": " + System.currentTimeMillis() + "}";
    }

}
