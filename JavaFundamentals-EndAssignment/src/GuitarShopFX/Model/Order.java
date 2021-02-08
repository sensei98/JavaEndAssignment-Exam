package GuitarShopFX.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private Article articles;


    private List<Customer> customerList = new ArrayList<>();
    private List<Article> articleList = new ArrayList<>();

    public List<Customer> getCustomerList(){return customerList;}
    public List<Article> getArticles(){return articleList;}

    private String name;
    private String city;
    private String phoneNo;
    private String email;
    private LocalDate date;
    private int count;
    private int orderID = 100000;

    private String brand;
    private String model;
    private Boolean acoustic;
    private String guitarType;
    private double price;
    private String uuid = "03fas455";
    private int quantity;

    public int getOrderID(){return orderID;}
    public LocalDate getDate(){return date;}
    public String getCustomerName() { return customer.getFullName();}
    public String getCustomerCity() {return customer.getCity();}
    public String getPhoneNo() { return customer.getPhoneNo();}
    public String getEmail() {return customer.getEmail();}
    public int getQuantity() {return quantity;}
    public int getCount(){return count;}
    public double getTotalPrice(){ return price +  articles.getTotalPrice();}

    public String getBrand() { return articles.getBrand();}
    public String getType(){return articles.getGuitarType();}
    public String getModel() {return articles.getModel();}
    public Boolean getAcoustic() {return articles.isAcoustic();}
    public double getPrice() { return articles.getPrice();}
    public String getUuid(){return articles.getUuid();}


    public Customer getCustomer(){return customer;}
    public Article getArticle(){return articles;}

    public Order(Customer customer, Article articles, LocalDate date, int quantity){
        this.date = date;
        this.customer = customer;
        this.articles = articles;
        this.quantity = quantity;

        articleList.add(articles);
        count = articleList.size();
        orderID++;
    }



}
