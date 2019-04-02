package studentsports.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable {


    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void set(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    public String toString() {
        String s = "";
        s += day + "/" + month + "/" + year;
        return s;
    }
    public boolean compare() {
        LocalDate currentDate = LocalDate.now();
        LocalDate classDate = LocalDate.of(year,month,day);
        return classDate.isAfter(currentDate);
    }

}
