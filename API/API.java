package API;
import java.util.LinkedList;
import Back.*;

public class API {

    public static LinkedList<Course> ShowCourses(LinkedList<Course> courses, String nameCollege, int Weight ,Grade grade, int id_Course){
        LinkedList<Course> ForReturn = new LinkedList<>();
        CopyLinkedListCourse(ForReturn, courses);

        ShowCoursesWithFilterForNameCollege(ForReturn, nameCollege);
        ShowCoursesWithFilterForWeight(ForReturn, Weight);
        ShowCoursesWithFilterForGrade(ForReturn, grade);
        ShowCoursesWithFilterForId_Course(ForReturn, id_Course);
        return ForReturn;
    }
    public static void CopyLinkedListCourse(LinkedList<Course> ForReturn, LinkedList<Course> courses){
        for (Course course : courses) {
            ForReturn.add(course);
        }
    }
    public static void ShowCoursesWithFilterForNameCollege (LinkedList<Course> ForReturn, String nameCollege){
        if(nameCollege == null){

        }
        else if(nameCollege.equals("")){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(!nameCollege.equals(ForReturn.get(i).getCollege() + "")){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }
    public static void ShowCoursesWithFilterForWeight (LinkedList<Course> ForReturn, int Weight){
        if(Weight <= 0){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(ForReturn.get(i).getWeight() != Weight){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }
    public static void ShowCoursesWithFilterForGrade (LinkedList<Course> ForReturn, Grade grade){
        if(grade == null){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(ForReturn.get(i).getGrade() != grade){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }
    public static void ShowCoursesWithFilterForId_Course (LinkedList<Course> ForReturn, int id){
        if(id <= 0){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(ForReturn.get(i).getId() != id){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }

    }


    public static LinkedList<Teacher> ShowTeachers(LinkedList<Teacher> teachers, String LastName, String nameCollege, TeacherDegree teacherDegree){
        LinkedList<Teacher> ForReturn = new LinkedList<>();
        CopyLinkedListTeacher(ForReturn, teachers);

        ShowTeachersWithFilterForLastName(ForReturn, LastName);
        ShowTeachersWithFilterForNameCollege(ForReturn, nameCollege);
        ShowTeachersWithFilterForTeacherDegree(ForReturn, teacherDegree);
        return ForReturn;
    }
    public static void CopyLinkedListTeacher(LinkedList<Teacher> ForReturn, LinkedList<Teacher> teachers){
        for (Teacher teacher : teachers) {
            ForReturn.add(teacher);
        }
    }
    public static void ShowTeachersWithFilterForLastName (LinkedList<Teacher> ForReturn, String LastName){
        if(LastName == null){

        }
        else if(LastName.equals("")){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(ForReturn.get(i).getLastname().indexOf(LastName) == -1){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }
    public static void ShowTeachersWithFilterForNameCollege (LinkedList<Teacher> ForReturn, String nameCollege){
        if(nameCollege == null){

        }
        else if(nameCollege.equals("")){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(!nameCollege.equals(ForReturn.get(i).getCollege() + "")){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }
    public static void ShowTeachersWithFilterForTeacherDegree (LinkedList<Teacher> ForReturn, TeacherDegree teacherDegree){
        if(teacherDegree == null){

        }
        else{
            int size = ForReturn.size();
            for (int i = 0; i < size; i++) {
                if(ForReturn.get(i).getTeacherDegree() != teacherDegree){
                    ForReturn.remove(i);
                    i = -1;
                    size = ForReturn.size();
                }
            }
        }
    }


    public static LinkedList<Course> GiveCoursesFromAStudent(Student student, LinkedList<Course> courses){
        LinkedList<Course> c = new LinkedList<>();
        for (int i = 0; i < student.getCourses_thisTerm().size(); i++) {
            for (Course co : courses) {
                if(co.getId() == student.getCourses_thisTerm().get(i)){
                    c.add(co);
                }
            }
        }

        return c;

    }



}

