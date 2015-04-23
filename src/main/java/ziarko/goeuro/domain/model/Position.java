package ziarko.goeuro.domain.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Position {

    @SerializedName("latitude")
    private BigDecimal latitude;

    @SerializedName("longitude")
    private BigDecimal longitude;

    public Position(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Position() {
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

}
