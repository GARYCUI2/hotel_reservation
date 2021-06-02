import api.HotelResource;
import model.IRoom;
import model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

public class MainMenu {

    public static void mainMenu() throws ParseException {
        boolean loopFlag = true;
        System.out.println("Welcome to the Hotel Reservation Application\n");


        do{ System.out.println("-------------------------------------------");
            System.out.println("1.Find and reserve a room");
            System.out.println("2.See my reservation");
            System.out.println("3.Create an account");
            System.out.println("4.Admin");
            System.out.println("5.Exit");
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option");

            char key = Utility.readMenuSelection(false);
            System.out.println();

            switch (key){
                case '1':
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                    System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                    String checkIn = getAndValidateDate();
                    Date checkInDate = dateFormat.parse(checkIn);

                    System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
                    String checkOut = getAndValidateDate();
                    Date checkOutDate = dateFormat.parse(checkOut);

                    for(IRoom r:HotelResource.getInstance().findARoom(checkInDate,checkOutDate)){
                        System.out.println(r);
                    }

                    System.out.println("Would you like to book a room? y/n");
                    char x = Utility.readConfirmSelection();
                    if(x == 'N'){
                        break;
                    }
                    System.out.println("Do you have an account with us? y/n");
                    char y = Utility.readConfirmSelection();
                    if(y == 'N'){
                        addCustomer();
                    }
                    System.out.println("Enter Email format: name@domain.com");
                    String strEmail = getAndValidateEmail();
                    while(HotelResource.getInstance().getCustomer(strEmail) == null){
                        System.out.println("No account found, please type again");
                        strEmail = getAndValidateEmail();
                    }

                    System.out.println("What room number would you like to reserve");
                    IRoom room =HotelResource.getInstance().getRoom(Utility.readKeyBoard());

                    Reservation res = HotelResource.getInstance().bookARoom(strEmail,room,checkInDate,checkOutDate);
                    System.out.println(res);

                    break;
                case '2':
                    System.out.println("Enter Email format: name@domain.com");
                    String email = getAndValidateEmail();

                    Collection<Reservation> reservationSet = HotelResource.getInstance().getCustomersReservations(email);
                    if(reservationSet.isEmpty()){
                        System.out.println("No reservation found.");
                        break;
                    }
                    for(Reservation r : reservationSet){
                        System.out.println(r);
                    }
                    break;
                case '3':
                    addCustomer();
                    break;
                case '4':
                    AdminMenu.admin();
                    break;
                case '5':
                    System.out.println("Exit(y/n):");
                    char yn = Utility.readConfirmSelection();
                    if(yn == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }while (loopFlag);
    }

    public static void addCustomer() {
        System.out.println("Enter Email format: name@domain.com");
        String newEmail = getAndValidateEmail();

        System.out.println("First Name");
        String firstName = Utility.readKeyBoard();
        System.out.println("Last Name");
        String lastName = Utility.readKeyBoard();

        try{
            HotelResource.getInstance().createACustomer(newEmail, firstName, lastName);
            System.out.println("Welcome to the Hotel Reservation Application");
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static String getAndValidateEmail() {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        String email = Utility.readKeyBoard(pattern);
        return email;
    }

    public static String getAndValidateDate() {
        String regex = "^(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02/(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))/[0-9]{4}$"; // MM/dd/yyyy
        Pattern pattern = Pattern.compile(regex);
        String date = Utility.readKeyBoard(pattern);
        return date;
    }

}
