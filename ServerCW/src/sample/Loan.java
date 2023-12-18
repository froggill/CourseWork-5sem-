package sample;

import java.io.Serializable;
import java.sql.Date;

public class Loan implements Serializable {
    public int getIDloan() {
        return IDloan;
    }

    public void setIDloan(int IDloan) {
        this.IDloan = IDloan;
    }

    private int IDloan;

    @Override
    public String toString() {
        return "Loan{" +
                "IDloan=" + IDloan +
                ", IDuser=" + IDuser +
                ", IDbook=" + IDbook +
                ", term=" + term +
                ", date=" + date +
                '}';
    }

    private int IDuser;
    private int IDbook;
    private int term;
    private Date date;

    public Loan(int IDuser, int IDbook, int term, Date date) {
        this.IDuser = IDuser;
        this.IDbook = IDbook;
        this.term = term;
        this.date = date;
    }

    public Loan(int IDloan, int IDuser, int IDbook, int term, Date date) {
        this.IDloan = IDloan;
        this.IDuser = IDuser;
        this.IDbook = IDbook;
        this.term = term;
        this.date = date;
    }

    public Loan() {
    }

    public int getIDuser() {
        return IDuser;
    }

    public void setIDuser(int IDuser) {
        this.IDuser = IDuser;
    }

    public int getIDbook() {
        return IDbook;
    }

    public void setIDbook(int IDbook) {
        this.IDbook = IDbook;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
