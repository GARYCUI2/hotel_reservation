package service;
import model.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private Collection<Customer> customers = new ArrayList<>();
    private Map<String, Customer> mapOfCustomer = new HashMap<String,Customer>();

    //static reference
    private static CustomerService customerService = null;
    private CustomerService(){};
    public static CustomerService getInstance(){
        if (null == customerService){
            customerService = new CustomerService();
        }
        return customerService;
    }


    public void addCustomer(String email,String firstName,String lastName){
        Customer newCustomer = new Customer(firstName, lastName, email);
        this.customers.add(newCustomer);
        this.mapOfCustomer.put(newCustomer.getEmail(), newCustomer);
    }

   public Customer getCustomer(String customerEmail){
        if(isFind(customerEmail) ){
            return mapOfCustomer.get(customerEmail);
        }else {
            return null;
        }
    }

    private boolean isFind(String customerEmail){

        return mapOfCustomer.containsKey(customerEmail);
    }

    public Collection<Customer> getAllCustomer(){

        return customers;
    }
}
