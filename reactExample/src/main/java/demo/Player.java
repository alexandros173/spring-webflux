package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Player {

    private final Long id;

    private final String name;

    @JsonCreator
    public Player(@JsonProperty("id") Long id, @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }

}
