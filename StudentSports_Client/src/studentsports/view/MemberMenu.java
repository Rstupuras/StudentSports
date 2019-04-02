package studentsports.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MemberMenu {
    private Stage memberMenu;
    private TableView membersSportClassesTableView;
    private ObservableList<SportClass> sportClassData;
    private FilteredList<SportClass> filteredSportsclassData;
    private ButtonHandler buttonHandler;
    private Text succesfulOperation;

    public MemberMenu(StudentSportsController studentSportsController) {
        this.memberMenu = new Stage();
        this.memberMenu = memberMenu();
        this.buttonHandler = new ButtonHandler(studentSportsController);
    }

    private Stage memberMenu() {

        return memberMenu;
    }

    public void showStage(Member member) {
        succesfulOperation = new Text();
        succesfulOperation.setStyle("-fx-font: 15 arial;");
        AtomicBoolean isYourClassesShowing = new AtomicBoolean(false);
        Member member1 = member;
        VBox hBox = new VBox();
        membersSportClassesTableView = new TableView();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn trainersColum = new TableColumn("Trainers");
        TableColumn participantsColum = new TableColumn("Participants");
        nameCol.setCellValueFactory(new PropertyValueFactory<SportClass, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<SportClass, Date>("date"));
        participantsColum.setCellValueFactory(new PropertyValueFactory<SportClass, String>("participants"));
        trainersColum.setCellValueFactory(new PropertyValueFactory<SportClass, TrainerList>("trainers"));
        membersSportClassesTableView.getColumns().addAll(nameCol, dateCol, trainersColum, participantsColum);
        Button registerButton = new Button("Register");
        Button loadAllClassesButton = new Button("Show all classes");
        Button loadRegisteredClassesButton = new Button("Show your classes");
        Button unregisterButton = new Button("Unregister");
        registerButton.setOnMouseClicked(event -> {

            try {
                if (membersSportClassesTableView.getItems().isEmpty()) {

                } else if (membersSportClassesTableView.getSelectionModel().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sport class has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select sport class to register");
                    alert.showAndWait();

                } else {
                    if (member.isIspaid()) {
                        SportClass temporarySportClass = (SportClass) membersSportClassesTableView.getSelectionModel().getSelectedItem();
                        if (temporarySportClass.hasAParticipant(member)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Sport class already registered");
                            alert.setHeaderText(null);
                            alert.setContentText("You are already registered to this class");
                            alert.showAndWait();
                        } else {
                            buttonHandler.register(succesfulOperation);
                            if (!isYourClassesShowing.get()) {
                                buttonHandler.getSportClasses();
                            } else {
                                buttonHandler.getMemberSportClasses();
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setContentText("You have not paid for the membership");
                        alert.showAndWait();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Sports class is full");
                alert.showAndWait();
            }

        });
        loadRegisteredClassesButton.setOnMouseClicked(event -> {
            try {
                buttonHandler.getMemberSportClasses();
                isYourClassesShowing.set(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        loadAllClassesButton.setOnMouseClicked(event -> {
            try {
                buttonHandler.getSportClasses();
                isYourClassesShowing.set(false);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        unregisterButton.setOnMouseClicked(event -> {
            try {
                if (membersSportClassesTableView.getItems().isEmpty()) {

                } else if (membersSportClassesTableView.getSelectionModel().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sport class has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select sport class to unregister");
                    alert.showAndWait();
                } else {
                    SportClass temporarySportClass = (SportClass) membersSportClassesTableView.getSelectionModel().getSelectedItem();
                    if (!(temporarySportClass.hasAParticipant(member))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sport class not registered");
                        alert.setHeaderText(null);
                        alert.setContentText("You are not registered to this class");
                        alert.showAndWait();
                    } else {
                        buttonHandler.unregister(succesfulOperation);
                        if (!isYourClassesShowing.get()) {
                            buttonHandler.getSportClasses();
                        } else {
                            buttonHandler.getMemberSportClasses();
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        GridPane gridpaneTop = new GridPane();
        HBox hBoxMiddle = new HBox();
        HBox hBoxBottom = new HBox();
        membersSportClassesTableView.setMinSize(600, 200);
        gridpaneTop.setMinSize(600, 100);
        Text memberName = new Text("Name: " + member1.getFirstName());
        Text memberLastName = new Text("Last name: " + member1.getLastName());
        Text memberUserName = new Text("User name: " + member1.getUserName());
        VBox vBoxTop = new VBox();
        vBoxTop.setSpacing(10);
        vBoxTop.setPadding(new Insets(10, 0, 0, 10));
        vBoxTop.getChildren().addAll(memberName, memberLastName, memberUserName);
        HBox hboxTop = new HBox();
        VBox forSuccesfulOperation = new VBox();
        forSuccesfulOperation.setPadding(new Insets(40, 0, 0, 0));
        forSuccesfulOperation.getChildren().add(succesfulOperation);
        hboxTop.getChildren().addAll(vBoxTop, forSuccesfulOperation);
        hboxTop.setSpacing(300);
        gridpaneTop.getChildren().addAll(hboxTop);
        hBoxMiddle.getChildren().add(membersSportClassesTableView);
        Scene scene = new Scene(hBox, 600, 500);
        registerButton.setMinSize(125, 25);
        loadAllClassesButton.setMinSize(125, 25);
        loadRegisteredClassesButton.setMinSize(125, 25);
        unregisterButton.setMinSize(125, 25);
        hBoxBottom.getChildren().addAll(loadRegisteredClassesButton, loadAllClassesButton, unregisterButton, registerButton);
        hBoxBottom.setPadding(new Insets(0, 0, 10, 20));
        hBox.setSpacing(20);
        hBoxBottom.setSpacing(20);
        hBox.getChildren().addAll(gridpaneTop, hBoxMiddle, hBoxBottom);
        memberMenu.setTitle("Student sports");
        memberMenu.setScene(scene);
        memberMenu.show();
    }

    public void setSportClassesItems(SportClassList sportClasslist) {
        sportClassData = FXCollections.observableArrayList();

        for (int i = 0; i < sportClasslist.size(); i++) {
            if (sportClasslist.getSportClassByNumber(i).getDate().compare()) {
                sportClassData.add(sportClasslist.getSportClassByNumber(i));
            }

        }
        this.filteredSportsclassData = new FilteredList<>(sportClassData, p -> true);
        membersSportClassesTableView.setItems(sportClassData);
        membersSportClassesTableView.setItems(filteredSportsclassData);


    }


    public SportClass getSportCLass() {
        return (SportClass) membersSportClassesTableView.getSelectionModel().getSelectedItem();
    }

    public void setMembersSportClasses(SportClassList sportClassList, Member member) {
        sportClassData = FXCollections.observableArrayList();
        for (int i = 0; i < sportClassList.size(); i++) {
            if (sportClassList.getSportClassByNumber(i).hasAParticipant(member) && sportClassList.getSportClassByNumber(i).getDate().compare()) {
                sportClassData.add(sportClassList.getSportClassByNumber(i));
            }

        }
        this.filteredSportsclassData = new FilteredList<>(sportClassData, p -> true);
        membersSportClassesTableView.setItems(sportClassData);
        membersSportClassesTableView.setItems(filteredSportsclassData);
    }


}

