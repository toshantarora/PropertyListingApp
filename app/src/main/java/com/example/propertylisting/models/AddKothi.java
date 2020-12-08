package com.example.propertylisting.models;

public class AddKothi
{
    private String sellerName;
    private String propertyAddress;
    private String city;
    private String sector;
    private String condition;
    private String storey;
    private String basement;
    private String forSale;
    private String number;
    private double price;

    public AddKothi() {
    }

    public AddKothi(String sellerName, String propertyAddress, String city, String sector, String condition, String storey,
                    String basement, String forSale, String number, double price) {
        this.sellerName = sellerName;
        this.propertyAddress = propertyAddress;
        this.city = city;
        this.sector = sector;
        this.condition = condition;
        this.storey = storey;
        this.basement = basement;
        this.forSale = forSale;
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

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getForSale() {
        return forSale;
    }

    public void setForSale(String forSale) {
        this.forSale = forSale;
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
        return "AddKothi{" +
                "sellerName='" + sellerName + '\'' +
                ", propertyAddress='" + propertyAddress + '\'' +
                ", city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", condition='" + condition + '\'' +
                ", storey='" + storey + '\'' +
                ", basement='" + basement + '\'' +
                ", forSale='" + forSale + '\'' +
                ", number='" + number + '\'' +
                ", price=" + price +
                '}';
    }
}