package studentsports.view;

import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Member;
import studentsports.domain.model.SportClass;
import studentsports.domain.model.SportClassList;
import studentsports.domain.model.Trainer;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface StudentSportsView {
    void start(StudentSportsController controller) throws SQLException, RemoteException;
    String getUserName();
    String getPassword();
    boolean isChecked();
    void showTrainerMenu(Trainer trainer);
    void setTrainersSportClassesItems(SportClassList sportClasslist,Trainer trainer);
    void setAllSportClassesItemsForTrainer(SportClassList sportClasslist);
    void closeMainMenu();
    SportClass getSelectedSportsClass();
    void showMainMenu();
    void closeTrainerMenu();
    SportClass getNewSportClass();
    void showMemberMenu(Member member);
    void setSportClassItems(SportClassList sportClassList);
    SportClass getSportClass();
    void setMembersSportClasItems(SportClassList sportClassList,Member member);
    Member getNewMember();
    void wrongLogin();
}
