package api;

import model.Customer;
import model.IRoom;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.List;

public class AdminResource {
     private AdminResource(){};
     private static AdminResource adminResource = null;
     public static AdminResource getInstance(){
         if (null == adminResource){
             adminResource = new AdminResource();
         }
         return adminResource;
     }

     public Customer getCustomer(String email){
         return CustomerService.getInstance().getCustomer(email);
     }

     public IRoom createARoom(String roomNumber,Double price, RoomType roomType){
         return ReservationService.getInstance().createARoom(roomNumber, price, roomType);
     }

     public void addRoom(List<IRoom> rooms){
         for(IRoom ir :rooms) {
             ReservationService.getInstance().addRoom(ir);
         }
     }

     public Collection<IRoom> getAllRooms(){
         return ReservationService.getInstance().getRooms();

     }

     public Collection<Customer> getAllCustomers(){
         return CustomerService.getInstance().getAllCustomer();
     }

     public void displayAllReservations(){
         ReservationService.getInstance().printAllReservation();
     }
}
