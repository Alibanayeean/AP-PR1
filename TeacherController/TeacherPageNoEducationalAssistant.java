package TeacherController;

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
import javafx.scene.control.*;
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

public class TeacherPageNoEducationalAssistant implements Initializable {


    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    Teacher teacher;
    LinkedList<Course> courses = new LinkedList<>();


    @FXML
    javafx.scene.control.MenuBar MenuBar;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Button LogOutButton;

    @FXML
    GridPane gridPane;

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
        teacher.color = colorPicker.getValue();
    }



    @FXML
    void LogOutFunction(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Are you sure?");
        if(alert.showAndWait().get() == ButtonType.OK){
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

    public void setTeacher(LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Course> courses, Teacher teacher){
        this.students = students;
        this.teachers = teachers;
        this.teacher = teacher;
        this.courses = courses;

        if(teacher.image == null){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else if(teacher.image.equals("")){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else if(teacher.image.equals("null")){
            Image image2 = new Image("Images/userIcon.png");
            imageViewUser.setImage(image2);
        }
        else{
            Image image2 = new Image("Images/" + teacher.image);
            imageViewUser.setImage(image2);
        }

        Color color = teacher.color;
        paneFilter.setBackground(new Background(new BackgroundFill(color, null, null)));
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));

        colorPicker.setValue(color);


        Status.setText("Teacher: " + teacher.getTeacherDegree());
        Firstname.setText(teacher.getFirstname());
        Lastname.setText(teacher.getLastname());
        emailLabel.setText(teacher.getEmail());

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



    public void HomeButtonFunction(MouseEvent mouseEvent) {

    }
}
