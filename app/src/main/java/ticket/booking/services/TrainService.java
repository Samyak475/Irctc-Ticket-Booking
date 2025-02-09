package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    private User user;
    private List<Train> trainList;
    private Train train;
    private static final String  TRAINS_PATH = "app/src/main/java/ticket/booking/localDb/trains.json";
    ObjectMapper objectMapper = new ObjectMapper();



    public void fetchTrains() throws IOException {
        File trains = new File(TRAINS_PATH);
        trainList= objectMapper.readValue(trains, new TypeReference<List<Train>>(){});
    }

    public TrainService( ) throws IOException {
        fetchTrains();
    }

    public List<Train> fetchTrainFromSToD( String source , String destination){
        System.out.println("Fetch Train");
        return trainList.stream().filter(train -> validTrainStation(train , source , destination)).collect(Collectors.toList());

    }

    public Boolean validTrainStation(Train train , String source , String destination ){
        List<String> stations = train.getStations();
        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destinationIndex = stations.indexOf(destination.toLowerCase());
        if(sourceIndex != -1 && destinationIndex != -1 && sourceIndex<destinationIndex){
            return true;
        }
        return false;
    }
    public String  bookSeatInTrain(Train train , int row , int column ) throws IOException {
        List<List<Integer>> seats = train.getSeats();
        if(row>=0 && row<seats.size() && column>=0 && column< seats.get(0).size()){
            if(seats.get(row).get(column)==0){
                seats.get(row).set(column,1);
                train.setSeats(seats);
                addTrain(train);
            return "Seat no row column booked successfully ";
            }
        }
        return "Seat no is either Booked or not present ";
    }
    public void saveTrainListToFile() throws IOException {
        File trainsFile = new File(TRAINS_PATH);
        objectMapper.writeValue(trainsFile, trainList);
    }

    public  void addTrain(Train newTrain) throws IOException {
        Optional<Train> existingTrain = trainList.stream().filter(
                train1 -> train1.getTrainId().equalsIgnoreCase(newTrain.getTrainId())
        ).findFirst();
        if(existingTrain.isPresent()){
            updateTrain(newTrain);
        }
        else {
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }
    public  void updateTrain(Train newTrain) throws IOException {
        OptionalInt index = IntStream.range(0, trainList.size()).filter(i->
                trainList.get(i).getTrainId().equalsIgnoreCase(newTrain.getTrainId())).findFirst();
        if(index.isPresent()){
            trainList.set(index.getAsInt(), newTrain);
            saveTrainListToFile();
        }
        else {
            addTrain(newTrain);
        }
    }

}
