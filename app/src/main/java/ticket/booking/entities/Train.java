package ticket.booking.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Train {
    private String trainId;
    private String source;
    private String destination;
    private String  trainNo;
    private List<List<Integer>> seats;
    private HashMap<String, String> stationTime;
    private List<String> stations;

    public  Train(){  }

    public Train(String trainId, String source, String destination, String trainNo, List<List<Integer>> seats, HashMap<String, String> stationTime, List<String> stations) {
        this.trainId = trainId;
        this.source = source;
        this.destination = destination;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTime = stationTime;
        this.stations = stations;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public HashMap<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(HashMap<String, String> stationTime) {
        this.stationTime = stationTime;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
    public String trainInfo(){
        return String.format("Train no %s travels from %s to %s ", trainNo, source,destination);
    }
}
