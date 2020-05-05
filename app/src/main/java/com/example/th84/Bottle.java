package com.example.th84;

public class Bottle {

    private String name;
    private String manufacturer;
    private float size;
    private float price;


    public Bottle(String a, float b, float c){
        name = a;
        size = b;
        price = c;
    }

    // public String toString (String){
   // }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public float getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }
}
