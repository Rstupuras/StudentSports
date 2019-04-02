package studentsports.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class TrainerMenu {
    private Stage trainerMenu;
    private ButtonHandler buttonHandler;
    private TableView trainersSportClasses;
    private ObservableList<SportClass> data;
    private FilteredList<SportClass> filteredSportsclassData;
    private Stage add;
    private TextField name;
    private ComboBox day;
    private ComboBox month;
    private ComboBox year;
    private ComboBox capacity;
    private GeneratedItems generatedItems;

    public TrainerMenu(StudentSportsController studentSportsController) {
        this.trainerMenu = new Stage();
        this.buttonHandler = new ButtonHandler(studentSportsController);
        this.generatedItems = new GeneratedItems();

    }

    public void showStage(Trainer trainer) {

        Trainer loggedInTrainer = trainer;
        VBox hBox = new VBox();
        trainersSportClasses = new TableView();
        TableColumn nameCol = new TableColumn("Name");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn trainersColum = new TableColumn("Trainers");
        TableColumn participantsColum = new TableColumn("Participants");
        nameCol.setCellValueFactory(new PropertyValueFactory<SportClass, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<SportClass, Date>("date"));
        participantsColum.setCellValueFactory(new PropertyValueFactory<SportClass, String>("participants"));
        trainersColum.setCellValueFactory(new PropertyValueFactory<SportClass, TrainerList>("trainers"));
        trainersSportClasses.getColumns().addAll(nameCol, dateCol, trainersColum, participantsColum);
        GridPane gridpaneTop = new GridPane();
        GridPane gridPaneBottom = new GridPane();
        gridpaneTop.setAlignment(Pos.TOP_LEFT);
        Button logOut = new Button("Log out");
        logOut.setOnAction(event -> {
            try {
                buttonHandler.logOut();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        Button createSportsClass = new Button("Create sports class");
        createSportsClass.setOnAction(event -> buttonHandler.addSportClass(this));
        Button getAllSportsClasses = new Button("Get All Sports Classes");
        getAllSportsClasses.setOnAction(event -> {
            try {
                buttonHandler.setSportsClassItems();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        Button getOnlyMyClasses = new Button("Get my sports classes");
        getOnlyMyClasses.setOnAction(event -> {
            try {
                buttonHandler.setTrainerItems();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        Button removeMeFromSportsClass = new Button("Remove me");
        removeMeFromSportsClass.setOnAction(event -> {
            try {
                buttonHandler.removeTrainer();
            }  catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sports class has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select sports class to remove trainer");
                alert.showAndWait();
            }
        });
        Button addMeToSportsClass = new Button("Add me");
        addMeToSportsClass.setOnAction(event -> {
            try {
                buttonHandler.addTrainer();
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sports class has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select sports class to add trainer");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Trainer already exists in sports class");
                alert.setHeaderText(null);
                alert.setContentText("Trainer already exists in sports class");
                alert.showAndWait();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        Button deleteSportsClass = new Button("Delete sports class");
        deleteSportsClass.setOnAction(event -> {
            try {
                if (getSelectedSportsClass()==null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sports class has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select sports class to delete");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Do you really want to delete this sports class?");
                    alert.setContentText("Do you really want to delete this sports class?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        buttonHandler.deleteSportsClass();
                    }
                    else
                    {
                        alert.close();
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        trainersSportClasses.setMinSize(600, 350);
        trainersSportClasses.setMaxSize(600, 350);
        gridpaneTop.setMinSize(300, 100);
        Label labelFirstName = new Label("First name: ");
        Label labelLastName = new Label("Last name: ");
        Label labelUserName = new Label("User name: ");
        Text name = new Text(loggedInTrainer.getFirstName());
        Text lastName = new Text(loggedInTrainer.getLastName());
        Text userName = new Text(loggedInTrainer.getUserName());
        hBox.getChildren().addAll(gridpaneTop, trainersSportClasses, gridPaneBottom);
        gridPaneBottom.getChildren().addAll(getAllSportsClasses, getOnlyMyClasses, logOut);
        gridPaneBottom.setPadding(new Insets(5, 5, 5, 5));
        gridPaneBottom.setHgap(5);
        gridPaneBottom.setVgap(5);
        GridPane.setConstraints(getAllSportsClasses, 0, 0);
        GridPane.setConstraints(getOnlyMyClasses, 1, 0);
        GridPane.setConstraints(logOut, 2, 0);
        gridpaneTop.setVgap(5);
        gridpaneTop.setHgap(5);
        gridpaneTop.setPadding(new Insets(10, 10, 10, 10));
        gridpaneTop.getChildren().addAll(name, lastName, labelFirstName, labelLastName, labelUserName, userName, createSportsClass, removeMeFromSportsClass, addMeToSportsClass,deleteSportsClass);
        GridPane.setConstraints(labelFirstName, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(labelLastName, 0, 1);
        GridPane.setConstraints(lastName, 1, 1);
        GridPane.setConstraints(labelUserName, 0, 2);
        GridPane.setConstraints(userName, 1, 2);
        GridPane.setConstraints(createSportsClass, 3, 2);
        GridPane.setConstraints(removeMeFromSportsClass, 4, 2);
        GridPane.setConstraints(addMeToSportsClass, 5, 2);
        GridPane.setConstraints(deleteSportsClass,6,2);
        Scene scene = new Scene(hBox,600,490);
        trainerMenu.setTitle("Student sports");
        trainerMenu.setScene(scene);
        trainerMenu.show();
    }

    public void setTrainersSportClassesItems(SportClassList sportClasslist, Trainer trainer) {
        data = FXCollections.observableArrayList();
        TrainerList trainerList = null;
        for (int i = 0; i < sportClasslist.size(); i++) {
            trainerList = sportClasslist.getSportClassByNumber(i).getTrainers();
            if (trainerList.getTrainerByUserNameAndPassword(trainer.getUserName(),trainer.getPassword()) != null) {
                data.add(sportClasslist.getSportClassByNumber(i));
            }
        }
        this.filteredSportsclassData = new FilteredList<>(data, p -> true);
        trainersSportClasses.setItems(data);
        trainersSportClasses.setItems(filteredSportsclassData);
    }

    public void setSportClassItems(SportClassList sportClass) {
        data = FXCollections.observableArrayList();
        for (int i = 0; i < sportClass.size(); i++) {
            data.add(sportClass.getSportClassByNumber(i));
        }
        this.filteredSportsclassData = new FilteredList<>(data, p -> true);
        trainersSportClasses.setItems(data);
        trainersSportClasses.setItems(filteredSportsclassData);
    }


    public SportClass getSelectedSportsClass() {
        SportClass sportClass = (SportClass) trainersSportClasses.getSelectionModel().getSelectedItem();
        return sportClass;
    }

    public void closeStage() {
        trainerMenu.close();
    }

    public void addSportClass() {
        add = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.TOP_CENTER);
        name = new TextField();
        Button save = new Button("Save");

        save.setPrefSize(70, 30);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(70, 30);
        Pane pane1 = new Pane();
        pane1.minHeightProperty().bind(name.heightProperty());
        Pane pane2 = new Pane();
        pane2.minHeightProperty().bind(name.heightProperty());
        Pane pane3 = new Pane();
        pane3.minHeightProperty().bind(name.heightProperty());
        Pane pane4 = new Pane();
        pane4.minHeightProperty().bind(name.heightProperty());
        Pane pane5 = new Pane();
        pane5.minHeightProperty().bind(name.heightProperty());
        Pane pane6 = new Pane();
        pane6.minHeightProperty().bind(name.heightProperty());
        Label nameLabel = new Label("Name:");
        Label dayLabel = new Label("Day:");
        Label monthLabel = new Label("Month:");
        Label yearLabel = new Label("Year:");
        Label dateLabel = new Label("Date");
        Label capacityLabel = new Label("Capacity");
        capacity = new ComboBox();
        capacity.setItems(generatedItems.capacity());
        this.day = new ComboBox();
        this.day.setItems(generatedItems.arrayDay(31));
        month = new ComboBox();
        month.setItems(generatedItems.arrayMonth());
        year = new ComboBox();
        year.setItems(generatedItems.arrayYear());
        month.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != oldValue) {
                    if (newValue.equals(1) || newValue.equals(3) || newValue.equals(5) || newValue.equals(7) || newValue.equals(8) || newValue.equals(10) || newValue.equals(12)) {
                        day.setItems(generatedItems.arrayDay(31));
                    }
                    if (newValue.equals(2)) {

                        day.setItems(generatedItems.arrayDay(28));

                    } else if (newValue.equals(4) || newValue.equals(6) || newValue.equals(9) || newValue.equals(11)) {
                        day.setItems(generatedItems.arrayDay(30));
                    }
                }

            }
        });


        GridPane.setConstraints(this.name, 2, 0);
        GridPane.setColumnSpan(this.name, 3);
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setColumnSpan(nameLabel, 2);
        GridPane.setConstraints(capacity, 2, 1);
        GridPane.setColumnSpan(capacity, 3);
        GridPane.setConstraints(capacityLabel, 0, 1);
        GridPane.setColumnSpan(capacityLabel, 2);
        GridPane.setConstraints(dateLabel, 3, 2);
        GridPane.setColumnSpan(dateLabel, 5);
        GridPane.setConstraints(dayLabel, 0, 3);
        GridPane.setConstraints(this.day, 1, 3);
        GridPane.setConstraints(monthLabel, 2, 3);
        GridPane.setConstraints(month, 3, 3);
        GridPane.setConstraints(yearLabel, 4, 3);
        GridPane.setConstraints(year, 5, 3);
        GridPane.setConstraints(pane1, 0, 4);
        GridPane.setConstraints(pane2, 0, 5);
        GridPane.setConstraints(pane3, 0, 6);
        GridPane.setConstraints(pane4, 0, 7);
        GridPane.setConstraints(pane5, 0, 8);
        GridPane.setConstraints(pane6, 0, 9);
        GridPane.setConstraints(save, 2, 10);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 4, 10);
        GridPane.setColumnSpan(cancel, 3);

        root.getChildren().addAll(this.name, nameLabel, this.day, month, year, dayLabel, monthLabel, yearLabel, dateLabel, save, cancel, capacity, pane1, pane2, pane3, pane4, pane5, pane6, capacityLabel);

        save.setOnAction(event -> {
            try {

                if (name.getText().isEmpty() == true) {
                    name.setPromptText("Write name here");
                    name.setStyle("-fx-prompt-text-fill: red");
                } else if (capacity.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Capacity has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select capacity");
                    alert.showAndWait();
                } else if (day.getSelectionModel().getSelectedItem() == null || month.getSelectionModel().getSelectedItem() == null || year.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Date has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select date");
                    alert.showAndWait();
                } else {
                    buttonHandler.saveAddedSportClass(add);
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Sports class already exists");
                alert.showAndWait();
            }

        });


        cancel.setOnAction(event -> buttonHandler.stageClose(add));

        Scene scene = new Scene(root, 400, 400);
        add.setScene(scene);
        add.initModality(Modality.APPLICATION_MODAL);
        add.setResizable(true);
        add.showAndWait();
    }

    public SportClass getNewSportClass() {
        Integer capacityForNewSportsClass = (Integer) capacity.getSelectionModel().getSelectedItem();
        SportClass newSportClass = new SportClass(name.getText(), capacityForNewSportsClass, null);
        Integer dayForNewSportsClass = (Integer) day.getSelectionModel().getSelectedItem();
        Integer monthForNewMember = (Integer) month.getSelectionModel().getSelectedItem();
        Integer yearForNewMember = (Integer) year.getSelectionModel().getSelectedItem();
        if (dayForNewSportsClass != null && monthForNewMember != null && yearForNewMember != null) {
            Date date = new Date(dayForNewSportsClass, monthForNewMember, yearForNewMember);
            newSportClass.setDate(date);
        }
        return newSportClass;
    }
}
