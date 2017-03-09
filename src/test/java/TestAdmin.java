import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leich on 2017/3/8.
 */
public class TestAdmin {
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    @Test
    public void testMakeClass3() {
        Integer invalidCapacity = 0;
        this.admin.createClass("Test", 2017, "Instructor", invalidCapacity);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass4() {
        String uniqueClass = "Test";
        Integer uniqueYear = 2017;
        this.admin.createClass(uniqueClass, uniqueYear, "Instructor", 15);
        this.admin.createClass(uniqueClass, uniqueYear, "Instructor1", 15);
        assertNotEquals("Instructor1",this.admin.getClassInstructor(uniqueClass,uniqueYear));
    }

    @Test
    public void testChangeCapacity() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 1);
        assertEquals(1,this.admin.getClassCapacity("Test", 2017));
    }


    @Test
    public void testChangeCapacity2() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        int inValidCapacity = 0;
        this.admin.changeCapacity("Test", 2017, inValidCapacity);
        assertNotEquals(inValidCapacity,this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacity3() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        String nonExistClass = "Test1";
        this.admin.changeCapacity(nonExistClass, 2017, 14);
        assertEquals(15,this.admin.getClassCapacity("Test", 2017));
        assertNotEquals(14,this.admin.getClassCapacity(nonExistClass, 2017));
    }

    @Test
    public void testChangeCapacity4() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student","Test",2017);
        this.student.registerForClass("Student1","Test",2017);
        int smallCapacity = 1;
        this.admin.changeCapacity("Test", 2017, smallCapacity);
        assertNotEquals(smallCapacity, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testInstructor() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertEquals("Instructor",this.admin.getClassInstructor("Test", 2017));
    }

    @Test
    public void testInstructor2() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertNull(this.admin.getClassInstructor("Test1", 2017));
    }

    @Test
    public void testInstructor3() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertNull(this.admin.getClassInstructor("Test", 2016));
    }
    @Test
    public void testInstructor4(){
        String tiredInstructor = "Instructor";
        this.admin.createClass("Test", 2017, tiredInstructor, 15);
        this.admin.createClass("Test1", 2017, tiredInstructor, 15);
        this.admin.createClass("Test2", 2017, tiredInstructor, 15);
        assertFalse(this.admin.classExists("Test2",2017));
    }
}
