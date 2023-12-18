package sample;

import java.io.Serializable;

public class Book implements Serializable {
    private int ID;
    private String nameb;
    private String author;
    private int year;
    private int pages;
    private String format;

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", nameb='" + nameb + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", format='" + format + '\'' +
                ", material='" + material + '\'' +
                ", coefficient=" + coefficient +
                ", status='" + status + '\'' +
                ", number=" + number +
                '}';
    }

    private String material;
    private float coefficient;
    private String status;
    private int number;

    public Book(String nameb, String author, int year, int pages, String format, String material, float coefficient, String status, int number) {
        this.nameb = nameb;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.format = format;
        this.material = material;
        this.coefficient = coefficient;
        this.status = status;
        this.number = number;
    }

    public Book(String nameb, String author, int year, int pages, String format, String material, float coefficient, String status) {
        this.nameb = nameb;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.format = format;
        this.material = material;
        this.coefficient = coefficient;
        this.status = status;
    }

    public Book() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameb() {
        return nameb;
    }

    public void setNameb(String nameb) {
        this.nameb = nameb;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
