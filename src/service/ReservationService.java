package service;
import model.*;
import java.util.*;
public class ReservationService {

    private ReservationService(){};
    private static ReservationService reservationService = null;
    public static ReservationService getInstance(){
        if(null == reservationService){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    private List<IRoom> rooms = new ArrayList<>();
    private Set<Reservation> reservations = new HashSet<>();

    public List<IRoom> getRooms() {
        return rooms;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }
    //created myself
    public IRoom createARoom(String roomNumber,Double price, RoomType roomType){
        Room room = new Room(roomNumber,price,roomType);
        return room;
    }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room:rooms){
            if(roomId.equals(room.getRoomNumber())){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,Date checkOutDate){
        Reservation reservedRooms = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservedRooms);
        return reservedRooms;
    }
    public List<IRoom> findRooms (Date checkInDate, Date checkOutDate){
        List<IRoom> freeRooms = new ArrayList<>();
        Set<IRoom> reservedRooms = getReservedRooms(checkInDate,checkOutDate);

        for(IRoom room :rooms){
            if(!reservedRooms.contains(room)){
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }

    private Set<IRoom> getReservedRooms(Date checkInDate, Date checkOutDate ){
        Set<IRoom> reservedRooms = new HashSet<>();
        for(Reservation r : reservations){
            IRoom room = r.getRoom();
            if(!(r.getCheckInDate().after(checkOutDate) || r.getCheckOutDate().before(checkInDate)))
                reservedRooms.add(room);
        }
        return reservedRooms;

    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        Set<Reservation> setCustomerReservation = new HashSet<>();
        for(Reservation r:reservations){
            if(r.getCustomer().equals(customer)) {
                setCustomerReservation.add(r);
            }
        }
        return setCustomerReservation;

    }

    public void printAllReservation(){
        for(Reservation r : reservations){
            System.out.println(r);
        }
    }













}
