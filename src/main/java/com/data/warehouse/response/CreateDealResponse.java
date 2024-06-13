package com.data.warehouse.response;

import com.data.warehouse.model.Deal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateDealResponse(@JsonProperty("isSuccess") boolean isSuccess,
                                 String errorMessage,
                                 Deal deal) {

}
