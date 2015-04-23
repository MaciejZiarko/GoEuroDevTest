package ziarko.goeuro.domain.model;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("_id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("geo_position")
    private Position position;

    public Suggestion(Long id, String name, String type, Position position) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.position = position;
    }

    private Suggestion() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }
}
