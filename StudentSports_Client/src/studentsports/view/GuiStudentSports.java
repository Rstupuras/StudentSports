package studentsports.view;

import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Member;
import studentsports.domain.model.SportClass;
import studentsports.domain.model.SportClassList;
import studentsports.domain.model.Trainer;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class GuiStudentSports implements StudentSportsView {
    private MainMenu mainMenu;
    private MemberMenu memberMenu;
    private TrainerMenu trainerMenu;

    @Override
    public void start(StudentSportsController controller) throws SQLException, RemoteException {
        this.mainMenu = new MainMenu(controller);
        this.trainerMenu = new TrainerMenu(controller);
        this.memberMenu = new MemberMenu(controller);
        showMainMenu();
    }

    @Override
    public String getUserName() {
        return mainMenu.getUserName();
    }

    @Override
    public String getPassword() {
        return mainMenu.getPassword();
    }

    public void showMainMenu() {
        mainMenu.showStage();
    }

    @Override
    public void closeTrainerMenu() {
        trainerMenu.closeStage();
    }

    @Override
    public SportClass getNewSportClass() {
        return trainerMenu.getNewSportClass();
    }

    public boolean isChecked() {
        return mainMenu.isChecked();
    }

    @Override
    public void showMemberMenu(Member member) {
        memberMenu.showStage(member);
    }

    public void closeMainMenu() {
        mainMenu.closeStage();
    }

    @Override
    public SportClass getSelectedSportsClass() {
        return trainerMenu.getSelectedSportsClass();
    }

    @Override
    public void showTrainerMenu(Trainer trainer) {
        trainerMenu.showStage(trainer);
    }

    @Override
    public void setTrainersSportClassesItems(SportClassList sportClasslist, Trainer trainer) {
        trainerMenu.setTrainersSportClassesItems(sportClasslist, trainer);
    }

    public void setSportClassItems(SportClassList sportClassList) {
        memberMenu.setSportClassesItems(sportClassList);
    }


    @Override
    public SportClass getSportClass() {
        return memberMenu.getSportCLass();
    }

    @Override
    public void setMembersSportClasItems(SportClassList sportClassList, Member member) {
        memberMenu.setMembersSportClasses(sportClassList, member);
    }

    @Override
    public Member getNewMember() {
        return mainMenu.getNewMember();
    }

    @Override
    public void setAllSportClassesItemsForTrainer(SportClassList sportClasslist) {
        trainerMenu.setSportClassItems(sportClasslist);
    }

    @Override
    public void wrongLogin() {
        mainMenu.setWrongLogin();
    }
}
