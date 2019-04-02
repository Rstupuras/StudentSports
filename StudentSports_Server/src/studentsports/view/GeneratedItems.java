package studentsports.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentsports.domain.model.SportClass;
import studentsports.domain.model.Trainer;

public class GeneratedItems {

    public ObservableList<Integer> arrayDay(int days) {
        ObservableList<Integer> dayss = FXCollections.observableArrayList();

        int count = 1;
        for (int i = 0; i < days; i++) {
            dayss.add(count);
            count++;
        }
        return dayss;

    }

    public ObservableList<Integer> arrayMonth() {
        ObservableList<Integer> month = FXCollections.observableArrayList();

        int count = 1;
        for (int i = 0; i < 12; i++) {
            month.add(count);
            count++;
        }
        return month;

    }

    public ObservableList<Integer> arrayYear() {
        ObservableList<Integer> year = FXCollections.observableArrayList();

        int count = 2018;
        for (int i = 2018; i <= 2019; i++) {
            year.add(count);
            count++;
        }
        return year;

    }
    public ObservableList<String> payedUntill() {
        ObservableList<String> year = FXCollections.observableArrayList();
        final String month = "1 Month";
        final String twoMonth = "2 Months";
        final String threeMonth = "3 Months";
        final String fourMonths = "4 Months";
        final String semester = "Semester";
        year.addAll(month,twoMonth,threeMonth,fourMonths,semester);
        return year;

    }
    public ObservableList<Integer> capacity()
    {
        ObservableList<Integer> capacity = FXCollections.observableArrayList();

        int count = 1;
        for (int i = 1; i <= 60; i++) {
            capacity.add(count);
            count++;
        }
        return capacity;
    }

}
