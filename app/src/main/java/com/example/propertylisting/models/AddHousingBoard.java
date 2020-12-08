package com.example.propertylisting.models;

public class AddHousingBoard
{
    private String sellerName;
    private String propertyAddress;
    private String city;
    private String sector;
    private String condition;
    private String category;
    private String floor;
    private String number;
    private double price;

    public AddHousingBoard() {
    }

    public AddHousingBoard(String sellerName, String propertyAddress, String city, String sector, String condition, String category,
                           String floor, String number, double price) {
        this.sellerName = sellerName;
        this.propertyAddress = propertyAddress;
        this.city = city;
        this.sector = sector;
        this.condition = condition;
        this.category = category;
        this.floor = floor;
        this.number = number;
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AddHousingBoard{" +
                "sellerName='" + sellerName + '\'' +
                ", propertyAddress='" + propertyAddress + '\'' +
                ", city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", condition='" + condition + '\'' +
                ", category='" + category + '\'' +
                ", floor='" + floor + '\'' +
                ", number='" + number + '\'' +
                ", price=" + price +
                '}';
    }
}
