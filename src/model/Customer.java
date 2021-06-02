package model;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "firstName: " + firstName +
                ", lastName: " + lastName +
                ", Email: " + email;
    }

    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    public Customer(String firstName, String lastName, String email) {
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }else {
            throw new IllegalArgumentException("Wrong Email format, please create again.");
        }
    }
}
