package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Position {

    private final Long playerId;

    private final String position;

    @JsonCreator
    public Position(@JsonProperty("id") Long playerId, @JsonProperty("name") String position){
        this.playerId = playerId;
        this.position = position;
    }

}
