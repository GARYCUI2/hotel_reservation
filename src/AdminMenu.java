import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.RoomType;


import java.util.ArrayList;
import java.util.List;

public class AdminMenu {
    public static void admin() {
        boolean isFlag = true;
        do {
            System.out.println("Admin Menu");
            System.out.println("-------------------------------------------");
            System.out.println("1.See all Customers");
            System.out.println("2.See all Rooms");
            System.out.println("3.See all Reservations");
            System.out.println("4.Add a Room");
            System.out.println("5.Add Test Date");
            System.out.println("6.Back to Main Menu");
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option");

            char key = Utility.readMenuSelection(true);
            System.out.println();

            switch (key) {
                case '1':
                    for(Customer c: AdminResource.getInstance().getAllCustomers()){
                        System.out.println(c);
                    }
                    break;
                case '2':
                    for(IRoom room:AdminResource.getInstance().getAllRooms()){
                        System.out.println(room);
                    }
                    break;
                case '3':
                    AdminResource.getInstance().displayAllReservations();
                    break;
                case '4':
                    boolean keep = true;
                    List<IRoom> roomList = new ArrayList<>();
                    do {
                        System.out.println("Enter room number");
                        String roomNumber = Utility.readKeyBoard();

                        System.out.println("Enter price per night");
                        Double price = Double.valueOf(Utility.readKeyBoard());

                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        int type = Integer.valueOf(Utility.readKeyBoard(1));
                        RoomType roomType = RoomType.SINGLE;

                        if(type ==2){
                            roomType = RoomType.DOUBLE;
                        }
                        IRoom newRoom = AdminResource.getInstance().createARoom(roomNumber,price,roomType);
                        roomList.add(newRoom);

                        System.out.println("Would you like to add another y/n ");
                        char yn = Utility.readConfirmSelection();
                        if(yn == 'N'){
                            keep= false;
                        }
                    }while (keep);
                    AdminResource.getInstance().addRoom(roomList);
                    for(IRoom room:AdminResource.getInstance().getAllRooms()){
                        System.out.println(room);
                    }
                    break;
                case '5':
                    //I do not understand
                    break;
                case '6':
                    isFlag = false;
                    break;
            }
        }while (isFlag);
    }
}
