package studentsports.view;

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
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Trainer;
import studentsports.domain.model.TrainerList;

public class TrainersMenu {
    private Stage trainerMenu;
    private FilteredList<Trainer> filteredtrainerData;
    private TableView tableViewTrainer;
    private ObservableList<Trainer> data;
    private Stage edit;
    private Stage add;
    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private PasswordField password;
    private ButtonHandler buttonHandler;
    private Text successfulOperation;

    public TrainersMenu(StudentSportsController controller) {
        this.tableViewTrainer = new TableView();
        this.trainerMenu = new Stage();
        this.trainerMenu = trainerMenu();
        this.buttonHandler = new ButtonHandler(controller);
    }

    public void setTrainerItems(TrainerList trainerList) {
        data = FXCollections.observableArrayList();
        for (int i = 0; i < trainerList.size(); i++) {
            data.add(trainerList.getTrainerByNumber(i));
        }
        this.filteredtrainerData = new FilteredList<>(data, p -> true);
        tableViewTrainer.setItems(data);
        tableViewTrainer.setItems(filteredtrainerData);
    }

    private Stage trainerMenu() {
        tableViewTrainer.setEditable(true);
        Image image = new Image("images/logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        trainerMenu.setResizable(false);
        VBox vBox = new VBox();
        HBox forImage = new HBox();
        forImage.setPadding(new Insets(0, 560, 0, 0));
        forImage.setAlignment(Pos.TOP_LEFT);
        forImage.getChildren().add(imageView);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_RIGHT);
        Button addTrainerButton = new Button("Add");
        addTrainerButton.setPrefSize(100, 40);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(40);
        Button deleteTrainerButton = new Button("Delete");
        deleteTrainerButton.setPrefSize(100, 40);
        hBox.getChildren().addAll(forImage, addTrainerButton, deleteTrainerButton);
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setPadding(new Insets(20, 20, 20, 20));

        deleteTrainerButton.setOnAction(event -> {
            if(tableViewTrainer.getItems().isEmpty())
            {

            }
            else if(tableViewTrainer.getSelectionModel().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Trainer has not been selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select trainer to delete");
                alert.showAndWait();
            }
            else
            buttonHandler.deleteTrainer(successfulOperation);
        });
        addTrainerButton.setOnAction(event -> buttonHandler.addTrainer(this));


        TextField textField = new TextField();
        textField.setPromptText("Search by name");
        successfulOperation = new Text();
        successfulOperation.setStyle("-fx-font: 20 arial;");
        hBox2.getChildren().addAll(successfulOperation, textField);
        hBox2.setSpacing(650);

        textField.setOnKeyReleased(event -> {
            buttonHandler.searchTrainerByName(filteredtrainerData, textField);
        });


        TableColumn firstNameCol = new TableColumn("First name");
        TableColumn lastNameCol = new TableColumn("Last name");
        TableColumn userNameCol = new TableColumn("User name");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Trainer, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Trainer, String>("lastName"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<Trainer, String>("userName"));

        tableViewTrainer.getColumns().addAll(firstNameCol, lastNameCol, userNameCol);
        tableViewTrainer.setMinHeight(480);
        tableViewTrainer.setMinWidth(1010);
        hBox1.getChildren().add(tableViewTrainer);

        vBox.getChildren().addAll(hBox, hBox1, hBox2);

        tableViewTrainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonHandler.doubleClickEditTrainer(tableViewTrainer, event, TrainersMenu.this);
            }
        });


        Scene scene = new Scene(vBox, 1000, 650);
        trainerMenu.setScene(scene);
        return trainerMenu;
    }

    public void editTrainer(SelectionModel selectionModel) {
        Trainer trainer = (Trainer) selectionModel.getSelectedItem();
        edit = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.TOP_CENTER);
        firstName = new TextField();
        lastName = new TextField();
        userName = new TextField();
        Button save = new Button("Save");

        save.setPrefSize(70, 30);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(70, 30);
        Pane pane1 = new Pane();
        pane1.minHeightProperty().bind(firstName.heightProperty());
        Pane pane2 = new Pane();
        pane2.minHeightProperty().bind(firstName.heightProperty());
        Pane pane3 = new Pane();
        pane3.minHeightProperty().bind(firstName.heightProperty());
        Pane pane4 = new Pane();
        pane4.minHeightProperty().bind(firstName.heightProperty());
        Pane pane5 = new Pane();
        pane5.minHeightProperty().bind(firstName.heightProperty());
        Pane pane6 = new Pane();
        pane6.minHeightProperty().bind(firstName.heightProperty());
        Label label = new Label("First Name:");
        Label label1 = new Label("Last name:");
        Label label2 = new Label("User name:");


        GridPane.setConstraints(firstName, 3, 0);
        GridPane.setColumnSpan(firstName, 3);
        GridPane.setConstraints(lastName, 3, 1);
        GridPane.setColumnSpan(lastName, 3);
        GridPane.setConstraints(userName, 3, 2);
        GridPane.setColumnSpan(userName, 3);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 3);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setColumnSpan(label1, 3);
        GridPane.setConstraints(label2, 0, 2);
        GridPane.setColumnSpan(label2, 3);
        GridPane.setConstraints(pane6, 0, 3);
        GridPane.setConstraints(pane5, 0, 4);
        GridPane.setConstraints(pane4, 0, 5);
        GridPane.setConstraints(pane3, 0, 6);
        GridPane.setConstraints(pane1, 0, 7);
        GridPane.setConstraints(pane2, 0, 8);
        GridPane.setConstraints(save, 0, 9);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 5, 9);
        GridPane.setColumnSpan(cancel, 2);

        root.getChildren().addAll(firstName, lastName, userName, label, label1, label2, save, cancel, pane1, pane2, pane3, pane4, pane5, pane6);

        firstName.setText(trainer.getFirstName());
        lastName.setText(trainer.getLastName());
        userName.setText(trainer.getUserName());
        save.setOnAction(event -> {
            try {

                if(firstName.getText().isEmpty())
                {
                    firstName.setStyle("-fx-prompt-text-fill: red");
                    firstName.setPromptText("First name");

                }
                else if (firstName.getText().length() > 14) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("First name cannot be longer than 15 letters");
                    alert.showAndWait();

                }
                else if(lastName.getText().isEmpty())
                {
                    lastName.setStyle("-fx-prompt-text-fill: red");
                    lastName.setPromptText("Last name");

                }
                else if (lastName.getText().length() > 14) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Last name cannot be longer than 15 letters");
                    alert.showAndWait();

                }
                else if(userName.getText().length() <4)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 10 characters");
                    alert.showAndWait();

                }
                else if(userName.getText().length() >14)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 10 characters");
                    alert.showAndWait();

                } else {
                    buttonHandler.saveEditedTrainer(edit, successfulOperation);
                }
            }
            catch (Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Trainer with this username already exists");
                alert.showAndWait();
            }

        });


        cancel.setOnAction(event -> buttonHandler.stageClose(edit));

        Scene scene = new Scene(root, 400, 400);
        edit.setScene(scene);
        edit.show();
    }

    public void addTrainer() {
        add = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.TOP_CENTER);
        firstName = new TextField();
        lastName = new TextField();
        userName = new TextField();
        password = new PasswordField();
        Button save = new Button("Save");

        save.setPrefSize(70, 30);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(70, 30);
        Pane pane1 = new Pane();
        pane1.minHeightProperty().bind(firstName.heightProperty());
        Pane pane2 = new Pane();
        pane2.minHeightProperty().bind(firstName.heightProperty());
        Label label = new Label("First Name:");
        Label label1 = new Label("Last name:");
        Label label2 = new Label("User name:");
        Label passwordLabel = new Label("Password");

        GridPane.setConstraints(firstName, 3, 0);
        GridPane.setColumnSpan(firstName, 3);
        GridPane.setConstraints(lastName, 3, 1);
        GridPane.setColumnSpan(lastName, 3);
        GridPane.setConstraints(userName, 3, 2);
        GridPane.setColumnSpan(userName, 3);
        GridPane.setConstraints(password, 3, 3);
        GridPane.setColumnSpan(password, 3);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 3);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setColumnSpan(label1, 3);
        GridPane.setConstraints(label2, 0, 2);
        GridPane.setColumnSpan(label2, 3);
        GridPane.setConstraints(passwordLabel, 0, 3);
        GridPane.setColumnSpan(passwordLabel, 3);
        GridPane.setConstraints(save, 3, 9);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 5, 9);
        GridPane.setColumnSpan(cancel, 2);

        root.getChildren().addAll(firstName, lastName, userName, label, label1, label2, save, cancel, pane1, pane2, password, passwordLabel);

        save.setOnAction(event -> {
            if(firstName.getText().isEmpty())
            {
                firstName.setStyle("-fx-prompt-text-fill: red");
                firstName.setPromptText("First name");

            }
            else if (firstName.getText().length() > 15) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("First name cannot be longer than 15 letters");
                alert.showAndWait();

            }
            else if(lastName.getText().isEmpty())
            {
                lastName.setStyle("-fx-prompt-text-fill: red");
                lastName.setPromptText("Last name");

            }
            else if (lastName.getText().length() > 14) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Last name cannot be longer than 15 letters");
                alert.showAndWait();

            }
            else if(userName.getText().length() <4)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Username should be between 4 and 10 characters");
                alert.showAndWait();

            }
            else if(userName.getText().length() >14)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Username should be between 4 and 10 characters");
                alert.showAndWait();

            }
            else if(password.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Password should be between 4 and 10 characters");
                alert.showAndWait();;
            }
            else if(password.getText().length() > 14) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Password should be between 4 and 10 characters");
                alert.showAndWait();
            }

            else {
                try {
                    buttonHandler.saveAddedTrainer(add, successfulOperation);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Trainer with this username already exists");
                    alert.showAndWait();
                }
            }

        });


        cancel.setOnAction(event -> buttonHandler.stageClose(add));

        Scene scene = new Scene(root, 250, 260);
        add.setScene(scene);
        add.show();
    }

    public void showStage() {
        trainerMenu.show();
    }

    public Trainer getSelectedTrainer() {
        Trainer trainer = (Trainer) tableViewTrainer.getSelectionModel().getSelectedItem();
        return trainer;
    }

    public Trainer getNewEditedTrainer() {
        Trainer trainer = getSelectedTrainer();
        trainer.setFirstName(firstName.getText());
        trainer.setLastName(lastName.getText());
        trainer.setUserName(userName.getText());

        return trainer;
    }

    public Trainer getNewTrainer() {
        Trainer trainer = new Trainer(firstName.getText(), lastName.getText(), userName.getText(), password.getText());
        return trainer;
    }

}
