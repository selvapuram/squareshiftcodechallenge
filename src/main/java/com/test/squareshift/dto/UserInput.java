package com.test.squareshift.dto;

import java.io.Serializable;
import java.util.List;

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
    @ApiModelProperty(value = "seats", required = true,  example="[[3,2],[4,3],[2,3],[3,4]]")
    private List<List<Integer>> seats;
    
    @Getter
    @Setter
    @ApiModelProperty(value = "passengers", required = true,  example="30")
    private int noOfPassengers;

}
