package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Ticket {
    private String source;
    private String destination;
    private String userId;
    private String ticketId;
    private String dateOfTravel;
    private Train train;

    public Ticket(){}

    public Ticket(String source, String destination, String userId, String ticketId, String dateOfTravel, Train train) {
        this.source = source;
        this.destination = destination;
        this.userId = userId;
        this.ticketId = ticketId;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
    public String ticketInfo(){
        return String.format("This Ticket %s belongs to User %s travelling from %s to %s on %s",ticketId,userId,source,destination,dateOfTravel);
    }
}
