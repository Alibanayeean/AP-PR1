package StudentController;

import TeacherController.TeacherPageEducationalAssistant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

public class ShowWeeklyScheduleStudent implements Initializable{
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
    Pane pane;



    Stage stage;
    @FXML
    void ChangingColor(ActionEvent event) {
        Color color = colorPicker.getValue();
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


        Color color = student.color;
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        colorPicker.setValue(color);

        LinkedList<Course> c;
        c = API.GiveCoursesFromAStudent(student, courses);

        PrintForPane(c);


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


    public void PrintForPane(LinkedList<Course> courses){

        Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        Color color = student.color;

        for (int i = 0; i < courses.size(); i++) {

            for (int j = 0; j < courses.get(i).getDays().size(); j++) {
                Label label = new Label(courses.get(i).getName() + '\n' + courses.get(i).getId());
                if(courses.get(i).getDays().get(j) == Days.Saturday){
                    label.setLayoutX(991);
                }
                else if(courses.get(i).getDays().get(j) == Days.Sunday){
                    label.setLayoutX(825);
                }
                else if(courses.get(i).getDays().get(j) == Days.Monday){
                    label.setLayoutX(661);
                }
                else if(courses.get(i).getDays().get(j) == Days.Tuesday){
                    label.setLayoutX(495);
                }
                else if(courses.get(i).getDays().get(j) == Days.Wednesday){
                    label.setLayoutX(330);
                }
                else if(courses.get(i).getDays().get(j) == Days.Thursday){
                    label.setLayoutX(165);
                }
                else if(courses.get(i).getDays().get(j) == Days.Friday){
                    label.setLayoutX(0);
                }
                label.setLayoutY(52 * (courses.get(i).getHourEndClass() - courses.get(i).getHourBeginClass()));
                label.setAlignment(Pos.CENTER);
                label.setMinWidth(166);
                label.setMinHeight((courses.get(i).getHourEndClass() - courses.get(i).getHourBeginClass()) * 53.0);
//                label.prefHeight();
//                label.prefWidth();
                label.setFont(font);
                pane.getChildren().add(label);
                Color color1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.7);
                label.setBackground(new Background(new BackgroundFill(color1, null, null)));
            }
        }
    }

    public Teacher TeacherCourse(LinkedList<Teacher> teachers, int id){
        for (Teacher te : teachers) {
            for (int i = 0; i < te.getCourses().size(); i++) {
                if(te.getCourses().get(i) == id){
                    return te;
                }
            }
        }
        return null;
    }

    public boolean StringCheckIsNumber(String s){
        for (int i = 0; i < s.length(); i++) {
            if(!((s.charAt(i) >= '0' & s.charAt(i) <= '9') | s.charAt(i) == '.')){
                return false;
            }
        }
        return true;
    }

    public void TeacherListMenuFunction(ActionEvent e) {
        try{
            stage = ((Stage)(((Scene)LogOutButton.getScene()).getWindow()));
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


    public void CoursesListMenuFunction(ActionEvent event) {
    }

    public void mouseMovedColorPicker(MouseEvent mouseEvent) {
    }

    public void mouseExitColorPicker(MouseEvent mouseEvent) {
    }
}
