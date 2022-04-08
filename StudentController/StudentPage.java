package StudentController;

import TeacherController.TeacherPageEducationalAssistant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;
import Back.*;
import publicController.LoginPage;



public class StudentPage implements Initializable {

    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    LinkedList<Course> courses = new LinkedList<>();

    Student student;

    @FXML
    MenuBar MenuBar;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Button LogOutButton;

    @FXML
    Pane paneFilter;

    @FXML
    Label timeShowLabel;

    @FXML
    ImageView imageViewUser;

    @FXML
    Label Status;

    @FXML
    Label Firstname;

    @FXML
    Label Lastname;

    @FXML
    Label emailLabel;

    @FXML
    Button HomeButton;

    Stage stage;
    @FXML
    void ChangingColor(ActionEvent event) {
        Color color = colorPicker.getValue();
        paneFilter.setBackground(new Background(new BackgroundFill(color, null, null)));
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
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


    }

    public void setStudent(LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Course> courses, Student student){
        this.students = students;
        this.teachers = teachers;
        this.student = student;
        this.courses = courses;

        if(student.image == null){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else if(student.image.equals("")){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else if(student.image.equals("null")){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else{
            Image image2 = new Image("Images/" + student.image);
            imageViewUser.setImage(image2);
        }

        Color color = student.color;
        paneFilter.setBackground(new Background(new BackgroundFill(color, null, null)));
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));

        Status.setText("Student: " + student.getGrade());
        Firstname.setText(student.getFirstname());
        Lastname.setText(student.getLastname());
        emailLabel.setText(student.getEmail());

        colorPicker.setValue(color);

    }

    public void HomeButtonFunction(ActionEvent e) {

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

    public void mouseMovedColorPicker(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitColorPicker(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

    public void CoursesListMenuFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)emailLabel.getScene()).getWindow()));
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

    public void TeacherListMenuFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)emailLabel.getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TeacherListMenuStudent.fxml"));
            Parent root = loader.load();
            TeacherListMenuStudent TeacherListMenuStudent = loader.getController();
            TeacherListMenuStudent.setStudent(students ,teachers, student, courses);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void ProfileFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)Firstname.getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ProfileStudent.fxml"));
            Parent root = loader.load();
            ProfileStudent ProfileStudent = loader.getController();
            ProfileStudent.setStudent(students ,teachers, student, courses);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    public void ScheduleFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)Firstname.getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ShowWeeklyScheduleStudent.fxml"));
            Parent root = loader.load();
            ShowWeeklyScheduleStudent ShowWeeklyScheduleStudent = loader.getController();
            ShowWeeklyScheduleStudent.setStudent(students ,teachers,  courses, student);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

