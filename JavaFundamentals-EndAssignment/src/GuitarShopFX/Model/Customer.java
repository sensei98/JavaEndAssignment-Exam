package GuitarShopFX.Model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String phoneNo;
    private String email;
    private List<Order> orders = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();

    public Customer(String firstName, String lastName, String streetAddress, String city, String phoneNo, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getCity() {
        return city;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public String getEmail() {
        return email;
    }
    public String getFullName(){return firstName + " " + lastName;}
    public List<Order> getOrders(){return orders;}
    public List<Article> getArticles(){return articles;}

}
