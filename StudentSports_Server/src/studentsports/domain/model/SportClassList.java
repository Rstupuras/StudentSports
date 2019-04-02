package studentsports.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SportClassList implements Serializable {
    public ArrayList<SportClass> sportClasses;

    public SportClassList() {
        sportClasses = new ArrayList<>();
    }

    public void addSportClass(SportClass sportClass) {
        sportClasses.add(sportClass);
    }

    public void deleteSportClass(SportClass sportClass) {

        sportClasses.remove(sportClass);
    }

    public SportClass getClassByName(String name) {
        for (int i = 0; i < sportClasses.size(); i++) {
            if (sportClasses.get(i).getName().equalsIgnoreCase(name)) {
                return sportClasses.get(i);
            }
        }
        return null;
    }


    public String toString() {
        String s = "";
        for (int i = 0; i < sportClasses.size(); i++) {
            if (i == sportClasses.size() - 1) {
                s += sportClasses.get(i);
            } else {
                s += sportClasses.get(i) + "\n";
            }
        }
        return s;
    }

    public int size() {
        return sportClasses.size();
    }

    public SportClass getSportClassByNumber(int number) {
        return sportClasses.get(number);
    }


}
