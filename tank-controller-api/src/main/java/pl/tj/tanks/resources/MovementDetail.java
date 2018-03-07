package pl.tj.tanks.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MovementDetail {

    @NotNull
    public final MovementDirection direction;

    @JsonCreator
    public MovementDetail(@JsonProperty("direction") MovementDirection direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "MovementDetail{" +
                "direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovementDetail that = (MovementDetail) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return direction != null ? direction.hashCode() : 0;
    }

    public enum MovementDirection {
        FORWARD,BACKWARD,STOPPED,LEFT,RIGHT
    }
}
