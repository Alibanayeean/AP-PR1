package Back;

import javafx.scene.paint.Color;

public class Person {
    static int id_counter;
    protected int id;
    public Color color;
    public String image;
    private String Firstname;
    private String Lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private String CodeMelli;
    private College college;
    private int lastHourLogin;

    public Person(Color color, String image, String firstname, String lastname, String email, String password, String phoneNumber, String CodeMelli, College college) {
        this.color = color;
        this.image = image;
        id = id_counter;
        id_counter++;
        Firstname = firstname;
        Lastname = lastname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.CodeMelli = CodeMelli;
        this.college = college;
    }

    public static int getId_counter() {
        return id_counter;
    }

    public static void setId_counter(int id_counter) {
        Person.id_counter = id_counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodeMelli() {
        return CodeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        CodeMelli = codeMelli;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public int getLastHourLogin() {
        return lastHourLogin;
    }

    public void setLastHourLogin(int lastHourLogin) {
        this.lastHourLogin = lastHourLogin;
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

    public EducationStatus StringToEducationStatus(String s){
        if(s.equals("Studying")){
            return EducationStatus.Studying;
        }
        else if(s.equals("Graduated")){
            return EducationStatus.Graduated;
        }
        else if(s.equals("Refuse")){
            return EducationStatus.Refuse;
        }
        else{
            return null;
        }
    }

    public TeacherDegree StringToTeacherDegree(String s){

        if(s.equals("AssistantProfessor")){
            return TeacherDegree.AssistantProfessor;
        }
        else if(s.equals("AssociateProfessor")){
            return TeacherDegree.AssociateProfessor;
        }
        else if(s.equals("FullProfessor")){
            return TeacherDegree.FullProfessor;
        }
        else{
            return null;
        }
    }


}
