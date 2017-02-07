// Thelma Andrews,CSC526,Homework2 (Part3)
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;


public class ScheduleTest {
    public final String[] courseStrings = {
            "EGR 222,3,MWF,05:00 PM,60",
            "EGR 333,4,MWF,04:30 PM,30",
            "EGR 444,1,R,12:00 PM,60",
            "EGR 555,2,RF,02:00 AM,15"
    };
    @Test
    public void scheduleConstructorTest(){
        Schedule s = new Schedule();
        for(String str : courseStrings){
            try{
                String[] tokens = str.split(",");
                s.add(new Course(tokens[0],
                        Integer.parseInt(tokens[1]),
                        CourseInstructorTest.toEnumSet(tokens[2]),
                        Time.fromString(tokens[3]), Integer.parseInt(tokens[4])));
            }catch(Exception e){
                //Assert.fail("Bug in the test case, courses in courseStrings should not conflict");
            }
        }
        Collection<Course> courses = s.getAllCourses();
        Assert.assertTrue(courses != null);
        Assert.assertEquals(4, courses.size());
    }
    @Test
    public void scheduleContainsTest(){
        Schedule s = new Schedule();
        for(String str : courseStrings){
            try{
                String[] tokens = str.split(",");
                s.add(new Course(tokens[0],
                        Integer.parseInt(tokens[1]),
                        CourseInstructorTest.toEnumSet(tokens[2]),
                        Time.fromString(tokens[3]), Integer.parseInt(tokens[4])));
            }catch(Exception e){
                //Assert.fail("Bug in the test case, courses in courseStrings should not conflict");
            }
        }
        String[] courseStrings = {"EGR 222,3,MWF,05:00 PM,60",
                "EGR 333,3,MWF,04:30 PM,30",
                "EGR 444,3,R,05:00 PM,60"
        };
        String[] tokens = courseStrings[0].split(",");
        Course testcourse1 = new Course(tokens[0],
                Integer.parseInt(tokens[1]),
                CourseInstructorTest.toEnumSet(tokens[2]),
                Time.fromString(tokens[3]), Integer.parseInt(tokens[4]));
        tokens = courseStrings[1].split(",");
        Course testcourse2 =  new Course(tokens[0],
                Integer.parseInt(tokens[1]),
                CourseInstructorTest.toEnumSet(tokens[2]),
                Time.fromString(tokens[3]), Integer.parseInt(tokens[4]));
        tokens = courseStrings[2].split(",");
        Course testcourse3 = new Course(tokens[0],
                Integer.parseInt(tokens[1]),
                CourseInstructorTest.toEnumSet(tokens[2]),
                Time.fromString(tokens[3]), Integer.parseInt(tokens[4]));
        Collection<Course> courses = s.getAllCourses();
        Assert.assertFalse(courses.contains(testcourse1));
        Assert.assertFalse(courses.contains(testcourse2));
        Assert.assertFalse(courses.contains(testcourse3));
    }
}
