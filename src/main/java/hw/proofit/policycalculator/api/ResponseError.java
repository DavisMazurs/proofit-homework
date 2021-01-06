package hw.proofit.policycalculator.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseError implements Response {
    private String error;

    public ResponseError(@JsonProperty(value = "error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
