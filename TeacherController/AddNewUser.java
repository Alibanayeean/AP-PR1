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


public class AddNewUser implements Initializable {
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
    Label timeShowLabel;

    @FXML
    Button HomeButton;

    @FXML
    Button AddButton;

    @FXML
    ChoiceBox GradeChoice;

    @FXML
    ChoiceBox TeacherAssistantChoiceBox;

    @FXML
    ChoiceBox CollegeChoiceBox;

    @FXML
    TextField FirstnameTextField;

    @FXML
    TextField LastnameTextField;

    @FXML
    TextField EmailTextField;

    @FXML
    TextField PhoneNumberTextField;

    @FXML
    TextField UsernameTextField;

    @FXML
    PasswordField Password1TextField;

    @FXML
    PasswordField Password2TextField;

    @FXML
    TextField IdentityCodeTextField;


    Stage stage;

    @FXML
    void ChangingColor(ActionEvent event) {
        Color color = colorPicker.getValue();
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        AddButton.setBackground(new Background(new BackgroundFill(color, null, null)));

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


        String[] choices_Grade = {"PHD" , "MS" , "BC"};
        GradeChoice.getItems().addAll(choices_Grade);

        String[] choices_College = {"EE", "CE", "CS", "Math", "Physics", "Chemistry", "Mechanic"};
        CollegeChoiceBox.getItems().addAll(choices_College);

    }

    public void setTeacher(LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Course> courses, Teacher teacher){
        this.students = students;
        this.teachers = teachers;
        this.teacher = teacher;
        this.courses = courses;

        Color color = teacher.color;
        MenuBar.setBackground(new Background(new BackgroundFill(color, null, null)));
        timeShowLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        HomeButton.setBackground(new Background(new BackgroundFill(color, null, null)));
        AddButton.setBackground(new Background(new BackgroundFill(color, null, null)));

        colorPicker.setValue(color);


        String[] teachersForChoiceBoxAdd = new String[teachers.size()];
        for (int i = 0; i < teachers.size(); i++) {
            teachersForChoiceBoxAdd[i] = teachers.get(i).getLastname();
        }
        TeacherAssistantChoiceBox.getItems().addAll(teachersForChoiceBoxAdd);
    }

    public void HomeButtonFunction(ActionEvent e) {

        try {
            stage = ((Stage)(((Scene)((Node)(e.getSource())).getScene()).getWindow()));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TeacherPageEducationalAssistant.fxml"));
            Parent root = null;
            root = loader.load();
            TeacherPageEducationalAssistant teacherPageEducationalAssistant = loader.getController();
            teacherPageEducationalAssistant.setTeacher(students, teachers, courses, teacher);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public boolean validInputForAddStudent(String s){
        for (int i = 0; i < s.length() - 3; i++) {
               if(s.charAt(i) == ':' & s.charAt(i + 1) == ':' & s.charAt(i + 2) == ':'){
                   return false;
               }
        }
        return true;
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


    public void AddNewUserFunction(ActionEvent e) {

        String stringChooseGrade = chooseGrade();

        if(stringChooseGrade == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Choose one of grades!!");
            alert.showAndWait();
            return;
        }

        String chooseTeacher = chooseTeacher();
        if(chooseTeacher == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Choose one of Teacher Assistant!!");
            alert.showAndWait();
            return;
        }

        String chooseCollege = chooseCollege();
        if(chooseCollege == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Choose one of College!!");
            alert.showAndWait();
            return;
        }

        if(FirstnameTextField.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("First name field is empty");
            alert.showAndWait();
            return;
        }
        if(!validInputForAddStudent(FirstnameTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("First name should not have :::");
            alert.showAndWait();
            return;
        }

        if(LastnameTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Last name field is empty");
            alert.showAndWait();
            return;
        }
        if(!validInputForAddStudent(LastnameTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Last name should not have :::");
            alert.showAndWait();
            return;
        }

        if(EmailTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Email field is empty");
            alert.showAndWait();
            return;
        }
        if(!validInputForAddStudent(EmailTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Email should not have :::");
            alert.showAndWait();
            return;
        }

        if(true){
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
        }
        if(PhoneNumberTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Phone number field is empty");
            alert.showAndWait();
            return;
        }
        if(true){
            for (int i = 0; i < PhoneNumberTextField.getText().length() ; i++) {
                if(!(PhoneNumberTextField.getText().charAt(i) >= '0' & PhoneNumberTextField.getText().charAt(i) <= '9')){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect");
                    alert.setHeaderText("Phone number should be number!!!");
                    alert.showAndWait();
                    return;
                }
            }
        }

        if(IdentityCodeTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Identity code field is empty");
            alert.showAndWait();
            return;
        }
        if(!validInputForAddStudent(IdentityCodeTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Identity code should not have :::");
            alert.showAndWait();
            return;
        }

        if(UsernameTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Student number field is empty");
            alert.showAndWait();
            return;
        }
        if(!validInputForAddStudent(UsernameTextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Student number should not have :::");
            alert.showAndWait();
            return;
        }

        if(true){
            for (int i = 0; i < UsernameTextField.getText().length() ; i++) {
                if(!(UsernameTextField.getText().charAt(i) >= '0' & UsernameTextField.getText().charAt(i) <= '9')){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect");
                    alert.setHeaderText("Student number should be number!!!");
                    alert.showAndWait();
                    return;
                }
            }
            for (Student student : students) {
                if(UsernameTextField.getText().equals(student.getStudentNumber())){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate username");
                    alert.setHeaderText("Sorry, this Student number has already been chosen");
                    alert.showAndWait();
                    return;
                }
            }
            for (Teacher teacher : teachers) {
                if(UsernameTextField.getText().equals(teacher.getUsername())){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate username");
                    alert.setHeaderText("Sorry, this Student number has already been chosen");
                    alert.showAndWait();
                    return;
                }
            }
        }

        if(Password1TextField.getText().equals("") |  Password2TextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Password field is empty");
            alert.showAndWait();
            return;
        }


        if(true){
            if(!Password1TextField.getText().equals(Password2TextField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect");
                alert.setHeaderText("the two Password field aren't equal");
                alert.showAndWait();
                return;
            }
        }
        if(!validInputForAddStudent(Password1TextField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect");
            alert.setHeaderText("Password should not have :::");
            alert.showAndWait();
            return;
        }

        Grade grade = StringToGrade(stringChooseGrade);
        College college = StringToCollege(chooseCollege);

        Teacher teacher = null;
        for (Teacher teacher1 : teachers) {
            if(teacher1.getLastname().equals(chooseTeacher)){
                teacher = teacher1;
                break;
            }
        }
        if(teacher == null){

        }
        else{
            LocalDateTime instance = LocalDateTime.now();

            students.add(new Student(Color.BLUE, null, FirstnameTextField.getText(), LastnameTextField.getText(), EmailTextField.getText(), Password1TextField.getText(), PhoneNumberTextField.getText() ,IdentityCodeTextField.getText(), college , UsernameTextField.getText(), null, null, null , 0.0 , teacher.getId(), grade, instance.getYear(), EducationStatus.Studying));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Added successfully");
            alert.setHeaderText("Student added successfully");
            alert.showAndWait();
        }
    }

    public College StringToCollege(String s){

        if(s.equals("EE")){
            return College.EE;
        }
        else if(s.equals("CE")){
            return College.CE;
        }
        else if(s.equals("CS")){
            return College.CS;
        }
        else if(s.equals("Math")){
            return College.Math;
        }
        else if(s.equals("Physics")){
            return College.Physics;
        }
        else if(s.equals("Chemistry")){
            return College.Chemistry;
        }
        else if(s.equals("Mechanic")){
            return College.Mechanic;
        }
        else{
            return null;
        }
    }

    public Grade StringToGrade(String s){

        if(s.equals("BC")){
            return Grade.BC;
        }
        else if(s.equals("MS")){
            return Grade.MS;
        }
        else if(s.equals("PHD")){
            return Grade.PHD;
        }
        else{
            return null;
        }
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

    public void mouseMovedAddButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.HAND);
    }

    public void mouseExitAddButton(MouseEvent e) {
        Scene scene;
        scene = ((Scene)((Node)(e.getSource())).getScene());
        scene.setCursor(Cursor.DEFAULT);
    }

    public String  chooseGrade (){
        String stringChooseGrade = (String) GradeChoice.getValue();
        return stringChooseGrade ;
    }

    public String  chooseTeacher (){
        String stringChooseGrade = (String) TeacherAssistantChoiceBox.getValue();
        return stringChooseGrade ;
    }

    public String  chooseCollege (){
        String stringChooseCollege = (String) CollegeChoiceBox.getValue();
        return stringChooseCollege ;
    }

}

