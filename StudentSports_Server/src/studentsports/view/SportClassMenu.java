package studentsports.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.*;

import java.sql.SQLException;

public class SportClassMenu {
    private Stage sportClassMenu;
    private FilteredList<SportClass> filteredSportClass;
    private FilteredList<Trainer> filteredTrainer;
    private FilteredList<Member> filteredMember;
    private TableView tableViewSportClass;
    private ObservableList<SportClass> dataSportClass;
    private ObservableList<Trainer> dataTrainer;
    private ObservableList<Member> dataMember;
    private Stage edit;
    private TextField name;
    private ComboBox day;
    private ComboBox month;
    private ComboBox year;
    private ComboBox capacity;
    private GeneratedItems generatedItems;
    private Stage add;
    private ButtonHandler buttonHandler;
    private Stage trainers;
    private TableView tableViewTrainers;
    private Stage participants;
    private TableView tableViewParticipants;
    private ComboBox trainersComboBox;
    private Text successfulOperation;

    public SportClassMenu(StudentSportsController controller) {
        this.tableViewSportClass = new TableView();
        this.sportClassMenu = new Stage();
        this.sportClassMenu = sportClassMenu();
        this.generatedItems = new GeneratedItems();
        this.buttonHandler = new ButtonHandler(controller);


    }

    public void setSportClassItems(SportClassList sportClass) {
        dataSportClass = FXCollections.observableArrayList();
        for (int i = 0; i < sportClass.size(); i++) {
            dataSportClass.add(sportClass.getSportClassByNumber(i));
        }
        this.filteredSportClass = new FilteredList<>(dataSportClass, p -> true);
        tableViewSportClass.setItems(dataSportClass);
        tableViewSportClass.setItems(filteredSportClass);
    }

    public Stage sportClassMenu() {
        tableViewSportClass.setEditable(true);
        Image image = new Image("images/logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        sportClassMenu.setResizable(false);
        VBox vBox = new VBox();
        HBox forImage = new HBox();
        forImage.setPadding(new Insets(0, 420, 0, 0));
        forImage.setAlignment(Pos.TOP_LEFT);
        forImage.getChildren().add(imageView);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_RIGHT);
        Button editSportClassButoon = new Button("Edit");
        editSportClassButoon.setPrefSize(100, 40);
        Button addSportClassButton = new Button("Add");
        addSportClassButton.setPrefSize(100, 40);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(40);
        Button deleteSportClassButon = new Button("Delete");
        deleteSportClassButon.setPrefSize(100, 40);
        hBox.getChildren().addAll(forImage, editSportClassButoon, addSportClassButton, deleteSportClassButon);
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setPadding(new Insets(20, 20, 20, 20));

        deleteSportClassButon.setOnAction(event -> {
            if (getSelectedSportClass()==null)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sports class has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select sports class to delete");
                alert.showAndWait();
            }
            else {
                buttonHandler.deleteSportClass(successfulOperation);

            }

        });
        addSportClassButton.setOnAction(event -> buttonHandler.addSportClass(this));
        editSportClassButoon.setOnAction(event -> {
            if (getSelectedSportClass()==null)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sports class has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select sports class to edit");
                alert.showAndWait();
            }
            else {
                buttonHandler.editSportClass(tableViewSportClass,this);
            }
                });



        TextField textField = new TextField();
        textField.setPromptText("Search by name");
        successfulOperation = new Text();
        successfulOperation.setStyle("-fx-font: 20 arial;");
        hBox2.getChildren().addAll(successfulOperation, textField);
        hBox2.setSpacing(650);
        textField.setOnKeyReleased(event -> {
            buttonHandler.searchSportClassByTrainer(filteredSportClass, textField);
        });


        TableColumn nameCol = new TableColumn("Name");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn trainersColum = new TableColumn("Trainers");
        TableColumn participantsColum = new TableColumn("Participants");
        tableViewSportClass.getSelectionModel().setCellSelectionEnabled(true);
        tableViewSportClass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonHandler.doubleClickEdtiTrainersInSportClass(event, tableViewSportClass, SportClassMenu.this);

            }
        });

        nameCol.setCellValueFactory(new PropertyValueFactory<SportClass, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<SportClass, Date>("date"));
        participantsColum.setCellValueFactory(new PropertyValueFactory<SportClass, String>("participants"));
        trainersColum.setCellValueFactory(new PropertyValueFactory<SportClass, TrainerList>("trainers"));
        tableViewSportClass.getColumns().addAll(nameCol, dateCol, trainersColum, participantsColum);
        tableViewSportClass.setMinHeight(480);
        tableViewSportClass.setMinWidth(1010);
        hBox1.getChildren().add(tableViewSportClass);

        vBox.getChildren().addAll(hBox, hBox1, hBox2);

        Scene scene = new Scene(vBox, 1000, 650);
        sportClassMenu.setScene(scene);
        return sportClassMenu;

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
                        SportClassMenu.this.day.setItems(generatedItems.arrayDay(31));
                    }
                    if (newValue.equals(2)) {

                        SportClassMenu.this.day.setItems(generatedItems.arrayDay(28));

                    } else if (newValue.equals(4) || newValue.equals(6) || newValue.equals(9) || newValue.equals(11)) {
                        SportClassMenu.this.day.setItems(generatedItems.arrayDay(30));
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
                }
                else if (capacity.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Capacity has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select capacity");
                    alert.showAndWait();
                }
                else if (day.getSelectionModel().getSelectedItem() == null || month.getSelectionModel().getSelectedItem() == null || year.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Date has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select date");
                    alert.showAndWait();
                }
                else{
                    buttonHandler.saveAddedSportClass(add, successfulOperation);
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

    public void editSportClass(SelectionModel selectionModel) {
        SportClass sportClass = (SportClass) selectionModel.getSelectedItem();
        edit = new Stage();
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
        Label label = new Label("Name:");
        Label label1 = new Label("Capacity:");
        Label label4 = new Label("Day:");
        Label label5 = new Label("Month:");
        Label label6 = new Label("Year:");
        Label payment = new Label("Date");
        day = new ComboBox();
        day.setItems(generatedItems.arrayDay(31));
        month = new ComboBox();
        month.setItems(generatedItems.arrayMonth());
        year = new ComboBox();
        year.setItems(generatedItems.arrayYear());
        capacity = new ComboBox();
        capacity.setItems(generatedItems.capacity());
        if (sportClass.getDate() != null) {
            day.setValue(sportClass.getDate().getDay());
            month.setValue(sportClass.getDate().getMonth());
            year.setValue(sportClass.getDate().getYear());

        }
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


        GridPane.setConstraints(name, 3, 0);
        GridPane.setColumnSpan(name, 3);
        GridPane.setConstraints(capacity, 3, 1);
        GridPane.setColumnSpan(capacity, 3);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 3);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setColumnSpan(label1, 3);
        GridPane.setConstraints(payment, 2, 4);
        GridPane.setColumnSpan(payment, 2);
        GridPane.setConstraints(label4, 0, 5);
        GridPane.setConstraints(day, 1, 5);
        GridPane.setConstraints(label5, 2, 5);
        GridPane.setConstraints(month, 3, 5);
        GridPane.setConstraints(label6, 4, 5);
        GridPane.setConstraints(year, 5, 5);
        GridPane.setConstraints(pane1, 0, 7);
        GridPane.setConstraints(pane2, 0, 8);
        GridPane.setConstraints(save, 0, 9);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 5, 9);
        GridPane.setColumnSpan(cancel, 2);

        root.getChildren().addAll(name, capacity, label, label1, day, month, year, label4, label5, label6, payment, save, cancel, pane1, pane2);

        name.setText(sportClass.getName());
        capacity.setValue(sportClass.getCapacity());
        save.setOnAction(event -> {
            try {
                if (name.getText().isEmpty() == true) {
                    name.setPromptText("Write name here");
                    name.setStyle("-fx-prompt-text-fill: red");
                }
                else if (capacity.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Capacity has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select capacity");
                    alert.showAndWait();
                }
                else if (day.getSelectionModel().getSelectedItem() == null || month.getSelectionModel().getSelectedItem() == null || year.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Date has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select date");
                    alert.showAndWait();
                }
                else{

                buttonHandler.saveEditedSportClass(edit, successfulOperation);
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        cancel.setOnAction(event -> buttonHandler.stageClose(edit));

        Scene scene = new Scene(root, 400, 400);
        edit.setScene(scene);
        edit.initModality(Modality.APPLICATION_MODAL);
        edit.showAndWait();
    }

    public void showStage() {
        sportClassMenu.showAndWait();
    }

    public SportClass getSelectedSportClass() {
        SportClass sportClass = (SportClass) tableViewSportClass.getSelectionModel().getSelectedItem();
        return sportClass;
    }

    public SportClass getNewSportClass() {
        Integer capacityForNewMember = (Integer) capacity.getSelectionModel().getSelectedItem();
        SportClass newSportClass = new SportClass(name.getText(), capacityForNewMember, null);
        Integer dayForNewMember = (Integer) day.getSelectionModel().getSelectedItem();
        Integer monthForNewMember = (Integer) month.getSelectionModel().getSelectedItem();
        Integer yearForNewMember = (Integer) year.getSelectionModel().getSelectedItem();
        if (dayForNewMember != null && monthForNewMember != null && yearForNewMember != null) {
            Date date = new Date(dayForNewMember, monthForNewMember, yearForNewMember);
            newSportClass.setDate(date);
        }
        return newSportClass;
    }

    public void trainersForSportClass() {
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox, 440, 300);
        trainers = new Stage();
        trainersComboBox = new ComboBox();
        tableViewTrainers = new TableView();
        trainersComboBox.setMinWidth(170);
        trainersComboBox.setMinHeight(30);
        trainersComboBox.setMaxHeight(30);
        trainersComboBox.setMaxWidth(170);
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        firstName.setCellValueFactory(new PropertyValueFactory<Trainer, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Trainer, String>("lastName"));
        tableViewTrainers.getColumns().addAll(firstName, lastName);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button add = new Button("Add");
        add.setOnAction(event -> {
            try {
                if (getSelectedComboBoxItem()==null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Trainer has not been selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select trainer to add");
                    alert.showAndWait();
                }
                else
                {
                    buttonHandler.addTrainerToSportClass(successfulOperation);
                    tableViewTrainers.refresh();
                }

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Trainer is already in sports class");
                alert.showAndWait();
            }
        });
        Button remove = new Button("Remove");
        remove.setOnAction(event -> {
            if (getSelectedTrainerInSportClass()==null)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trainer has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select trainer to delete");
                alert.showAndWait();
            }
            else {
                buttonHandler.removeTrainerFromSportClass(successfulOperation);
            }

        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(event -> buttonHandler.stageClose(trainers));
        gridPane.getChildren().addAll(add, remove, cancel, trainersComboBox);
        GridPane.setConstraints(add, 0, 0);
        GridPane.setConstraints(remove, 1, 0);
        GridPane.setConstraints(cancel, 2, 0);
        GridPane.setConstraints(trainersComboBox, 0, 1);
        GridPane.setColumnSpan(trainersComboBox,3);
        hBox.getChildren().addAll(tableViewTrainers, gridPane);
        trainers.setScene(scene);
        trainers.initModality(Modality.APPLICATION_MODAL);
        trainers.show();


    }

    public void setTrainerItemsForSportClass(SportClass sportClass) {
        TrainerList trainerList = sportClass.getTrainers();
            dataTrainer = FXCollections.observableArrayList();
                for (int i = 0; i < trainerList.size(); i++) {
                    dataTrainer.add(sportClass.getTrainers().getTrainerByNumber(i));
                }
                filteredTrainer = new FilteredList<>(dataTrainer, p -> true);
                tableViewTrainers.setItems(dataTrainer);
                tableViewTrainers.setItems(filteredTrainer);
            if (trainerList.size()==0){
                tableViewTrainers.setItems(null);
        }



    }

    public void setAllTrainerItemsForComboBox(TrainerList trainerList) {
        for (int i = 0; i < trainerList.size(); i++) {
            trainersComboBox.getItems().add(trainerList.getTrainerByNumber(i));
        }
    }

    public Trainer getSelectedComboBoxItem() {
        Trainer trainer = (Trainer) trainersComboBox.getSelectionModel().getSelectedItem();
        return trainer;
    }

    public Trainer getSelectedTrainerInSportClass() {
        Trainer trainer = (Trainer) tableViewTrainers.getSelectionModel().getSelectedItem();
        return trainer;
    }

    public SportClass getNewEditedSportsClass() {
        SportClass newSportClass = getSelectedSportClass();
        Integer capacityForNewMember = (Integer) capacity.getSelectionModel().getSelectedItem();
        newSportClass.setCapacity(capacityForNewMember);
        newSportClass.setName(name.getText());
        Integer dayForNewMember = (Integer) day.getSelectionModel().getSelectedItem();
        Integer monthForNewMember = (Integer) month.getSelectionModel().getSelectedItem();
        Integer yearForNewMember = (Integer) year.getSelectionModel().getSelectedItem();
        if (dayForNewMember != null && monthForNewMember != null && yearForNewMember != null) {
            Date date = new Date(dayForNewMember, monthForNewMember, yearForNewMember);
            newSportClass.setDate(date);
        }
        return newSportClass;
    }
    public void participants()
    {
        participants = new Stage();
        VBox hBox = new VBox();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(hBox,400,300);
        tableViewParticipants = new TableView();
        TableColumn firstNameColumn = new TableColumn("First name");
        TableColumn lastNameColumn = new TableColumn("Last name");
        TableColumn userNameColumn = new TableColumn("User name");
        TableColumn studentNumberColumn = new TableColumn("Student number");
        tableViewParticipants.getColumns().addAll(firstNameColumn,lastNameColumn,userNameColumn,studentNumberColumn);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Member,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Member,String>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Member,String>("userName"));
        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<Member,String>("studentNumber"));
        Label search = new Label("Search: ");
        TextField textField = new TextField();
        textField.setMaxSize(80,20);
        textField.setOnKeyReleased(event -> {
            buttonHandler.searchParticipantByName(filteredMember, textField);
        });
        GridPane.setConstraints(search,0,0);
        GridPane.setConstraints(textField,1,0);
        gridPane.getChildren().addAll(search,textField);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        hBox.getChildren().addAll(tableViewParticipants,gridPane);
        participants.setScene(scene);
        participants.show();

    }
    public void setParticipantsItems(SportClass sportClass) {
        dataMember = FXCollections.observableArrayList();
        for (int i = 0; i < sportClass.getAllParticipants().size(); i++) {
            dataMember.add(sportClass.getAllParticipants().getMemberByNumber(i));
        }
        filteredMember = new FilteredList<>(dataMember, p -> true);
        tableViewParticipants.setItems(dataMember);
        tableViewParticipants.setItems(filteredMember);
    }
}
