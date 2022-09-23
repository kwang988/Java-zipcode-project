package com.kenzie.app.data.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodeDTO {
    //declare properties
    @JsonIgnoreProperties
    private List<Place> places;
    private String state;
    //declare getter/setter


    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
