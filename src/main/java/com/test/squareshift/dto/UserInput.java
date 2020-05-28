package com.test.squareshift.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class UserInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9110080193858126704L;

    @Getter
    @Setter
    @ApiModelProperty(value = "seats", required = true, example = "[[3,2],[4,3],[2,3],[3,4]]")
    private @NotEmpty(message = "seats should not be empty") List<@NotEmpty(message = "rows cols should not be empty") @Size(min = 2, max = 2, message = "input size should be two") List<@Min(value = 1, message = "(row, col) value should be greater than 0") Integer>> seats;

    @Getter
    @Setter
    @ApiModelProperty(value = "passengers", required = true, example = "30")
    @Min(value = 1, message = "Passenger count should be greater than 0")
    private int noOfPassengers;

}
