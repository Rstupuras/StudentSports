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
import javafx.stage.Stage;
import studentsports.controller.StudentSportsController;
import studentsports.domain.model.Date;
import studentsports.domain.model.Member;
import studentsports.domain.model.MemberList;

import java.sql.SQLException;


public class MainMenu {
    private Stage mainMenu;
    private FilteredList<Member> filteredUnpaidMemberData;
    private TableView tableViewUnpaidMember;
    private ObservableList<Member> data;
    private Stage edit;
    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private TextField studentNumber;
    private ComboBox day;
    private ComboBox month;
    private ComboBox year;
    private ComboBox payingFor;
    private GeneratedItems generatedItems;
    private ButtonHandler buttonHandler;
    private Text saveSuccessful;

    public MainMenu(StudentSportsController controller) {
        this.tableViewUnpaidMember = new TableView();
        this.mainMenu = new Stage();
        this.mainMenu = mainMenu();
        this.generatedItems = new GeneratedItems();
        buttonHandler = new ButtonHandler(controller);


    }

    public void setUnpaidMemberItems(MemberList memberList) {
        data = FXCollections.observableArrayList();
        for (int i = 0; i < memberList.size(); i++) {
            data.add(memberList.getMemberByNumber(i));
        }
        this.filteredUnpaidMemberData = new FilteredList<>(data, p -> true);
        tableViewUnpaidMember.setItems(data);
        tableViewUnpaidMember.setItems(filteredUnpaidMemberData);
    }

    public Stage mainMenu() {
        tableViewUnpaidMember.setEditable(true);
        Image image = new Image("images/logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        mainMenu.setResizable(false);
        VBox vBox = new VBox();
        HBox forImage = new HBox();
        forImage.setPadding(new Insets(0, 420, 0, 0));
        forImage.setAlignment(Pos.TOP_LEFT);
        forImage.getChildren().add(imageView);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_RIGHT);
        Button trainersButton = new Button("Trainers");
        Button refreshButton = new Button("Refresh");
        trainersButton.setPrefSize(100, 40);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(40);
        Button sportClassesButton = new Button("Sport classes");
        sportClassesButton.setPrefSize(100, 40);
        Button membersButton = new Button("Members");
        membersButton.setPrefSize(100, 40);
        hBox.getChildren().addAll(forImage, trainersButton, sportClassesButton, membersButton);
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setPadding(new Insets(20, 20, 20, 20));

        trainersButton.setOnAction(event ->
        {
            buttonHandler.showTrainersMenu();
        });


        refreshButton.setOnAction(event -> {
            try {
                buttonHandler.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        membersButton.setOnAction(event -> {

            try {
                buttonHandler.showMembersMenu();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
        sportClassesButton.setOnAction(event -> {
            buttonHandler.showSportClassMenu();
        });
        mainMenu.setOnCloseRequest(event -> System.exit(0));


        TextField textField = new TextField();
        textField.setPromptText("Search by name");
        saveSuccessful = new Text();
        saveSuccessful.setStyle("-fx-font: 20 arial;");
        hBox2.getChildren().addAll(saveSuccessful,refreshButton, textField);
        hBox2.setSpacing(20);
        textField.setOnKeyReleased(event -> {
            buttonHandler.searchUnpaidMember(filteredUnpaidMemberData, textField);
        });


        TableColumn firstNameCol = new TableColumn("First name");
        TableColumn lastNameCol = new TableColumn("Last name");
        TableColumn userNameCol = new TableColumn("User name");
        TableColumn studentNoCol = new TableColumn("Student number");
        TableColumn paymentCol = new TableColumn("Payment date");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("userName"));
        studentNoCol.setCellValueFactory(new PropertyValueFactory<Member, String>("studentNumber"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<Member, Date>("paymentDate"));


        tableViewUnpaidMember.getColumns().addAll(firstNameCol, lastNameCol, userNameCol, studentNoCol, paymentCol);
        tableViewUnpaidMember.setMinHeight(480);
        tableViewUnpaidMember.setMinWidth(1010);
        hBox1.getChildren().add(tableViewUnpaidMember);

        vBox.getChildren().addAll(hBox, hBox1, hBox2);

        tableViewUnpaidMember.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonHandler.doubleClickEditUnpaidMember(tableViewUnpaidMember, event, MainMenu.this);
            }
        });


        Scene scene = new Scene(vBox, 1000, 650);
        mainMenu.setScene(scene);
        return mainMenu;

    }

    public void showStage() {
        mainMenu.show();
    }

    public void editMember(SelectionModel selectionModel) {

        Member member = (Member) selectionModel.getSelectedItem();
        edit = new Stage();
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.TOP_CENTER);
        firstName = new TextField();
        lastName = new TextField();
        userName = new TextField();
        studentNumber = new TextField();
        studentNumber.setOnKeyTyped(event -> {
            char ch = event.getCharacter().charAt(0);
            if (!(Character.isDigit(ch))) {
                event.consume();
            }
        });

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
        Label label3 = new Label("Student Number:");
        Label label4 = new Label("Day:");
        Label label5 = new Label("Month:");
        Label label6 = new Label("Year:");
        Label payment = new Label("Payment date");
        Label payingUntill = new Label("Paying for:");
        payingFor = new ComboBox();
        payingFor.setItems(generatedItems.payedUntill());
        day = new ComboBox();
        day.setItems(generatedItems.arrayDay(31));
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


        GridPane.setConstraints(firstName, 3, 0);
        GridPane.setColumnSpan(firstName, 3);
        GridPane.setConstraints(lastName, 3, 1);
        GridPane.setColumnSpan(lastName, 3);
        GridPane.setConstraints(userName, 3, 2);
        GridPane.setColumnSpan(userName, 3);
        GridPane.setConstraints(studentNumber, 3, 3);
        GridPane.setColumnSpan(studentNumber, 3);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 3);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setColumnSpan(label1, 3);
        GridPane.setConstraints(label2, 0, 2);
        GridPane.setColumnSpan(label2, 3);
        GridPane.setConstraints(label3, 0, 3);
        GridPane.setColumnSpan(label3, 3);
        GridPane.setConstraints(payment, 2, 4);
        GridPane.setColumnSpan(payment, 2);
        GridPane.setConstraints(label4, 0, 5);
        GridPane.setConstraints(day, 1, 5);
        GridPane.setConstraints(label5, 2, 5);
        GridPane.setConstraints(month, 3, 5);
        GridPane.setConstraints(label6, 4, 5);
        GridPane.setConstraints(year, 5, 5);
        GridPane.setConstraints(payingUntill, 0, 6);
        GridPane.setColumnSpan(payingUntill, 2);
        GridPane.setConstraints(payingFor, 2, 6);
        GridPane.setColumnSpan(payingFor, 4);
        GridPane.setConstraints(pane1, 0, 7);
        GridPane.setConstraints(pane2, 0, 8);
        GridPane.setConstraints(save, 0, 9);
        GridPane.setColumnSpan(save, 2);
        GridPane.setConstraints(cancel, 5, 9);
        GridPane.setColumnSpan(cancel, 2);

        root.getChildren().addAll(firstName, lastName, userName, studentNumber, label, label1, label2, label3, day, month, year, label4, label5, label6, payment, save, cancel, payingFor, pane1, pane2, payingUntill);

        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        userName.setText(member.getUserName());
        studentNumber.setText(member.getStudentNumber());
        save.setOnAction(event -> {
            try {
                if (firstName.getText().isEmpty()) {
                    firstName.setStyle("-fx-prompt-text-fill: red");
                    firstName.setPromptText("First name");

                }
                else if (firstName.getText().length() > 15) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("First name cannot be longer than 15 letters");
                    alert.showAndWait();

                }else if (lastName.getText().isEmpty()) {
                    lastName.setStyle("-fx-prompt-text-fill: red");
                    lastName.setPromptText("Las tname");

                }
                else if (lastName.getText().length() > 15) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Last name cannot be longer than 15 letters");
                    alert.showAndWait();

                }else if (userName.getText().length() < 4) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 10 characters");
                    alert.showAndWait();
                }
                else if (userName.getText().length() > 10) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Username should be between 4 and 10 characters");
                    alert.showAndWait();
                }
                else if ((studentNumber.getText() == null) == false && studentNumber.getText().equals("") == false) {

                    if (!(studentNumber.getText().length() == 6))

                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setContentText("Student number should contain 6 digits");
                        alert.showAndWait();
                    } else
                        buttonHandler.saveEditedUnpaidMember(edit, saveSuccessful);
                } else {
                    buttonHandler.saveEditedUnpaidMember(edit, saveSuccessful);
                }
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Member with this username already exists");
                alert.showAndWait();
            }
        });


        cancel.setOnAction(event -> buttonHandler.stageClose(edit));

        Scene scene = new Scene(root, 400, 400);
        edit.setScene(scene);
        edit.show();

    }

    public Member getNewMember() {
        Member member = (Member) tableViewUnpaidMember.getSelectionModel().getSelectedItem();
        member.setFirstName(firstName.getText());
        member.setLastName(lastName.getText());
        member.setUserName(userName.getText());
        String studentNumberString = studentNumber.getText();
        if (studentNumber.getText() != null && studentNumberString.isEmpty() == false) {
            if ((100000 < Integer.parseInt(studentNumber.getText()) && Integer.parseInt(studentNumber.getText()) < 999999)) {
                member.setStudentNumber(studentNumber.getText());

            } else {

            }
        } else {
            member.setStudentNumber(null);
        }

        Integer dayForNewMember = (Integer) day.getSelectionModel().getSelectedItem();
        Integer monthForNewMember = (Integer) month.getSelectionModel().getSelectedItem();
        Integer yearForNewMember = (Integer) year.getSelectionModel().getSelectedItem();
        if (dayForNewMember != null && monthForNewMember != null && yearForNewMember != null) {
            Date date = new Date(dayForNewMember, monthForNewMember, yearForNewMember);
            member.setPaymentDate(date);
            final String month = "1 Month";
            final String twoMonth = "2 Months";
            final String threeMonth = "3 Months";
            final String fourMonths = "4 Months";
            final String semester = "Semester";
            if (payingFor.getSelectionModel().getSelectedItem() == null) {
                member.setPaidUntil(null);
                member.setPaymentDate(null);
            } else {
                switch (payingFor.getSelectionModel().getSelectedItem().toString()) {
                    case month:
                        member.setPaidUntil(new Date(dayForNewMember, monthForNewMember + 1, yearForNewMember));
                        break;
                    case twoMonth:
                        member.setPaidUntil(new Date(dayForNewMember, monthForNewMember + 2, yearForNewMember));
                        break;
                    case threeMonth:
                        member.setPaidUntil(new Date(dayForNewMember, monthForNewMember + 3, yearForNewMember));
                        break;
                    case fourMonths:
                        member.setPaidUntil(new Date(dayForNewMember, monthForNewMember + 4, yearForNewMember));
                        break;
                    case semester:
                        if (monthForNewMember <= 6 && yearForNewMember == 2018) {
                            member.setPaidUntil(new Date(1, 7, 2018));
                        }
                        if (monthForNewMember <= 6 && yearForNewMember == 2019) {
                            member.setPaidUntil(new Date(1, 7, 2019));
                        }
                        if (monthForNewMember > 6 && yearForNewMember == 2018) {
                            member.setPaidUntil(new Date(1, 2, 2019));
                        }
                        if (monthForNewMember > 6 && yearForNewMember == 2019) {
                            member.setPaidUntil(new Date(1, 2, 2020));
                        }
                        break;
                }
                System.out.println(member.getPaidUntil());

            }


        }
        return member;
    }

    public Member getSelectedMember() {
        Member member = (Member) tableViewUnpaidMember.getSelectionModel().getSelectedItem();
        return member;
    }
}
