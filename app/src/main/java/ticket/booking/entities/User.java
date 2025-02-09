package ticket.booking.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {
//    @JsonProperty("name")
    private String userName;
    private String hashedPassword;
    private String password;
    private List <Ticket> ticketsBooked;
    private String userId;


    public User(String userName, String password,String hashedPassword, List<Ticket> ticketsBooked, String userId) {
        this.userName = userName;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }
    public User(){}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword(){
        return hashedPassword;
    }
    public  void setHashedPassword(String hashedPassword){
        this.hashedPassword=hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printTicket(){
        ticketsBooked.forEach(ticket->{System.out.println(ticket.ticketInfo());});
    }
}
