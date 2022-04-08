package StudentController;

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

public class TeacherListMenuStudent implements Initializable{
    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    LinkedList<Course> courses = new LinkedList<>();

    Student student;

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
    Button ShowButton;

    @FXML
    TextField LastnameTextField;

    @FXML
    ChoiceBox CollegeNameField;

    @FXML
    ChoiceBox TeacherDegreeField;

    @FXML
    Pane pane;



    Stage stage;
    @FXML
    void ChangingColor(ActionEvent event) {
        Color color = colorPicker.getValue();
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        ShowButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        student.color = colorPicker.getValue();
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


        String[] choices_TeacherDegreeField = {"--", "FullProfessor", "AssistantProfessor", "AssociateProfessor"};
        TeacherDegreeField.getItems().addAll(choices_TeacherDegreeField);

        String[] choices_CollegeNameField = {"--", "EE", "CE", "CS", "Math", "Physics", "Chemistry", "Mechanic"};
        CollegeNameField.getItems().addAll(choices_CollegeNameField);


    }

    public void setStudent(LinkedList<Student> students, LinkedList<Teacher> teachers, Student student, LinkedList<Course> courses){
        this.students = students;
        this.teachers = teachers;
        this.student = student;
        this.courses = courses;

        Color color = student.color;
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        ShowButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        colorPicker.setValue(color);

        PrintForPane(teachers);


    }

    public void HomeButtonFunction(ActionEvent e) {

        try {
            stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BCPage.fxml"));
            Parent root = loader.load();
            StudentPage StudentPage = loader.getController();
            StudentPage.setStudent(students, teachers, courses, student);
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

    public void mouseMovedShowButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitShowButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void ShowButtonFunction(ActionEvent e){
        String stringChooseCollegeName = chooseCollegeName();
        String stringChooseTeacherDegreeField = chooseTeacherDegreeField();


        if(stringChooseCollegeName == null){

        }
        else if(stringChooseCollegeName.equals("--")){
            stringChooseCollegeName = "";
        }

        TeacherDegree teacherDegree = null;
        if(stringChooseTeacherDegreeField == null){

        }
        else if(stringChooseTeacherDegreeField.equals("--")){
            stringChooseTeacherDegreeField = "";
        }
        else if(stringChooseTeacherDegreeField.equals("FullProfessor")){
            teacherDegree = TeacherDegree.FullProfessor;
        }
        else if(stringChooseTeacherDegreeField.equals("AssistantProfessor")){
            teacherDegree = TeacherDegree.AssistantProfessor;
        }
        else if(stringChooseTeacherDegreeField.equals("AssociateProfessor")){
            teacherDegree = TeacherDegree.AssociateProfessor;
        }


        LinkedList<Teacher> ForReturn = new LinkedList<>();
        ForReturn = API.ShowTeachers(teachers, LastnameTextField.getText(), stringChooseCollegeName ,teacherDegree);
        PrintForPane(ForReturn);

    }

    public String  chooseTeacherDegreeField (){
        String stringChooseTeacherDegreeField = (String) TeacherDegreeField.getValue();
        return stringChooseTeacherDegreeField ;
    }

    public String  chooseCollegeName (){
        String stringCollegeName = (String) CollegeNameField.getValue();
        return stringCollegeName ;
    }


    public void PrintForPane(LinkedList<Teacher> teachers){

        Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        pane.getChildren().clear();
        pane.setPrefHeight(teachers.size() * 80 + 30);
        for (int i = 0; i < teachers.size(); i++) {

            Label label1 = new Label(teachers.get(i).getLastname());
            label1.prefHeight(142);
            label1.prefWidth(57);
            label1.setLayoutX(217);
            label1.setLayoutY(23 + 80 * i);
            label1.setFont(font);
            pane.getChildren().add(label1);

            Label label2 = new Label(teachers.get(i).getCollege().name());
            label2.prefHeight(142);
            label2.prefWidth(57);
            label2.setLayoutX(536);
            label2.setLayoutY(23 + 80 * i);
            label2.setFont(font);
            pane.getChildren().add(label2);

            Label label3 = new Label(teachers.get(i).getTeacherDegree() + "");
            label3.prefHeight(142);
            label3.prefWidth(57);
            label3.setLayoutX(921);
            label3.setLayoutY(23 + 80 * i);
            label3.setFont(font);
            pane.getChildren().add(label3);

        }
    }

    public void CoursesListMenuFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)LogOutButton.getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CourseListMenuStudent.fxml"));
            Parent root = loader.load();
            CourseListMenuStudent courseListMenuStudent = loader.getController();
            courseListMenuStudent.setStudent(students ,teachers, courses, student);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }



}
