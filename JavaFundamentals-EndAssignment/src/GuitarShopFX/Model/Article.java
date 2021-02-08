package GuitarShopFX.Model;

public class Article {
    private String brand;
    private double price;
    private String model;
    private boolean acoustic;
    private String guitarType;
    private int quantity;
    private String uuid = generateRandomUuid(10);

    public String getModel(){
        return model;
    }
    public boolean isAcoustic(){
        return acoustic;
    }
    public String getBrand(){
        return brand;
    }
    public String getGuitarType(){
        return guitarType;
    }
    public String getUuid(){return uuid;}
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalPrice(){
        return price * quantity;
    }
    public double getPrice(){
        return price;
    }

    public Article(int quantity,String brand,String model ,boolean acoustic, double price, String guitarType){
        this.quantity = quantity;
        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.guitarType = guitarType;
        this.price = price;
    }
    private String generateRandomUuid(int size){
        String words = "!@#%&()-*asdfghjkl1234567890zxcvbnm";
        StringBuilder sb = new StringBuilder(size);

        for(int i = 0; i < size; i++){
           int index = (int)(words.length() * Math.random());
           sb.append(words.charAt(index));
        }
        return sb.toString();
    }
}
