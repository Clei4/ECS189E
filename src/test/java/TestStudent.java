import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by leich on 2017/3/8.
 */
public class TestStudent {
    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;
    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
        this.admin.createClass("Class", 2017, "Instructor", 2);
    }

    @Test
    public void testRegister() {
        this.student.registerForClass("Student","Class",2017);
        assertTrue(this.student.isRegisteredFor("Student","Class",2017));
    }

/*    @Test(expected = IllegalArgumentException.class)
    public void testRegister2() {
        String studentExisted = "Student";
        this.student.registerForClass(studentExisted,"Class",2017);
        this.student.registerForClass(studentExisted,"Class",2017);
    }*/

    @Test
    public void testRegister3() {
        String nonExistClass = "Class1";
        this.student.registerForClass("Student",nonExistClass,2017);
        assertFalse( this.student.isRegisteredFor("Student",nonExistClass,2017));
    }

    @Test
    public void testRegister4() {
        String fullClass = "Class";
        this.student.registerForClass("Student",fullClass,2017);
        this.student.registerForClass("Student1",fullClass,2017);
        this.student.registerForClass("Student2",fullClass,2017);
        assertFalse( this.student.isRegisteredFor("Student2",fullClass,2017));
    }
    @Before
    public void setup1() {
        this.student.registerForClass("Student","Class",2017);
        this.instructor.addHomework("Instructor", "Class", 2017, "Homework", "testHomework");

    }
    @Test
    public void testDrop(){
        this.student.dropClass("Student","Class",2017);
        assertFalse(this.student.isRegisteredFor("Student","Class",2017));
    }

/*    @Test(expected = IllegalArgumentException.class)
    public void testDrop1(){
        String nonExistStudent = "Student1";
        this.student.dropClass(nonExistStudent,"Class",2017);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrop2(){
        String nonExistClass = "Class1";
        this.student.dropClass("Student",nonExistClass,2017);
    }*/

    @Test
    public void testSubmit(){
        this.student.submitHomework("Student","Homework","Answer","Class",2017);
        assertTrue(this.student.hasSubmitted("Student","Homework","Class",2017));
    }

    @Test
    public void testSubmit2(){
        this.student.registerForClass("Student1","Class",2017);
        assertFalse(this.student.hasSubmitted("Student1","Homework","Class",2017));
    }

    @Before
    public void setup2() {
        this.student.submitHomework("Student","Homework","Answer","Class",2017);
    }
    @Test
    public void testSubmit3(){
        String nonExistStudent = "Student1";//maybe this test is useless because if the program throw exception in functions like find students, this function should already been tested in TestInstructor
        assertFalse(this.student.hasSubmitted(nonExistStudent,"Homework","Class",2017));
    }
    @Test
    public void testSubmit4(){
        String nonExistHomework = "Homework1";
        assertFalse(this.student.hasSubmitted("Student",nonExistHomework,"Class",2017));
    }
    @Test
    public void testSubmit5(){
        String nonExistClass = "Class1";
        assertFalse(this.student.hasSubmitted("Student","Homework",nonExistClass,2017));
    }
}
