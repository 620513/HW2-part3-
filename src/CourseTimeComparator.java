// Thelma Andrews,CSC526,Homework2 (Part3)
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;


public class CourseTimeComparator implements Comparator<Course> {
    @Override
    public int compare(Course course1, Course course2) {
        return compareCourseTime(course1.getStartTime(), course2.getStartTime());
    }
    public int compareCourseTime(Time timeFirst, Time timeSecond){
        return courseTime(timeFirst.toString()) - courseTime(timeSecond.toString());
    }
    DateFormat format = new SimpleDateFormat("hh:mm a");
    public int courseTime(String temptime){
        return courseTime(temptime, format);
    }

    private int courseTime(String temptime, DateFormat tempformat) {
        try{
            Date sortDate = tempformat.parse(temptime);
            return (int)sortDate.getTime();
        }catch(ParseException e){
            return -1;
        }
    }
}
