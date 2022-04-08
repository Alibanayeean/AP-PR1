package publicController;
import StudentController.StudentPage;
import TeacherController.TeacherPageEducationalAssistant;
import TeacherController.TeacherPageNoEducationalAssistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import Back.*;


public class LoginPage {

    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    LinkedList<Course> courses = new LinkedList<>();


    @FXML
    Button LoginButton;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField captchaField;

    @FXML
    ImageView imageView;

    @FXML
    Button Recaptcha;

    int input = 0;
    Stage stage;


    @FXML
    void Login(ActionEvent e) {

        Student studentLogin = null;
        Teacher teacherLogin = null;

        for (Student student : students) {
            if(student.getStudentNumber().equals(usernameField.getText()) & student.getPassword().equals(passwordField.getText())){
                studentLogin =  student;
                break;
            }
        }

        for (Teacher teacher : teachers) {
            if(teacher.getUsername().equals(usernameField.getText()) & teacher.getPassword().equals(passwordField.getText())){
                teacherLogin =  teacher;
                break;
            }
        }

        if(teacherLogin == null & studentLogin == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Username or password incorrect");
            alert.show();
            setImageView(students, teachers, courses);
            return;
        }



        boolean b = ValidCaptchaInput();
        if(!b){
            setImageView(students, teachers, courses);
            return;
        }

        if(studentLogin != null){
            try {
                stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BCPage.fxml"));
                Parent root = loader.load();
                StudentPage studentPage = loader.getController();
                studentPage.setStudent(students, teachers, courses, studentLogin);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }

        if(teacherLogin != null){
            try {
                if(teacherLogin.isEducationalAssistant()){
                    stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TeacherPageEducationalAssistant.fxml"));
                    Parent root = loader.load();
                    TeacherPageEducationalAssistant teacherPageEducationalAssistant = loader.getController();
                    teacherPageEducationalAssistant.setTeacher(students, teachers, courses, teacherLogin);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TeacherPageNoEducationalAssistant.fxml"));
                    Parent root = loader.load();
                    TeacherPageNoEducationalAssistant teacherPageNoEducationalAssistant = loader.getController();
                    teacherPageNoEducationalAssistant.setTeacher(students, teachers, courses, teacherLogin);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }

    }

    public void setImageView( LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Course> courses) {
        Random random = new Random();
        input = random.nextInt(5);
        input++;
        Image image = new Image("Images/captcha"+ input +".png");
        imageView.setImage(image);
        Image image1 = new Image("Images/recaptcha.png");
        Recaptcha.setGraphic(new ImageView(image1));
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;

    }

    public void ChangeCaptchaButton(ActionEvent event) {
        setImageView(students, teachers, courses);
    }

    public boolean ValidCaptchaInput(){
        String s = captchaField.getText();
        if(input == 1){
            if(s.equals("74853"))
                return true;
        }
        else if(input == 2){
            if(s.equals("cg5dd"))
                return true;
        }
        else if(input == 3){
            if(s.equals("b5nmm"))
                return true;
        }
        else if(input == 4){
            if(s.equals("mxyxw"))
                return true;
        }
        else if(input == 5){
            if(s.equals("c7gb3"))
                return true;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect captcha input");
        alert.show();
        return false;
    }

    public void mouseMovedLogin(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitLogin(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

    public void mouseMovedRecaptcha(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitRecaptcha(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

}



