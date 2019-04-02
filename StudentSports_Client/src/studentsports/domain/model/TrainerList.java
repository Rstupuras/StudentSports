package studentsports.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TrainerList implements Serializable {
    private ArrayList<Trainer> trainersList;

    public TrainerList() {
        trainersList = new ArrayList<>();
    }

    public void addTrainer(Trainer trainer) {
        trainersList.add(trainer);
    }

    public void delete(Trainer trainer) {
        trainersList.remove(trainer);
    }

    public Trainer getTrainerByFirstName(String firstName) {
        for (int i = 0; i < trainersList.size(); i++) {
            if (trainersList.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                return trainersList.get(i);
            }
        }
        return null;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < trainersList.size(); i++) {
            if (i == trainersList.size() - 1) {
                s += trainersList.get(i).getFirstName() + " " +trainersList.get(i).getLastName();
            } else {
                s += trainersList.get(i).getFirstName() + " " +trainersList.get(i).getLastName() + "\n";
            }
        }
        return s;
    }

    public int size() {
        return trainersList.size();
    }

    public Trainer getTrainerByNumber(int number) {
        return trainersList.get(number);
    }

    public Trainer getTrainerByUserNameAndPassword(String userName,String password)
    {
        Trainer trainer = null;
        for (int i=0;i<trainersList.size();i++)
        {
            if (trainersList.get(i).getUserName().equals(userName))
            {
                if (trainersList.get(i).getPassword().equals(password))
                {
                    trainer= trainersList.get(i);
                }
            }
        }
        return trainer;
    }

}
