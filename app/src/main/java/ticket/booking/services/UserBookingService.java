package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User>userList;
    private User user;

    private  final String  USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";
//    created this path so that we can use local db users data in our project


    private List<Train>trainList;

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        fetchUsers();
    }

    public UserBookingService() throws IOException {
        fetchUsers();
    }
    public void fetchUsers() throws IOException {
//        This is done so that our service remembers which user has logged into the system
        File users = new File(USERS_PATH);
//        new file is created but here it will be read in simple format , so first we will map it like user_id => userId
//        for this object mapper is used .
        userList= objectMapper.readValue(users, new TypeReference <List<User>> (){});
    }
    public  Boolean login(){
      Optional<User> foundUser =   userList.stream().filter(user1 -> {
                return user.getUserName().equals(user1.getUserName())
                  && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());}).findFirst();

      return foundUser.isPresent();
    }
    public Boolean signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }

    }
    public void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchUsersBooking(){
        user.printTicket();
    }

    public boolean cancelUserBooking(String ticketId) throws IOException {

//        File usersFile = new File(USERS_PATH);
//       List<User> users= objectMapper.readValue(usersFile, new TypeReference<List<User>>() {});
       Optional<User>userOptional= userList.stream().filter(user1->
            user1.getUserId().equals(user.getUserId())
        ).findFirst();

       if(userOptional.isPresent()){
           User user1= userOptional.get();
           if(user1.getTicketsBooked().contains(ticketId)) {
               user1.getTicketsBooked().remove(ticketId);
               saveUserListToFile();
               return true;
           }
       }
       return false;
    }



    public List<Train>getTrain(String source , String destination)  {

        TrainService trainService ;
        try {
            trainService = new TrainService( );
            return trainService.fetchTrainFromSToD( source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
            return new ArrayList<>();
        }

    }

    public String bookSeat( Train train, int row , int column ){
        TrainService trainService;
        try {
            trainService = new TrainService( );
            return trainService.bookSeatInTrain(train, row ,column);
        } catch (IOException e) {
            return "No seat available ";
        }

    }

}
