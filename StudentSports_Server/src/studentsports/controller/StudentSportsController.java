package studentsports.controller;

import studentsports.domain.mediator.StudentSportsModel;
import studentsports.view.StudentSportsView;

import java.sql.SQLException;

public class StudentSportsController {
    private StudentSportsModel model;
    private StudentSportsView view;

    public StudentSportsController(StudentSportsModel model, StudentSportsView view) {
        this.model = model;
        this.view = view;

    }

    public void execute(String what) throws SQLException {
        switch (what) {
            case "loadUnpaidMembers":
                view.setTableUnpaidMemberItems(model.getMemberList().getAllUnpaidMembers());
                break;
            case "memberMenu":
                view.showMemberMenu();
                break;
            case "LoadMembers":
              view.setTableMemberItems(model.getMemberList());
              break;
            case "editUnpaidMember":
                model.deleteMemberFromList(view.getSelectedUnpaidMember());
                model.addMemberToList(view.getNewUnpaidMember());
                view.setTableUnpaidMemberItems(model.getMemberList().getAllUnpaidMembers());
                break;
            case "editMember":
                model.deleteMemberFromList(view.getSelectedMember());
                model.addMemberToList(view.getNewEditedMember());
                view.setTableMemberItems(model.getMemberList());
                break;
            case "DeleteMember":
                model.deleteMemberFromList(view.getSelectedMember());
                view.setTableMemberItems(model.getMemberList());
                break;
                case "AddMember":
                model.addMemberToList(view.getNewMember());
                view.setTableMemberItems(model.getMemberList());
                break;
            case "trainerMenu":
                view.showTrainerMenu();
                break;
            case "LoadTrainers":
                view.setTableTrainerItems(model.getTrainerList());
                break;
            case "EditTrainer":
                model.deleteTrainerFromList(view.getSelectedTrainer());
                model.addTrainerToList(view.getNewEditedTrainer());
                view.setTableTrainerItems(model.getTrainerList());
                break;
            case "AddTrainer":
                model.addTrainerToList(view.getNewTrainer());
                view.setTableTrainerItems(model.getTrainerList());
                break;
            case "DeleteTrainer":
                model.deleteTrainerFromList(view.getSelectedTrainer());
                view.setTableTrainerItems(model.getTrainerList());
                break;
            case "sportClassMenu":
                view.showSportClassMenu();
                break;
            case "LoadSportClass":
                view.setTableSportClassItems(model.getSportClassList());
                break;
            case "DeleteSportClass":
                model.deleteSportClass(view.getSelectedSportClass());
                view.setTableSportClassItems(model.getSportClassList());
                break;
            case "AddSportClass":
                model.addSportClass(view.getNewSportClass());
                view.setTableSportClassItems(model.getSportClassList());
                break;
            case "ShowTrainersForSportClass":
                view.setTableSportClassTrainerItems(view.getSelectedSportClass());
                view.setAllTrainerItemsForComboBox(model.getTrainerList());
                break;
            case "AddTrainerToSportClass":
                model.addTrainerToSportClass(view.getSelectedSportClass(),view.getSelectedTrainerFromSportClassComboBox());
                view.setTableSportClassTrainerItems(view.getSelectedSportClass());
                break;
            case "DeleteTrainerFromSportClass":
                model.deleteTrainerFromSportClass(view.getSelectedSportClass(),view.getSelectedTrainerFromSportClass());
                view.setTableSportClassTrainerItems(view.getSelectedSportClass());
                break;
            case "EditSportsClass":
                model.deleteSportClass(view.getSelectedSportClass());
                model.addSportClass(view.getNewEditedSportClass());
                view.setTableSportClassItems(model.getSportClassList());
                break;
            case "ShowParticipantsForSportClass":
                view.setTableSportClassMemberItems(view.getSelectedSportClass());
                break;
            case "Refresh":
                view.setTableUnpaidMemberItems(model.getMemberList().getAllUnpaidMembers());
                break;
        }

    }

}
