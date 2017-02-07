// Thelma Andrews,CSC526,Homework2 (Part3)
import java.util.*;


public class Course {
    String coursename;
    int coursecredits;
    Set<Weekday> weekDays;
    Time starttime;
    int duration;
    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration){
        coursename=name;
        coursecredits=credits;
        weekDays=days;
        starttime=startTime;
        this.duration=duration;
        if(coursename==null || (coursename!=null && coursename.equalsIgnoreCase(""))){
            throw new IllegalArgumentException("Empty name or null name should be invalid");
        }
        if(coursename!=null && coursename.indexOf(" ")==-1){
            throw new IllegalArgumentException("Name should contain space");
        }
        if(coursecredits<=0 || coursecredits>=6){
            throw new IllegalArgumentException("0 credit should be invalid or more than 6 credit should be invalid");
        }
        if(weekDays==null){
            throw new IllegalArgumentException("null days should be invalid");
        }
        if(starttime==null){
            throw new IllegalArgumentException("null time should be invalid");
        }
        if(this.duration<=0){
            throw new IllegalArgumentException("duration should be greater than 0");
        }
    }
    public boolean conflictsWith(Course course){
        try {
            Iterator iterator = weekDays.iterator();
            boolean conflictCourse = false;
            for (;iterator.hasNext();) {
                Weekday weekday = (Weekday) iterator.next();
                if (course.weekDays.contains(weekday)) {
                    conflictCourse = true;
                    break;
                }
            }
            if (conflictCourse && (toString().indexOf(course.starttime.toString()) != -1)) {
                return true;
            }
            DateTime dateTime=new DateTime();
            int gapinminutes=dateTime.getDurationBetweenDates(this,course);
            if (conflictCourse && gapinminutes>0 && gapinminutes < duration) {
                return true;
            }
            course.starttime.shift(course.duration);
            if(conflictCourse && starttime.toString().equals(course.starttime.toString())){
                return false;
            }
            starttime.shift(duration);
            if(conflictCourse && starttime.toString().equals(course.starttime.toString())){
                return true;
            }
        }catch (Exception e){}
        return false;
    }
    public boolean contains(Weekday day, Time startTime){
        try {
            if (weekDays.contains(day) && (toString().indexOf(startTime.toString()) != -1)) {
                return true;
            }
            DateTime dateTime=new DateTime();
            int diffMinutes=dateTime.getDurationBetweenTimes(this.starttime,startTime);
            if (weekDays.contains(day) && diffMinutes<duration) {
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean equals(Object o){
        return (this==o);
    }
    public int getCredits(){
        return coursecredits;
    }
    public int getDuration(){
        return duration;
    }
    public String getName(){
        return coursename;
    }
    public Time getStartTime(){
        return starttime;
    }
    public Time getEndTime(){
        DateTime dateTime=new DateTime();
        return dateTime.getNewTimeWithDuration(starttime,duration);
    }
    public String toString(){
        String shortNames="";
        Iterator iterator=weekDays.iterator();
        for(;iterator.hasNext();){
            Weekday weekday=(Weekday)iterator.next();
            shortNames+=weekday.toShortName();
        }
        return coursename+","+coursecredits+","+shortNames+","+starttime+","+duration;
    }
}
