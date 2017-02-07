// Thelma Andrews,CSC526,Homework2 (Part3)
import java.io.PrintStream;
import java.util.*;


public class Schedule implements Iterable<Course>{
    List<Course> schedulecourses=new Stack<Course>();;
    public Schedule(){}
    public void add(Course course) throws ScheduleConflictException{
        if(schedulecourses==null){
            schedulecourses=new Stack<Course>();
        }else{
            boolean addconflict=false;
            for(Course schcourse : schedulecourses){
                boolean isweekdaysame = false;
                Iterator coursedaysiterator = schcourse.weekDays.iterator();
                while (coursedaysiterator.hasNext()) {
                    Weekday courseteachday = (Weekday) coursedaysiterator.next();
                    if (course.weekDays.contains(courseteachday)) {
                        isweekdaysame = true;
                        break;
                    }
                }
                if(isweekdaysame){
                    if((schcourse.starttime.toString().indexOf(course.starttime.toString()) != -1)) {
                        addconflict=true;
                        break;
                    }
                    DateTime dateTime=new DateTime();
                    int duration=dateTime.getDurationBetweenDates(course,schcourse);
                    if (duration>0 && duration<course.getDuration()) {
                        addconflict=true;
                        break;
                    }
                }
            }
            if(addconflict==false){
                schedulecourses.add(course);
            }else{
                throw new ScheduleConflictException("Schedule Conflict Exception !!!");
            }
        }
    }
    public List getAllCourses(){
        return schedulecourses;
    }
    public Object clone() throws CloneNotSupportedException{
        return this;
    }
    public Course getCourse(Weekday day,Time time){
        Course course=null;
        for(Course schcourse : schedulecourses){
            boolean matchcourse = false;
            if (schcourse.weekDays.contains(day)) {
                matchcourse = true;
            }
            if(matchcourse==true && time.toString().compareTo(schcourse.getStartTime().toString())==0){
                course=schcourse;
            }
        }
        return course;
    }
    public void save(PrintStream printStream, Comparator<Course> courselistcomparator){
        Collections.sort(schedulecourses,courselistcomparator);
        for(Course schcourse : schedulecourses){
            printStream.println(schcourse.toString());
        }
        printStream.close();
    }
    public int totalCredits(){
        int allcreditsFromCourses=0;
        for(Course schcourse : schedulecourses){
            allcreditsFromCourses=allcreditsFromCourses+schcourse.getCredits();
        }
        return allcreditsFromCourses;
    }
    public void remove(Weekday day,Time time){
        for(Course schcourse : schedulecourses){
            boolean coursedelete = false;
            if (schcourse.weekDays.contains(day)) {
                coursedelete = true;
                break;
            }
            if(coursedelete && time.toString().compareTo(schcourse.getStartTime().toString())==0){
                schedulecourses.remove(schcourse);
            }
        }
    }
    @Override
    public Iterator<Course> iterator(){
        return (schedulecourses.iterator());
    }
}
