package studentsports.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Member;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


public class MainMenu {
    private Stage mainMenu;
    private ButtonHandler buttonHandler;
    private TextField textField;
    private PasswordField passwordField;
    private CheckBox checkBox;
    private String password;
    private String userName;
    private Stage add;
    private TextField firstName;
    private TextField lastName;
    private TextField userNameTextfield;
    private TextField studentNumber;
    private PasswordField passwordTextField;
    private Text wrongLogin;

    public MainMenu(StudentSportsController controller) {
        this.mainMenu = new Stage();
        this.mainMenu = mainMenu();
        this.buttonHandler = new ButtonHandler(controller);

    }


    public Stage mainMenu() {
        wrongLogin = new Text();
        wrongLogin.setFill(Color.RED);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 200, 0, 0));
        gridPane.setAlignment(Pos.CENTER);
        Label label = new Label("User name");
        this.textField = new TextField();
        Label label1 = new Label("Password");
        this.passwordField = new PasswordField();
        this.checkBox = new CheckBox("Trainer");
        Button button = new Button("Log in");
        Button register = new Button("Register");
        register.setOnAction(event -> buttonHandler.addMember(this));
        button.setOnAction(event -> {
            try {

                setPassword(passwordField.getText());
                setUserName(textField.getText());
                buttonHandler.logIn();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(textField, 0, 1);
        GridPane.setConstraints(label1, 0, 2);
        GridPane.setConstraints(passwordField, 0, 3);
        GridPane.setConstraints(checkBox, 0, 4);
        GridPane.setConstraints(button, 0, 5);
        GridPane.setConstraints(wrongLogin,0,8);
        gridPane.getChildren().addAll(label, textField, label1, passwordField, checkBox, button, wrongLogin);
        hBox.getChildren().addAll(gridPane, register);
        Scene scene = new Scene(hBox, 700, 300);
        mainMenu.setTitle("Student sports");
        mainMenu.setScene(scene);
        return mainMenu;

    }

    public void showStage() {
        mainMenu.show();
    }

    public void closeStage() {

        passwordField.setText("");
        textField.setText("");
        textField.requestFocus();
        checkBox.selectedProperty().setValue(false);
        mainMenu.close();
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }

    public void addMember() {

        add = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.TOP_CENTER);
        firstName = new TextField();
        lastName = new TextField();
        userNameTextfield = new TextField();
        studentNumber = new TextField();
        Button save = new Button("Save");
        passwordTextField = new PasswordField();
        studentNumber.setOnKeyTyped(event -> {
            char ch = event.getCharacter().charAt(0);
            if (!(Character.isDigit(ch))) {
                event.consume();
            }
        });

        save.setPrefSize(70, 30);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(70, 30);
        Label label = new Label("First Name:");
        Label label1 = new Label("Last name:");
        Label label2 = new Label("User name:");
        Label passwordLabel = new Label("Password");
        Label label3 = new Label("Student Number:");


        GridPane.setConstraints(firstName, 3, 0);
        GridPane.setColumnSpan(firstName, 3);
        GridPane.setConstraints(lastName, 3, 1);
        GridPane.setColumnSpan(lastName, 3);
        GridPane.setConstraints(userNameTextfield, 3, 2);
        GridPane.setColumnSpan(userNameTextfield, 3);
        GridPane.setConstraints(passwordTextField, 3, 3);
        GridPane.setColumnSpan(passwordTextField, 3);
        GridPane.setConstraints(studentNumber, 3, 4);
        GridPane.setColumnSpan(studentNumber, 3);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 3);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setColumnSpan(label1, 3);
        GridPane.setConstraints(label2, 0, 2);
        GridPane.setColumnSpan(label2, 3);
        GridPane.setConstraints(label3, 0, 4);
        GridPane.setColumnSpan(label3, 3);
        GridPane.setConstraints(passwordLabel, 0, 3);
        GridPane.setColumnSpan(passwordLabel, 3);
        GridPane.setConstraints(save, 3, 5);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 5, 5);
        GridPane.setColumnSpan(cancel, 2);

        root.getChildren().addAll(firstName, lastName, userNameTextfield, passwordTextField, studentNumber, label, label1, label2, label3, passwordLabel, save, cancel);

        save.setOnAction(event -> {
            try {
                if (firstName.getText().isEmpty()) {
                    firstName.setStyle("-fx-prompt-text-fill: red");
                    firstName.setPromptText("First name");

                } else if (firstName.getText().length() > 15) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("First name cannot be longer than 15 letters");
                    alert.showAndWait();

                } else if (lastName.getText().isEmpty()) {
                    lastName.setStyle("-fx-prompt-text-fill: red");
                    lastName.setPromptText("Last name");

                } else if (lastName.getText().length() > 15) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Last name cannot be longer than 15 letters");
                    alert.showAndWait();

                } else if (userNameTextfield.getText().length() < 4) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 14 characters");
                    alert.showAndWait();

                } else if (userNameTextfield.getText().length() > 14) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 14 characters");
                    alert.showAndWait();
                } else if (passwordTextField.getText().length() < 4) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Password should be between 4 and 10 characters");
                    alert.showAndWait();
                    ;
                } else if (passwordTextField.getText().length() > 14) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Password should be between 4 and 10 characters");
                    alert.showAndWait();
                } else if ((studentNumber.getText() == null) == false && studentNumber.getText().equals("") == false) {
                    System.out.println(!(studentNumber.getText().length() == 6));
                    if (!(studentNumber.getText().length() == 6))

                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setContentText("Student number should contain 6 digits");
                        alert.showAndWait();
                    } else
                        buttonHandler.saveAddedMember(add);
                } else
                    buttonHandler.saveAddedMember(add);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Member with this username already exists");
                alert.showAndWait();

            }


        });


        cancel.setOnAction(event -> buttonHandler.stageClose(add));

        Scene scene = new Scene(root, 350, 250);
        add.setScene(scene);
        add.show();
    }

    public Member getNewMember() {
        if (firstName.getText() != null && lastName.getText() != null && userNameTextfield.getText() != null && passwordTextField.getText() != null) {
            Member member = new Member(firstName.getText(), lastName.getText(), userNameTextfield.getText(), passwordTextField.getText(), null);
            String studentNumberString = studentNumber.getText();
            if (studentNumber.getText() != null && studentNumberString.isEmpty() == false) {
                if ((100000 < Integer.parseInt(studentNumber.getText()) && Integer.parseInt(studentNumber.getText()) < 999999)) {
                    member.setStudentNumber(studentNumber.getText());

                } else {

                }
            } else {
                member.setStudentNumber(null);
            }
            return member;
        }
        return null;

    }

    public void setWrongLogin() {
        wrongLogin.setText("Wrong username or password");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                //Send add members email
                wrongLogin.setText("");
                timer.cancel();
            }
        }, 3000);
    }
}
