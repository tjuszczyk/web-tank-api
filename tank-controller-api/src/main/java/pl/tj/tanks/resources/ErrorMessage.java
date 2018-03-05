package pl.tj.tanks.resources;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ErrorMessage {
    public final String code;
    public final String message;

    @JsonCreator
    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
