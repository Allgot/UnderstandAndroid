package dev_allgot.understand.doitmission_14;

public class Product {
    public int imageRId;
    public String name;
    public String price;
    public String explanation;

    public Product(int imageRId, String name, String price, String explanation) {
        this.imageRId = imageRId;
        this.name = name;
        this.price = price;
        this.explanation = explanation;
    }

    public Product(String name, String price, String explanation) {
        this.imageRId = -1;
        this.name = name;
        this.price = price;
        this.explanation = explanation;
    }
}
