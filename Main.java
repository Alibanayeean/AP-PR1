
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import publicController.LoginPage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

import Back.*;


public class Main extends Application {
    LinkedList<Student> students = new LinkedList<>();
    LinkedList<Teacher> teachers = new LinkedList<>();
    LinkedList<Course> courses = new LinkedList<>();

    static String path = "./src/Load/Classes/ClassesLoad.txt";
    private static FileOutputStream fileOutputStream;

    public static void Load(LinkedList<Student> students , LinkedList<Teacher> teachers, LinkedList<Course> courses, String path) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner scanner2 = new Scanner(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file , true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        while(scanner2.hasNext()){
            String str = scanner2.nextLine();
            if (str.equals("Student")) {
                Color c = Color.YELLOW;
                Student student = new Student( c , "a" , "a" , "a" , "a", "a", "a",
                        "a", College.CE, "a", null, null, null, 0.0, 0, Grade.BC, 100, EducationStatus.Studying);
                student.load(scanner2.nextLine());
                students.add(student);
            }
            else if (str.equals("Teacher")) {
                Color c = Color.YELLOW;
                Teacher teacher = new Teacher( c , "a" , "a" , "a" , "a", "a", "a",
                        "a", College.CE, false, false, null, TeacherDegree.AssistantProfessor, "a", 10);
                teacher.load(scanner2.nextLine());
                teachers.add(teacher);
            }
            else if (str.equals("Course")) {
                Course course = new Course(  "a" , College.CE , null , null, 0, Grade.BC, 10 , 10 , 10, null, 0, 0);
                course.load(scanner2.nextLine());
                courses.add(course);
            }
        }
        scanner2.close();
    }

    public static void Save (LinkedList<Student> students , LinkedList<Teacher> teachers, LinkedList<Course> courses, String path) {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append("Student\n");
            sb.append(student.save());
        }
        for (Teacher teacher : teachers) {
            sb.append("Teacher\n");
            sb.append(teacher.save());
        }
        for (Course course : courses) {
            sb.append("Course\n");
            sb.append(course.save());
        }
        try {
            SaveToFileString(path , sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveToFileString(String path ,String value) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner scanner2 = new Scanner(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(value);
        scanner2.close();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception { ;

        Load(students , teachers , courses, path);

//        LinkedList<Days> days3 = new LinkedList<>();
//        days3.add(Days.Saturday);
//        days3.add(Days.Thursday);
//        Course course3 = new Course("Math1" , College.Math, null, null, 4, Grade.BC, 1400, 11, 7, days3, 13, 15);
//
//        LinkedList<Integer> reBp = new LinkedList();
//        reBp.add(course3.getId());
//
//        LinkedList<Days> days1 = new LinkedList<>();
//        days1.add(Days.Monday);
//        days1.add(Days.Saturday);
//        Course course1 = new Course("BP" , College.CS, null, reBp, 4, Grade.BC, 1400, 10, 26, days1, 10.5, 12.5);
//
//
//        LinkedList<Days> days2 = new LinkedList<>();
//        days2.add(Days.Saturday);
//        days2.add(Days.Sunday);
//        Course course2 = new Course("BP" , College.CE, null, reBp, 3, Grade.BC, 1400, 10, 27, days2, 11.5, 1);
//
//        LinkedList<Days> days4 = new LinkedList<>();
//        days4.add(Days.Thursday);
//        days4.add(Days.Wednesday);
//        Course course4 = new Course("Physic1" , College.Physics, null, reBp, 3, Grade.BC, 1400, 10, 31, days4, 15, 16.5);
//
//        LinkedList<Integer> preAp = new LinkedList();
//        preAp.add(course1.getId());
//        preAp.add(course3.getId());
//
//
//        LinkedList<Days> days5 = new LinkedList<>();
//        days5.add(Days.Thursday);
//        days5.add(Days.Wednesday);
//        Course course5 = new Course("AP" , College.CS, preAp, null, 4, Grade.BC, 1401, 4, 10, days5, 18, 20);
//
//        LinkedList<Days> days6 = new LinkedList<>();
//        days6.add(Days.Thursday);
//        days6.add(Days.Friday);
//        Course course6 = new Course("AP" , College.CE, preAp, null, 3, Grade.BC, 1401, 4 , 5, days6, 10, 11.5);
//
//        LinkedList<Integer> preGossaste = new LinkedList();
//        preGossaste.add(course3.getId());
//
//        LinkedList<Days> days7 = new LinkedList<>();
//        days7.add(Days.Thursday);
//        Course course7 = new Course("Discrete structure" , College.CS, preGossaste, null, 3, Grade.BC, 1401, 4 , 6, days7, 11, 14);
//
//
//        LinkedList<Days> days8 = new LinkedList<>();
//        days8.add(Days.Thursday);
//        days8.add(Days.Wednesday);
//        Course course8 = new Course("Discrete structure" , College.CE, preGossaste, null, 3, Grade.BC, 1401, 4, 7, days8, 12, 13.5);
//
//
//        LinkedList<Integer> courses22 = new LinkedList();
//        courses22.add(course5.getId());
//        Teacher tefagh = new Teacher(Color.RED, null,  "Mojtaba" , "Tefagh" , "m.e@" , "1" ,"98902", "054461" ,College.CS, false, false, courses22, TeacherDegree.AssistantProfessor, "1", 140);
//
//
//        LinkedList<Integer> coursesKhazayee = new LinkedList();
//        coursesKhazayee.add(course3.getId());
//        Teacher khazayee = new Teacher(Color.RED, null,"Shahram" , "Khazayee" , "s.kh@" , "2","98985", "8456463", College.CS, false, true, coursesKhazayee ,TeacherDegree.AssociateProfessor, "2" , 143);
//
//
//        LinkedList<Integer> Fanaicourses = new LinkedList();
//        Fanaicourses.add(course7.getId());
//        Teacher Fanai = new Teacher(Color.BLUE, null ,"Hamidreza" , "Fanai" , "h.f@" , "3" ,"9898", "34536" ,College.CS, true, false, Fanaicourses ,TeacherDegree.FullProfessor, "3", 180);
//
//
//        teachers.add(tefagh);
//        teachers.add(khazayee);
//        teachers.add(Fanai);
//
//        LinkedList<Integer> CourseThisterm = new LinkedList();
//        CourseThisterm.add(course5.getId());
//        CourseThisterm.add(course7.getId());
//
//        Student ali = new Student(Color.RED, "ali.jpg", "Ali" , "Banayeean" , "a.@" , "alavi","902798", "2283763614" ,College.CS ,"400108785", CourseThisterm, null , null, 19.0, tefagh.getId(), Grade.BC, 1400, EducationStatus.Studying );
//
//        Student reza = new Student(Color.GRAY, null, "Reza" , "Bana" , "r.@" , "r" , "902", "228", College.CE, "1", CourseThisterm, null , null, 18.0, khazayee.getId() , Grade.BC, 1399, EducationStatus.Studying);
//        students.add(ali);
//        students.add(reza);
//
//        courses.add(course1);
//        courses.add(course2);
//        courses.add(course3);
//        courses.add(course4);
//        courses.add(course5);
//        courses.add(course6);
//        courses.add(course7);
//        courses.add(course8);

        Save(students, teachers, courses, path);





        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();
        LoginPage loginPage = loader.getController();
        loginPage.setImageView(students ,teachers, courses);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Close programme");
            alert.setHeaderText("Are you sure?");
            if(alert.showAndWait().get() == ButtonType.OK){
                primaryStage.close();
            }
        });

    }



}
