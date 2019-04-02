import javafx.application.Application;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.mediator.StudentSportsModel;
import studentsports.domain.mediator.StudentSportsModelManager;
import studentsports.view.GuiStudentSports;
import studentsports.view.StudentSportsView;

import java.rmi.RemoteException;

public class ClientMain extends Application {
    public static void main(String[] args) throws RemoteException {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StudentSportsView view = new GuiStudentSports();
        ReadFromFile fromFile = new ReadFromFile();
        fromFile.readFile("connectionData.txt");
        String ip = fromFile.getIp();
        String port = fromFile.getPort();
        String bindedName = fromFile.getBindedName();
        StudentSportsModel model = new StudentSportsModelManager(ip,port,bindedName);
        StudentSportsController member = new StudentSportsController(model, view);
        view.start(member);
    }
}
