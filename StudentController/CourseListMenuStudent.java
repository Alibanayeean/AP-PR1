package StudentController;

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

public class CourseListMenuStudent implements Initializable{
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
    javafx.scene.control.ScrollBar ScrollBar;

    @FXML
    Button ShowButton;

    @FXML
    TextField idCourseTextField;

    @FXML
    ChoiceBox GradeField;

    @FXML
    TextField WeighCourseTextField;

    @FXML
    ChoiceBox CollegeNameField;

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
                    = DateTimeFormatter.ofPattern("dd-MM-yyyy // hh:mm:ss");
            String formattedString = formatter.format(instance);
            timeShowLabel.setText(formattedString);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


        String[] choices_GradeField = {"--", "BC" , "MS" , "PHD"};
        GradeField.getItems().addAll(choices_GradeField);

        String[] choices_CollegeNameField = {"--", "EE", "CE", "CS", "Math", "Physics", "Chemistry", "Mechanic"};
        CollegeNameField.getItems().addAll(choices_CollegeNameField);


    }

    public void setStudent(LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Course> courses,  Student student){
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

        PrintForPane(courses);

    }

    public void HomeButtonFunction(ActionEvent e) {
        try {
            stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StudentPage.fxml"));
            Parent root = null;
            root = loader.load();
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
        String stringChooseGrade = chooseGrade();
        String weigh = WeighCourseTextField.getText();
        String id_course = idCourseTextField.getText();
        int w = 0;

        if(stringChooseCollegeName == null){

        }
        else if(stringChooseCollegeName.equals("--")){
            stringChooseCollegeName = "";
        }


        if( weigh == null){

        }
        else if(weigh.equals("")){

        }
        else{
            double a = Double.parseDouble(weigh);
            w = (int) a;
        }

        int id = 0;
        if(id_course == null ){

        }
        else if(id_course.equals("")){

        }
        else{
            double a = Double.parseDouble(id_course);
            id = (int) a;
        }

        Grade grade = null;
        if(stringChooseGrade == null){

        }
        else if(stringChooseGrade.equals("--")){

        }
        else if(stringChooseGrade.equals("BC")){
            grade = Grade.BC;
        }
        else if(stringChooseGrade.equals("MS")){
            grade = Grade.MS;
        }
        else if(stringChooseGrade.equals("PHD")){
            grade = Grade.PHD;
        }


        courses = API.ShowCourses(courses, stringChooseCollegeName, w, grade, id);
        PrintForPane(courses);
    }

    public String  chooseGrade (){
        String stringChooseGrade = (String) GradeField.getValue();
        return stringChooseGrade ;
    }

    public String  chooseCollegeName (){
        String stringCollegeName = (String) CollegeNameField.getValue();
        return stringCollegeName ;
    }


    public void PrintForPane(LinkedList<Course> courses){
        Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        pane.getChildren().clear();
        pane.setPrefHeight(courses.size() * 80 + 30);
        for (int i = 0; i < courses.size(); i++) {

            Label label1 = new Label(courses.get(i).getName());
            label1.prefHeight(142);
            label1.prefWidth(57);
            label1.setLayoutX(14);
            label1.setLayoutY(23 + 80 * i);
            label1.setFont(font);
            pane.getChildren().add(label1);

            Label label2 = new Label(courses.get(i).getCollege().name());
            label2.prefHeight(142);
            label2.prefWidth(57);
            label2.setLayoutX(226);
            label2.setLayoutY(23 + 80 * i);
            label2.setFont(font);
            pane.getChildren().add(label2);

            Label label3 = new Label(courses.get(i).getWeight() + "");
            label3.prefHeight(142);
            label3.prefWidth(57);
            label3.setLayoutX(536);
            label3.setLayoutY(23 + 80 * i);
            label3.setFont(font);
            pane.getChildren().add(label3);

            Label label4 = new Label(courses.get(i).getGrade().name());
            label4.prefHeight(142);
            label4.prefWidth(57);
            label4.setLayoutX(824);
            label4.setLayoutY(23 + 80 * i);
            label4.setFont(font);
            pane.getChildren().add(label4);

            Label label5 = new Label(courses.get(i).getId() + "");
            label5.prefHeight(142);
            label5.prefWidth(57);
            label5.setLayoutX(1005);
            label5.setLayoutY(23 + 80 * i);
            label5.setFont(font);
            pane.getChildren().add(label5);

            String teacherString = getLastNameTeacher(courses.get(i).getId());
            if(teacherString.equals( "")){
                teacherString = "------------";
            }
            else{
                teacherString = "MR/MS. " + teacherString;
            }
            Label label6 = new Label(teacherString);
            label6.prefHeight(142);
            label6.prefWidth(57);
            label6.setLayoutX(1117);
            label6.setLayoutY(23 + 80 * i);
            label6.setFont(font);
            pane.getChildren().add(label6);
        }
    }

    public String getLastNameTeacher(int id){
        for (Teacher teacher :teachers) {
            if(id == teacher.getId()){
                return teacher.getLastname();
            }
        }
        return "";
    }

    public void TeacherListMenuFunction(ActionEvent e) {

    }
}
