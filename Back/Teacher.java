package Back;

import javafx.scene.paint.Color;

import java.util.LinkedList;


public class Teacher extends Person{

    private String username = "";
    private boolean isBoss;
    private boolean isEducationalAssistant;
    private LinkedList<Integer> courses;
    private TeacherDegree teacherDegree;
    private int roomNumber;

    public Teacher(Color color, String image, String firstname, String lastname, String email, String password, String phoneNumber, String CodeMelli, College college, boolean isBoss, boolean isEducationalAssistant, LinkedList<Integer> courses, TeacherDegree teacherDegree, String username, int roomNumber) {
        super(color, image, firstname, lastname, email, password, phoneNumber, CodeMelli, college);
        this.isBoss = isBoss;
        this.isEducationalAssistant = isEducationalAssistant;
        this.courses = courses;
        this.teacherDegree = teacherDegree;
        this.username = username;
        this.roomNumber = roomNumber;
    }


//    Getter and Setters:


    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public boolean isEducationalAssistant() {
        return isEducationalAssistant;
    }

    public void setEducationalAssistant(boolean educationalAssistant) {
        isEducationalAssistant = educationalAssistant;
    }

    public LinkedList<Integer> getCourses() {
        return courses;
    }

    public void setCourses(LinkedList<Integer> courses) {
        this.courses = courses;
    }

    public TeacherDegree getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(TeacherDegree teacherDegree) {
        this.teacherDegree = teacherDegree;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    //    Load - ToString - Save

    public String toString() {
        String result =  this.getColor().getRed() + ":::" +
                this.getColor().getBlue() + ":::" +
                this.getColor().getGreen() + ":::" +
                this.getColor().getOpacity() + ":::" +
                this.image + ":::" +
                this.getFirstname() + ":::" + this.getLastname() + ":::" + this.getEmail() + ":::" + this.getPassword() + ":::" + this.getPhoneNumber() + ":::" +
                this.getCodeMelli() + ":::" + this.getCollege() + ":::" + this.isBoss + ":::" + this.isEducationalAssistant + ":::";
        if(courses == null){
            result = result + 0 + ":::";
        }
        else{
            result = result + courses.size() + ":::";
            for (int i = 0; i < courses.size(); i++) {
                result = result + courses.get(i) + ":::";
            }
        }


        result = result + teacherDegree + ":::" + username + ":::" + roomNumber + ":::" + getId() + ":::";
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
        setBoss(Boolean.parseBoolean(strings[12]));
        setEducationalAssistant(Boolean.parseBoolean(strings[13]));
        int size = Integer.parseInt(strings[14]);
        int counter = 15;
        LinkedList<Integer> coursesForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            coursesForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        courses = coursesForSave;

        setTeacherDegree(StringToTeacherDegree(strings[counter]));
        counter++;

        setUsername(strings[counter]);
        counter++;

        roomNumber = Integer.parseInt(strings[counter]);
        counter++;

        setId(Integer.parseInt(strings[counter]));

    }
}


