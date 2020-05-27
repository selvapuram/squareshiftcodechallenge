package com.test.squareshift.exception;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author madhankumar
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7819101233649625249L;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private long timestamp;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private String message;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private String code;

  public static ErrorModel of(String code, String message) {
    ErrorModel err = new ErrorModel();
    err.setCode(code).setMessage(message).setTimestamp(new Date().getTime());
    return err;
  }

}
