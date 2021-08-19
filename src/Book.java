/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 10/08/2021    1.0            Vanhv   
 */

/**
 *
 * @author vanhv
 */
public class Book {

    private String bCode;
    private String title;
    private int quantity;
    private int lended;
    private int price;

    public Book() {
    }

    public Book(String bCode, String title, int quantity, int price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public Book(String bCode, String title, int quantity, int lended, int price) {
        this.bCode = bCode;
        this.title = title;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    public String getbCode() {
        return bCode;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLended() {
        return lended;
    }

    public void setLended(int lended) {
        this.lended = lended;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
//        return "bCode=" + bCode + ", title=" + title + ", quantity=" + quantity + ", lended=" + lended + ", price=" + price ;
        return String.format("%3s     %3s     %3s     %3s     %3s", bCode, title, quantity,lended, price);
    }

}
