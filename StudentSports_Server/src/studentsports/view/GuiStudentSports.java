package studentsports.view;

import studentsports.controller.StudentSportsController;
import studentsports.domain.model.*;

import java.sql.SQLException;

public class GuiStudentSports implements StudentSportsView {
    private StudentSportsController controller;
    private MainMenu mainMenu;
    private MemberMenu memberMenu;
    private TrainersMenu trainersMenu;
    private SportClassMenu sportClassMenu;

    @Override
    public void start(StudentSportsController controller) throws SQLException {
        this.controller = controller;
        this.mainMenu = new MainMenu(controller);
        this.memberMenu = new MemberMenu(controller);
        this.trainersMenu = new TrainersMenu(controller);
        this.sportClassMenu = new SportClassMenu(controller);
        showMainMenu();
    }

    @Override
    public void setTableUnpaidMemberItems(MemberList memberList) {
        mainMenu.setUnpaidMemberItems(memberList);
    }
    @Override
    public void showMemberMenu() throws SQLException {
        controller.execute("LoadMembers");
        memberMenu.showStage();
    }
    @Override
    public void setTableMemberItems(MemberList memberList) {
        memberMenu.setMemberItems(memberList);
    }
    @Override
    public void showMainMenu() throws SQLException {
        controller.execute("loadUnpaidMembers");
        mainMenu.showStage();
    }
    @Override
    public Member getSelectedUnpaidMember() {
        return mainMenu.getSelectedMember();
    }
    @Override
    public Member getNewUnpaidMember()
    {
        return mainMenu.getNewMember();
    }

    @Override
    public Member getSelectedMember() {
        return memberMenu.getSelectedMember();
    }

    @Override
    public Member getNewMember()
    {
        return memberMenu.getNewMember();
    }
    @Override
    public Member getNewEditedMember()
    {
        return memberMenu.getNewEditedMember();
    }
    @Override
    public void showTrainerMenu() throws SQLException {
        controller.execute("LoadTrainers");
        trainersMenu.showStage();

    }
    @Override
    public void setTableTrainerItems(TrainerList trainerList) {
        trainersMenu.setTrainerItems(trainerList);
    }

    @Override
    public Trainer getSelectedTrainer() {
        return trainersMenu.getSelectedTrainer();
    }
    public Trainer getNewEditedTrainer()
    {
        return trainersMenu.getNewEditedTrainer();
    }

    @Override
    public Trainer getNewTrainer() {
        return trainersMenu.getNewTrainer();
    }
    public void showSportClassMenu() throws SQLException {
        controller.execute("LoadSportClass");
        sportClassMenu.showStage();
    }
    @Override
    public void setTableSportClassItems(SportClassList sportClassItems)
    {
        sportClassMenu.setSportClassItems(sportClassItems);
    }

    @Override
    public SportClass getSelectedSportClass() {
        return sportClassMenu.getSelectedSportClass();
    }

    @Override
    public SportClass getNewSportClass() {
        return sportClassMenu.getNewSportClass();
    }

    @Override
    public void setTableSportClassTrainerItems(SportClass sportClassTrainerItems) {
        sportClassMenu.setTrainerItemsForSportClass(sportClassTrainerItems);
    }

    @Override
    public void setAllTrainerItemsForComboBox(TrainerList trainerList) {
        sportClassMenu.setAllTrainerItemsForComboBox(trainerList);
    }

    @Override
    public Trainer getSelectedTrainerFromSportClassComboBox() {
        return sportClassMenu.getSelectedComboBoxItem();
    }
    public Trainer getSelectedTrainerFromSportClass()
    {
        return sportClassMenu.getSelectedTrainerInSportClass();
    }

    @Override
    public SportClass getNewEditedSportClass() {
        return sportClassMenu.getNewEditedSportsClass();
    }

    @Override
    public void setTableSportClassMemberItems(SportClass sportClass) {
        sportClassMenu.setParticipantsItems(sportClass);
    }

}
