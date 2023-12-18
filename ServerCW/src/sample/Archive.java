package sample;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.Serializable;
import java.sql.Date;

public class Archive implements Serializable {
    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private int IDbook;
    private String date;
    private String author;
    private int IDadmin;
    private String title;

    public Archive(int IDbook, String date, String author, int IDadmin, String title) {
        this.IDbook = IDbook;
        this.date = date;
        this.author = author;
        this.IDadmin = IDadmin;
        this.title = title;
    }

    public Archive(int ID, int IDbook, String date, String author, int IDadmin, String title) {
        this.IDbook = IDbook;
        this.date = date;
        this.author = author;
        this.IDadmin = IDadmin;
        this.title = title;
    }

    public Archive() {
    }

    public int getIDbook() {
        return IDbook;
    }

    public void setIDbook(int IDbook) {
        this.IDbook = IDbook;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIDadmin() {
        return IDadmin;
    }

    public void setIDadmin(int IDadmin) {
        this.IDadmin = IDadmin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "ID=" + ID +
                ", IDbook=" + IDbook +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", IDadmin=" + IDadmin +
                ", title='" + title + '\'' +
                '}';
    }
}
