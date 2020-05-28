package com.test.squareshift.exception;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author madhankumar
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(value = Include.NON_DEFAULT)
public class ErrorModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7819101233649625249L;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private long timestamp;

  @ApiModelProperty(value = "${ErrorModel.message.ApiModelProperty.value}", required = true, readOnly = true)
  private String message;

  @ApiModelProperty(value = "${ErrorModel.code.ApiModelProperty.value}", required = true, readOnly = true)
  private String code;
  
  @ApiModelProperty(value = "${ErrorModel.messages.ApiModelProperty.value}", required = true, readOnly = true)
  private List<String> messages;
  
  
  public static enum ErrorCodes {
      ERR_GENERIC_EXCEPTION("9999"),
      ERR_INVALID_INPUT("1003");
      ErrorCodes(String code) {
          this.code = code;
      }
      @Getter String code;
  }
}
