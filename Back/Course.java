package Back;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Objects;

public class Course {

    private String name;
    private College college;
    private LinkedList<Integer> prerequisites;
    private LinkedList<Integer> requisites;
    private int id;
    private static int id_counter = 2000;
    private double weight;
    private  Grade grade;
    private int yearExam;
    private int monthExam;
    private int dayExam;
    private LinkedList<Days> days;
    private double HourBeginClass;
    private double HourEndClass;

    public Course(String name, College college, LinkedList<Integer> prerequisites, LinkedList<Integer> requisites,
                  double weight, Grade grade, int yearExam, int monthExam, int dayExam, LinkedList<Days> days, double HourBeginClass, double HourEndClass) {
        this.name = name;
        this.college = college;
        this.prerequisites = prerequisites;
        this.requisites = requisites;
        this.id = id_counter;
        id_counter++;
        this.weight = weight;
        this.grade = grade;
        this.dayExam = dayExam;
        this.monthExam = monthExam;
        this.yearExam = yearExam;
        this.days = days;
        this.HourBeginClass = HourBeginClass;
        this.HourEndClass = HourEndClass;

    }



//    Getter and Setters:


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public LinkedList<Integer> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(LinkedList<Integer> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public LinkedList<Integer> getRequisites() {
        return requisites;
    }

    public void setRequisites(LinkedList<Integer> requisites) {
        this.requisites = requisites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getYearExam() {
        return yearExam;
    }

    public void setYearExam(int yearExam) {
        this.yearExam = yearExam;
    }

    public int getMonthExam() {
        return monthExam;
    }

    public void setMonthExam(int monthExam) {
        this.monthExam = monthExam;
    }

    public int getDayExam() {
        return dayExam;
    }

    public void setDayExam(int dayExam) {
        this.dayExam = dayExam;
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

    public Days StringToDays(String s){
        if(s.equals("Monday")){
            return Days.Monday;
        }
        else if(s.equals("Tuesday")){
            return Days.Tuesday;
        }
        else if(s.equals("Wednesday")){
            return Days.Wednesday;
        }
        else if(s.equals("Thursday")){
            return Days.Thursday;
        }
        else if(s.equals("Friday")){
            return Days.Friday;
        }
        else if(s.equals("Saturday")){
            return Days.Saturday;
        }
        else if(s.equals("Sunday")){
            return Days.Sunday;
        }
        else{
            return null;
        }
    }

    public LinkedList<Days> getDays() {
        return days;
    }

    public void setDays(LinkedList<Days> days) {
        this.days = days;
    }

    public double getHourBeginClass() {
        return HourBeginClass;
    }

    public void setHourBeginClass(double hourBeginClass) {
        HourBeginClass = hourBeginClass;
    }

    public double getHourEndClass() {
        return HourEndClass;
    }

    public void setHourEndClass(double hourEndClass) {
        HourEndClass = hourEndClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    public String toString() {
        String result =  name  + ":::" + college + ":::";
        if(prerequisites == null){
            result = result + 0 + ":::";
            prerequisites = new LinkedList<>();
        }
        else{
            result = result + prerequisites.size() + ":::";
        }

        for (int i = 0; i < prerequisites.size(); i++) {
            result = result + prerequisites.get(i) + ":::";
        }

        if(requisites == null){
            result = result + 0 + ":::";
            requisites = new LinkedList<>();
        }
        else{
            result = result + requisites.size() + ":::";
        }

        for (int i = 0; i < requisites.size(); i++) {
            result = result + requisites.get(i) + ":::";
        }

        if(days == null){
            result = result + 0 + ":::";
            days = new LinkedList<>();
        }
        else{
            result = result + days.size() + ":::";
        }

        for (int i = 0; i < days.size(); i++) {
            result = result + days.get(i) + ":::";
        }

        result = result + weight + ":::" + grade + ":::" + yearExam + ":::" + monthExam + ":::" + dayExam + ":::" + HourBeginClass + ":::" +  HourEndClass + ":::" ;
        return result + "\n";
    }
    public String save(){
        return  this.toString();
    }
    public void load(String s) {
        String[] strings = s.split(":::");
        setName(strings[0]);
        setCollege(StringToCollege(strings[1]));
        int size = Integer.parseInt(strings[2]);
        int counter = 3;
        LinkedList<Integer> prerequisitesForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            prerequisitesForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        prerequisites = prerequisitesForSave;

        size = Integer.parseInt(strings[counter]);
        counter++;
        LinkedList<Integer> requisitesForSave = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            requisitesForSave.add(Integer.parseInt(strings[counter]));
            counter++;
        }
        requisites = requisitesForSave;

        LinkedList<Days> daysForSave = new LinkedList<>();
        size = Integer.parseInt(strings[counter]);
        counter++;

        for (int i = 0; i < size; i++) {
            daysForSave.add(StringToDays(strings[counter]));
            counter++;
        }

        days = daysForSave;

        setWeight(Double.parseDouble(strings[counter]));
        counter++;

        setGrade(StringToGrade(strings[counter]));
        counter++;

        yearExam = Integer.parseInt(strings[counter]);
        counter++;

        monthExam = Integer.parseInt(strings[counter]);
        counter++;

        dayExam = Integer.parseInt(strings[counter]);
        counter++;

        HourBeginClass = Double.parseDouble(strings[counter]);
        counter++;

        HourEndClass = Double.parseDouble(strings[counter]);
        counter++;
    }

}
