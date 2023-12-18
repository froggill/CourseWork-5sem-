package sample;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int IDorder;
    private String name;
    private String author;
    private int year;
    private int pages;
    private String material;
    private String format;
    private float price;

    @Override
    public String toString() {
        return "Order{" +
                "IDorder=" + IDorder +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", material='" + material + '\'' +
                ", format='" + format + '\'' +
                ", price=" + price +
                ", admin=" + admin +
                ", date='" + date + '\'' +
                ", number=" + number +
                ", status='" + status + '\'' +
                '}';
    }

    private int admin;
    private Date date;
    private int number;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(String name, String author, int year, int pages, String material, String format, float price, int admin, Date date, int number) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.material = material;
        this.format = format;
        this.price = price;
        this.admin = admin;
        this.date = date;
        this.number = number;
    }

    public Order(int IDorder, String name, String author, int year, int pages, String material, String format, float price, int admin, Date date, String status, int number) {
        this.IDorder = IDorder;
        this.name = name;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.material = material;
        this.format = format;
        this.price = price;
        this.admin = admin;
        this.date = date;
        this.number = number;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getadmin() {
        return admin;
    }

    public void setadmin(int IDadmin) {
        this.admin = IDadmin;
    }

    public int getIDorder() {
        return IDorder;
    }

    public void setIDorder(int IDorder) {
        this.IDorder = IDorder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }
}
