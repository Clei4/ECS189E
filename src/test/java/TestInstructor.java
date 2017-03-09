import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leich on 2017/3/8.
 */
public class TestInstructor {
    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;
    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
        this.admin.createClass("Class", 2017, "Instructor", 15);
    }

    @Test
    public void testMakeHomeWork() {
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "testHomework");
        assertTrue(this.instructor.homeworkExists("Class", 2017, "Homework"));
    }

    @Test
    public void testMakeHomeWork2() {
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "testHomework");
        assertFalse(this.instructor.homeworkExists("Class1", 2017, "Homework"));
    }

/*    @Test(expected = IllegalArgumentException.class)
    public void testMakeHomeWork3() {
        String homeworkExisted = "Homework";
        this.instructor.addHomework("Instructor", "Class", 2017, homeworkExisted, "testHomework");
    }*/

    @Test
    public void testMakeHomeWork4() {
        String invalidInstructor = "Instructor1";
        this.instructor.addHomework(invalidInstructor,"Class",2017,"Homework","HomeworkDescription");
        assertFalse(this.instructor.homeworkExists("Class", 2017, "Homework"));
    }
    @Test
    public void testMakeHomeWork5() {
        String invalidClassName = "Class1";
        this.instructor.addHomework("Instructor",invalidClassName,2017,"Homework","HomeworkDescription");
        assertFalse(this.instructor.homeworkExists(invalidClassName, 2017, "Homework"));
    }
    @Before
    public void setup1(){
        this.student.registerForClass("Student","Class",2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "testHomework");
        this.student.submitHomework("Student","Homework","Answer","Class",2017);
    }
    @Test
    public void testGrade(){
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", 20);
        Integer expectedGrade = 20;
        assertEquals(expectedGrade,this.instructor.getGrade("Class",2017,"Homework","Student"));
    }

    @Test
    public void testGrade2(){
        Integer invalidGrade = -1;
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", invalidGrade);
        assertNotEquals(invalidGrade,this.instructor.getGrade("Class",2017,"Homework","Student"));
    }

    @Test
    public void testGrade3(){
        Integer invalidGrade = 101;
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", "Student", invalidGrade);
        assertNotEquals(invalidGrade,this.instructor.getGrade("Class",2017,"Homework","Student"));
    }

    @Test
    public void testGrade4(){
        String illegalInstructor = "Instructor1";
        Integer possibleGrade = 99;
        this.instructor.assignGrade(illegalInstructor, "Class", 2017, "Homework", "Student", possibleGrade);
        assertNotEquals( possibleGrade, this.instructor.getGrade("Class",2017,"Homework","Student"));

    }
    @Test
    public void testGrade5(){
        String illegalClassName = "Class1";
        Integer possibleGrade = 99;
        this.instructor.assignGrade("Instructor", illegalClassName, 2017, "Homework", "Student", possibleGrade);
        assertNotEquals( possibleGrade, this.instructor.getGrade(illegalClassName,2017,"Homework","Student"));

    }

    @Test
    public void testGrade6(){
        String nonExistHomework = "Homework1";
        Integer possibleGrade = 99;
        this.instructor.assignGrade("Instructor", "Class", 2017, nonExistHomework, "Student", possibleGrade);
        assertNotEquals( possibleGrade, this.instructor.getGrade("Class",2017,nonExistHomework,"Student"));

    }

    @Test
    public void testGrade7(){
        String nonExistStudent = "Student1";
        Integer possibleGrade = 99;
        this.instructor.assignGrade("Instructor", "Class", 2017, "Homework", nonExistStudent, possibleGrade);
        assertNotEquals( possibleGrade, this.instructor.getGrade("Class",2017,"Homework",nonExistStudent));

    }
    //since the year has been tested in the admin, if the year is wrong, the system should report something like wrong class or wrong instructor instead of a wrong year
}
