import javafx.application.Application;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.mediator.StudentSportsModel;
import studentsports.domain.mediator.StudentSportsModelManager;
import studentsports.view.GuiStudentSports;
import studentsports.view.StudentSportsView;

public class ServerMain extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ReadFromFile fromFile = new ReadFromFile();
        fromFile.readFile("connectionData.txt");
        StudentSportsView view = new GuiStudentSports();
        String ip = fromFile.getIp();
        String port = fromFile.getPort();
        String databaseUser = fromFile.getDatabaseUser();
        String databaseUserPassword = fromFile.getDatabaseUserPassword();
        String databaseName = fromFile.getDatabaseName();
        String serverBindName = fromFile.getBindName();
        String registryPort = fromFile.getRegistryPort();
        StudentSportsModel model = new StudentSportsModelManager(databaseUser, databaseUserPassword, ip, databaseName, port, serverBindName,registryPort);
        StudentSportsController member = new StudentSportsController(model, view);
        view.start(member);

    }
}
