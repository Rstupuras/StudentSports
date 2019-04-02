package studentsports.view;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Member;
import studentsports.domain.model.SportClass;
import studentsports.domain.model.Trainer;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonHandler {

    private StudentSportsController controller;

    public ButtonHandler(StudentSportsController controller) {

        this.controller = controller;

    }

    public void showTrainersMenu() {
        try {
            controller.execute("trainerMenu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showMembersMenu() throws SQLException {

            controller.execute("memberMenu");
    }

    public void showSportClassMenu() {
        try {
            controller.execute("sportClassMenu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUnpaidMember(FilteredList<Member> filteredUnpaidMemberData, TextField textField) {
        filteredUnpaidMemberData.setPredicate(member -> member.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
    }

    public void doubleClickEditUnpaidMember(TableView tableViewUnpaidMember, MouseEvent event, MainMenu mainMenu) {
        if (event.getClickCount() == 2) {
            if (tableViewUnpaidMember.getSelectionModel().getSelectedItem() != null) {
                mainMenu.editMember(tableViewUnpaidMember.getSelectionModel());
            }
        }
    }

    public void saveEditedUnpaidMember(Stage edit, Text text) throws SQLException {

            controller.execute("editUnpaidMember");
            completeAction("Member edited", text);

        stageClose(edit);
    }


    public void deleteMember(Text text) {
        try {
            controller.execute("DeleteMember");
            completeAction("Member deleted", text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(MemberMenu memberMenu) {
        memberMenu.addMember();
    }

    public void searchMemberByName(FilteredList<Member> filteredMemberData, TextField textField) {
        filteredMemberData.setPredicate(member -> member.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
    }

    public void doubleClickEditMember(TableView tableViewMember, MouseEvent event, MemberMenu memberMenu) {
        if (event.getClickCount() == 2) {
            if (tableViewMember.getSelectionModel().getSelectedItem() != null) {
                memberMenu.editMember(tableViewMember.getSelectionModel());
            }
        }
    }

    public void editMember(Stage edit) throws SQLException {

            controller.execute("editMember");

        stageClose(edit);
    }

    public void saveAddedMember(Stage add, Text text) throws SQLException {

            controller.execute("AddMember");
            completeAction("Member added", text);
            stageClose(add);

    }

    public void stageClose(Stage stage) {
        stage.close();
    }

    public void deleteSportClass(Text text) {
            try {
                controller.execute("DeleteSportClass");
                completeAction("Sport class deleted", text);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void addSportClass(SportClassMenu sportClassMenu) {
        sportClassMenu.addSportClass();
    }

    public void searchSportClassByTrainer(FilteredList<SportClass> filteredSportClass, TextField textField) {
        filteredSportClass.setPredicate(trainer -> trainer.getName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
    }

    public void editSportClass(TableView tableViewSportClass, SportClassMenu sportClassMenu) {
        if (tableViewSportClass.getSelectionModel().getSelectedItem() != null) {
            sportClassMenu.editSportClass(tableViewSportClass.getSelectionModel());
        }
    }

    public void deleteTrainer(Text text) {
        try {
            controller.execute("DeleteTrainer");
            completeAction("Trainer deleted", text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTrainer(TrainersMenu trainersMenu) {
        trainersMenu.addTrainer();
    }

    public void searchTrainerByName(FilteredList<Trainer> filteredtrainerData, TextField textField) {
        filteredtrainerData.setPredicate(trainer -> trainer.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
    }

    public void doubleClickEditTrainer(TableView tableViewTrainer, MouseEvent event, TrainersMenu trainersMenu) {
        if (event.getClickCount() == 2) {
            if (tableViewTrainer.getSelectionModel().getSelectedItem() != null) {
                trainersMenu.editTrainer(tableViewTrainer.getSelectionModel());
            }
        }
    }

    public void saveEditedTrainer(Stage edit, Text text) throws SQLException {

            controller.execute("EditTrainer");
            completeAction("Trainer edited", text);

        stageClose(edit);
    }

    public void saveAddedTrainer(Stage add, Text text) throws SQLException {
            controller.execute("AddTrainer");
            completeAction("Trainer added", text);
            stageClose(add);
    }

    public void saveAddedSportClass(Stage add, Text text) throws SQLException {
        controller.execute("AddSportClass");
        stageClose(add);
        completeAction("Sport class added", text);
    }

    public void completeAction(String whatHappened, Text text) {
        text.setText(whatHappened);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                //Send add members email
                text.setText("");
                timer.cancel();
            }
        }, 3000);
    }

    public void saveEditedSportClass(Stage edit,Text text) throws SQLException {

        controller.execute("EditSportsClass");
        stageClose(edit);
        completeAction("Sport class edited", text);

    }

    public void addTrainerToSportClass(Text text) throws SQLException {
            controller.execute("AddTrainerToSportClass");
            completeAction("Trainer added", text);

    }

    public void removeTrainerFromSportClass(Text text) {
        try {
            controller.execute("DeleteTrainerFromSportClass");
            completeAction("Trainer deleted", text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doubleClickEdtiTrainersInSportClass(MouseEvent event, TableView tableViewSportClass, SportClassMenu sportClassMenu) {
        if (event.getClickCount() == 2) {
            if (tableViewSportClass.getFocusModel().getFocusedCell().getColumn() == 2) {
                try {
                    sportClassMenu.trainersForSportClass();
                    controller.execute("ShowTrainersForSportClass");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (tableViewSportClass.getFocusModel().getFocusedCell().getColumn() ==3)
            {
                try {
                    sportClassMenu.participants();
                    controller.execute("ShowParticipantsForSportClass");
                }
                catch (SQLException e)
                {

                }
            }
        }
    }

    public void searchParticipantByName(FilteredList<Member> filteredMember, TextField textField) {
       filteredMember.setPredicate(member -> member.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));

    }

    public void refresh() throws SQLException {
        controller.execute("Refresh");
    }
}

