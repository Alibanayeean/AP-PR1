package Back;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Student extends Person{

    private String studentNumber;
    private LinkedList<Integer> courses_thisTerm;
    private LinkedList<Integer> courses_Pass;
    private LinkedList<Integer> courses_Fail;
    private double average_score;
    private Grade grade;
    private int helpTeacher;
    private int YearComeToUniversity;
    private EducationStatus educationStatus;

    public Student(Color color, String image, String firstname, String lastname, String email,
                   String password, String phoneNumber, String CodeMelli, College college,
                   String studentNumber, LinkedList<Integer> courses_thisTerm, LinkedList<Integer> courses_Pass,
                   LinkedList<Integer> courses_Fail, double average_score, int helpTeacher, Grade grade,
                   int YearComeToUniversity, EducationStatus educationStatus) {
        super(color, image, firstname, lastname, email, password, phoneNumber, CodeMelli, college);
        this.studentNumber = studentNumber;
        this.courses_thisTerm = courses_thisTerm;
        this.courses_Pass = courses_Pass;
        this.courses_Fail = courses_Fail;
        this.average_score = average_score;
        this.grade = grade;
        this.helpTeacher = helpTeacher;
        this.YearComeToUniversity = YearComeToUniversity;
        this.educationStatus = educationStatus;
    }

//    Getter and Setters:

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public LinkedList<Integer> getCourses_thisTerm() {
        return courses_thisTerm;
    }

    public void setCourses_thisTerm(LinkedList<Integer> courses_thisTerm) {
        this.courses_thisTerm = courses_thisTerm;
    }

    public LinkedList<Integer> getCourses_Pass() {
        return courses_Pass;
    }

    public void setCourses_Pass(LinkedList<Integer> courses_Pass) {
        this.courses_Pass = courses_Pass;
    }

    public LinkedList<Integer> getCourses_Fail() {
        return courses_Fail;
    }

    public void setCourses_Fail(LinkedList<Integer> courses_Fail) {
        this.courses_Fail = courses_Fail;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getHelpTeacher() {
        return helpTeacher;
    }

    public void setHelpTeacher(int helpTeacher) {
        this.helpTeacher = helpTeacher;
    }

    public int getYearComeToUniversity() {
        return YearComeToUniversity;
    }

    public void setYearComeToUniversity(int yearComeToUniversity) {
        YearComeToUniversity = yearComeToUniversity;
    }

    public EducationStatus getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(EducationStatus educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String toString() {
        String result =  this.getColor().getRed() + ":::" +
                this.getColor().getBlue() + ":::" +
                this.getColor().getGreen() + ":::" +
                this.getColor().getOpacity() + ":::" +
                this.image + ":::" +
                this.getFirstname() + ":::" + this.getLastname() + ":::" + this.getEmail() + ":::" + this.getPassword() + ":::" + this.getPhoneNumber() + ":::" +
                this.getCodeMelli() + ":::" + this.getCollege() + ":::" + this.studentNumber + ":::";
        if(courses_thisTerm == null){
            result = result + 0 + ":::";
        }
        else{
            result = result + courses_thisTerm.size() + ":::";
            for (int i = 0; i < courses_thisTerm.size(); i++) {
                result = result + courses_thisTerm.get(i) + ":::";
            }
        }

        if(courses_Pass == null){
            result = result + 0 + ":::";
        }
        else{
            result = result + courses_Pass.size() + ":::";
            for (int i = 0; i < courses_Pass.size(); i++) {
                result = result + courses_Pass.get(i) + ":::";
            }
        }

        if(courses_Fail == null){
            result = result + 0 + ":::";
        }
        else{
            result = result + courses_Fail.size() + ":::";
            for (int i = 0; i < courses_Fail.size(); i++) {
                result = result + courses_Fail.get(i) + ":::";
            }
        }





        result = result + average_score + ":::" + helpTeacher + ":::" + grade + ":::" + YearComeToUniversity + ":::" + educationStatus + ":::" + id + ":::";
        return result + "\n";
    }
    public String save(){
        return  this.toString();
    }
    public void load(String s) {
        String[] strings = s.split(":::");
        setColor(new Color(Float.parseFloat(strings[0]), Float.parseFloat(strings[1]), Float.parseFloat(strings[2]) ,Float.parseFloat(strings[3])));
        setImage(strings[4]);
        setFirstname(strings[5]);
        setLastname(strings[6]);
        setEmail(strings[7]);
        setPassword(strings[8]);
        setPhoneNumber(strings[9]);
        setCodeMelli(strings[10]);
        setCollege(StringToCollege(strings[11]));
        setStudentNumber(strings[12]);

        int size = Integer.parseInt(strings[13]);
        int counter = 14;
        LinkedList<Integer> coursesThisTermForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            coursesThisTermForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        courses_thisTerm = coursesThisTermForSave;

        size = Integer.parseInt(strings[counter]);
        counter++;
        LinkedList<Integer> coursesPassTermForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            coursesPassTermForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        courses_Pass = coursesPassTermForSave;

        size = Integer.parseInt(strings[counter]);
        counter++;
        LinkedList<Integer> coursesFailTermForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            coursesFailTermForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        courses_Fail = coursesFailTermForSave;

        average_score = Double.parseDouble(strings[counter]);
        counter++;

        helpTeacher = Integer.parseInt(strings[counter]);
        counter++;

        setGrade(StringToGrade(strings[counter]));
        counter++;

        YearComeToUniversity = Integer.parseInt(strings[counter]);
        counter++;

        educationStatus = StringToEducationStatus(strings[counter]);
        counter++;

        setId(Integer.parseInt(strings[counter]));
    }

}


