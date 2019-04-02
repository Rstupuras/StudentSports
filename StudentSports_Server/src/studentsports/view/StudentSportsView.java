package studentsports.view;

import studentsports.controller.StudentSportsController;
import studentsports.domain.model.*;

import java.sql.SQLException;

public interface StudentSportsView {
    void start(StudentSportsController controller) throws SQLException;
    void setTableUnpaidMemberItems(MemberList memberItems);
    void showMemberMenu() throws SQLException;
    void showMainMenu() throws SQLException;
    void setTableMemberItems(MemberList memberList) throws SQLException;
    Member getSelectedUnpaidMember();
    Member getNewUnpaidMember();
    Member getSelectedMember();
    Member getNewMember();
    Member getNewEditedMember();
    void showTrainerMenu() throws SQLException;
    void setTableTrainerItems(TrainerList trainerList);
    Trainer getSelectedTrainer();
    Trainer getNewEditedTrainer();
    Trainer getNewTrainer();
    void showSportClassMenu() throws SQLException;
    void setTableSportClassItems(SportClassList sportClassItems);
    SportClass getSelectedSportClass();
    SportClass getNewSportClass();
    void setTableSportClassTrainerItems(SportClass sportClassTrainerItems);
    void setAllTrainerItemsForComboBox(TrainerList trainerList);
    Trainer getSelectedTrainerFromSportClassComboBox();
    Trainer getSelectedTrainerFromSportClass();
    SportClass getNewEditedSportClass();
    void setTableSportClassMemberItems(SportClass sportClass);
}
