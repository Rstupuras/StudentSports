package studentsports.view;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonHandler {

    private StudentSportsController controller;

    public ButtonHandler(StudentSportsController controller) {

        this.controller = controller;

    }

    public void logIn() throws SQLException, RemoteException {
        controller.execute("LogIn");
    }

    public void setTrainerItems() throws SQLException, RemoteException {
        controller.execute("LoadSportsClassForTrainer");
    }

    public void setSportsClassItems() throws SQLException, RemoteException {
        controller.execute("LoadAllSportsClassForTrainer");
    }

    public void removeTrainer() throws SQLException, RemoteException {
        controller.execute("RemoveTrainerFromSportsClass");
    }

    public void addTrainer() throws SQLException, RemoteException {
        controller.execute("AddTrainerToSportsClass");
    }

    public void logOut() throws SQLException, RemoteException {
        controller.execute("LogOut");
    }

    public void stageClose(Stage stage) {
        stage.close();
    }

    public void saveAddedSportClass(Stage add) throws SQLException, RemoteException {
        controller.execute("AddSportClass");
        stageClose(add);
    }

    public void addSportClass(TrainerMenu sportClassMenu) {
        sportClassMenu.addSportClass();
    }

    public void deleteSportsClass() throws SQLException, RemoteException {
        controller.execute("DeleteSportsClass");
    }

    public void register(Text text) throws SQLException, RemoteException {
        controller.execute("register");
        completeAction("Registered successfully", text);

    }

    public void getMemberSportClasses() throws SQLException, RemoteException {
        controller.execute("setMembersSportClasses");
    }

    public void getSportClasses() throws SQLException, RemoteException {
        controller.execute("setSportClasses");
    }

    public void saveAddedMember(Stage add) throws SQLException, RemoteException {

        controller.execute("AddMember");
        stageClose(add);

    }

    public void addMember(MainMenu mainMenu) {
        mainMenu.addMember();
    }

    public void unregister(Text text) throws SQLException, RemoteException {
        controller.execute("unregister");
        completeAction("Unregistered successfully",text);
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
}

