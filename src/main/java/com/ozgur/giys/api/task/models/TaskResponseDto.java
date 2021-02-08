package com.ozgur.giys.api.task.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponseDto implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = -7885848451941655490L;

	@JsonProperty("status")
	private int status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private Object result;


}
