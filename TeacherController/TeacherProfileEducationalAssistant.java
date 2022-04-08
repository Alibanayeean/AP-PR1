package TeacherController;

import TeacherController.TeacherPageEducationalAssistant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;
import Back.*;
import API.*;
import publicController.LoginPage;

public class TeacherProfileEducationalAssistant implements Initializable{
    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    LinkedList<Course> courses = new LinkedList<>();

    Teacher teacher;

    @FXML
    javafx.scene.control.MenuBar MenuBar;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Button LogOutButton;

    @FXML
    Label timeShowLabel;

    @FXML
    Button HomeButton;

    @FXML
    Label FirstNameLabel;

    @FXML
    Label LastNameLabel;

    @FXML
    Label IdentityCodeLabel;

    @FXML
    Label IDLabel;

    @FXML
    Label RoomNumberLabel;

    @FXML
    Label CollegeLabel;

    @FXML
    Label TeacherDegreeLabel;

    @FXML
    TextField PhoneNumberTextField;

    @FXML
    TextField EmailTextField;

    @FXML
    Button EditPhoneNumber;

    @FXML
    Button EditEmail;

    @FXML
    ImageView imageViewUser;

    int numClickedEditPhoneNumber = 0;
    int numClickedEditEmail = 0;



    Stage stage;
    @FXML
    void ChangingColor(ActionEvent event) {
        Color color = colorPicker.getValue();
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        EditEmail.setBackground(new Background(new BackgroundFill(color, null, null)));
        EditPhoneNumber.setBackground(new Background(new BackgroundFill(color, null, null)));
        teacher.color = colorPicker.getValue();
    }

    @FXML
    void LogOutFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LoginPage.fxml"));
            Parent root = loader.load();
            LoginPage loginPage = loader.getController();
            loginPage.setImageView(students ,teachers, courses);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image1 = new Image("Images/logOut.png");
        LogOutButton.setGraphic(new ImageView(image1));

        Image image3 = new Image("Images/homeIcon2.png");
        HomeButton.setGraphic(new ImageView(image3));



        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
//            LocalTime currentTime = LocalTime.now();

            LocalDateTime instance = LocalDateTime.now();
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
            String formattedString = formatter.format(instance);
            timeShowLabel.setText(formattedString);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        PhoneNumberTextField.setDisable(true);
        EmailTextField.setDisable(true);
    }

    public void setTeacher(LinkedList<Student> students, LinkedList<Teacher> teachers, Teacher teacher, LinkedList<Course> courses){
        this.students = students;
        this.teachers = teachers;
        this.teacher = teacher;
        this.courses = courses;


        if(teacher.image == null ){
            Image image2 = new Image("Images/userIcon256.png");
            imageViewUser.setImage(image2);
        }
        else if(teacher.image.equals("")){
            Image image2 = new Image("Images/userIcon256.png");
            imageViewUser.setImage(image2);
        }
        else if(teacher.image.equals("null")){
            Image image2 = new Image("Images/userIcon256.png");
            imageViewUser.setImage(image2);
        }
        else{
            Image image2 = new Image("Images/" + teacher.image);
            imageViewUser.setImage(image2);
        }

        Color color = teacher.color;
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        EditEmail.setBackground(new Background(new BackgroundFill(color, null, null)));
        EditPhoneNumber.setBackground(new Background(new BackgroundFill(color, null, null)));
        colorPicker.setValue(color);

        FirstNameLabel.setText(teacher.getFirstname());
        LastNameLabel.setText(teacher.getLastname());
        IdentityCodeLabel.setText(teacher.getCodeMelli());
        IDLabel.setText(teacher.getId() + "");
        PhoneNumberTextField.setText(teacher.getPhoneNumber());
        EmailTextField.setText(teacher.getEmail());
        CollegeLabel.setText(teacher.getCollege() + "");
        RoomNumberLabel.setText(teacher.getRoomNumber() + "");
        TeacherDegreeLabel.setText(teacher.getTeacherDegree() + "");


    }

    public void HomeButtonFunction(ActionEvent e) {

        try {
            stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TeacherPageEducationalAssistant.fxml"));
            Parent root = loader.load();
            TeacherPageEducationalAssistant TeacherPageEducationalAssistant = loader.getController();
            TeacherPageEducationalAssistant.setTeacher(students, teachers, courses, teacher);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void mouseMovedLogOut(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitLogOut(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

    public void mouseMovedHomeButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitHomeButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }



    public void EditPhoneNumberFunction(ActionEvent e) {
        if(numClickedEditPhoneNumber % 2 == 0){
            PhoneNumberTextField.setDisable(false);
            EditPhoneNumber.setText("OK");
            numClickedEditPhoneNumber++;
        }
        else{
            for (int i = 0; i < PhoneNumberTextField.getText().length(); i++) {
                if(!(PhoneNumberTextField.getText().charAt(i) >= '0' && PhoneNumberTextField.getText().charAt(i) <= '9')){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect");
                    alert.setHeaderText("Incorrect phone number input");
                    alert.showAndWait();
                    return;
                }
            }
            EditPhoneNumber.setText("Edit");
            PhoneNumberTextField.setDisable(true);
            teacher.setPhoneNumber(EditPhoneNumber.getText());
            numClickedEditPhoneNumber++;
        }

    }

    public void EditEmailFunction(ActionEvent e) {
        if(numClickedEditEmail % 2 == 0){
            EmailTextField.setDisable(false);
            EditEmail.setText("OK");
            numClickedEditEmail++;
        }
        else{
            String email = EmailTextField.getText();
            boolean b1 = false;
            boolean b2 = false;
            for (int i = 0; i < email.length(); i++) {
                if(email.charAt(i) == '@'){
                    b1 = true;
                }
                if(b1 & email.charAt(i) == '.'){
                    b2 = true;
                    break;
                }
            }
            if(!(b1 & b2)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText("Invalid email format");
                alert.showAndWait();
                return;
            }

            EditEmail.setText("Edit");
            EmailTextField.setDisable(true);
            teacher.setEmail(EmailTextField.getText());
            numClickedEditEmail++;

        }
    }

}
