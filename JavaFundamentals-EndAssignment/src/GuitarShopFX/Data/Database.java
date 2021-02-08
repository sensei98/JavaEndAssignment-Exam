package GuitarShopFX.Data;

import GuitarShopFX.Model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Customer> customerList = new ArrayList<>();
    private List<Person> personList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<Article> articleList = new ArrayList<>();

    public List<Article> getArticleList() {
        return articleList;
    }
    public List<Customer> getCustomerList() {
        return customerList;
    }
    public List<Person> getPersonList(){return personList;}
    public List<Order> getOrders(){return orderList;}


    public Database(){
        personList.add(new Sales("kaysmith","kaysmith1234", "Smith", "Kay"));
        personList.add(new Manager("philjones","philjones1234", "Phil", "Jones"));

        articleList.add(new Article(3,"Fender","Telecaster",false, 1079.79,"Regular"));
        articleList.add(new Article(10,"Fender","Precision", false, 1300.49,"Bass" ));
        articleList.add(new Article(8, "Simon Patrick", "Pro Flame Maple", true,1290.7,"Regular"));

        customerList.add(new Customer("Wim","Wiltenburg","Stentorstraat 90", "Amsterdam","06-12345678","wim@gmail.com"));
        customerList.add(new Customer("Jack","Traven","Dorpstraat 10", "Arnhem","06-29044032","jack@gmail.com"));
        customerList.add(new Customer("Jenny","Grump","Kikkenstein 37", "Den Haag","06-12345568","jenny@gmail.com"));

        Order order1 = new Order(customerList.get(0),articleList.get(0),LocalDate.parse("2020-08-10"),3);
        orderList.add(order1);
    }

}
