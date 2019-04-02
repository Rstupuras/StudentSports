package studentsports.controller;

import studentsports.domain.mediator.StudentSportsModel;
import studentsports.domain.model.SportClass;
import studentsports.view.StudentSportsView;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StudentSportsController {
    private StudentSportsModel model;
    private StudentSportsView view;

    public StudentSportsController(StudentSportsModel model, StudentSportsView view) {
        this.model = model;
        this.view = view;

    }

    public void execute(String what) throws SQLException, RemoteException {
        switch (what) {
            case "LogIn":
                if (view.isChecked() == true) {
                    if (model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()) != null) {
                        view.showTrainerMenu(model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                        view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                        view.closeMainMenu();
                        break;
                    } else {
                        view.wrongLogin();
                    }

                } else {
                    if (model.getMemberByUserNameAndPassword(view.getUserName(), view.getPassword()) != null) {
                        view.showMemberMenu(model.getMemberByUserNameAndPassword(view.getUserName(), view.getPassword()));
                        view.setSportClassItems(model.getSportClassList());
                        view.closeMainMenu();
                        break;

                    } else {
                        view.wrongLogin();
                    }
                }
                break;
            case "LogOut":
                view.closeTrainerMenu();
                view.showMainMenu();
            case "LoadAllSportsClassForTrainer":
                view.setAllSportClassesItemsForTrainer(model.getSportClassList());
                break;
            case "LoadSportsClassForTrainer":
                view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "AddTrainerToSportsClass":
                model.addTrainerToSportClass(view.getSelectedSportsClass(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "RemoveTrainerFromSportsClass":
                model.deleteTrainerFromSportClass(view.getSelectedSportsClass(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "AddSportClass":
                SportClass sportClass = view.getNewSportClass();
                model.addSportClass(sportClass);
                model.addTrainerToSportClass(sportClass, model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "DeleteSportsClass":
                model.deleteSportClass(view.getSelectedSportsClass());
                view.setTrainersSportClassesItems(model.getSportClassList(), model.getTrainerByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "register":
                model.addParticipantToSportClass(view.getSportClass(), model.getMemberByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "setMembersSportClasses":
                view.setMembersSportClasItems(model.getSportClassList(), model.getMemberByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
            case "setSportClasses":
                view.setSportClassItems(model.getSportClassList());
                break;
            case "AddMember":
                model.addMemberToList(view.getNewMember());
                break;
            case "unregister":
                model.deleteParticipantFromSportClass(view.getSportClass(), model.getMemberByUserNameAndPassword(view.getUserName(), view.getPassword()));
                break;
        }
    }
}
