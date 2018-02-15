package pl.tj.tanks.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovementDetail {
    public final MovementDirection direction;

    @JsonCreator
    public MovementDetail(@JsonProperty("movement") MovementDirection direction) {
        this.direction = direction;
    }

    public enum MovementDirection {
        FORWARD,BACKWARD,STOPPED,LEFT,RIGHT
    }
}
