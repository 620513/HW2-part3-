// Thelma Andrews,CSC526,Homework2 (Part3)
import java.util.Comparator;


public class CourseCreditComparator implements Comparator<Course> {
    @Override
    public int compare(Course course1, Course course2) {
        if(course1.getCredits()<course2.getCredits()){
            return -1;
        }else if(course1.getCredits()>course2.getCredits()){
            return 1;
        }else if(course1.getCredits()==course2.getCredits()){
            return 0;
        }
        return 0;
    }
}
