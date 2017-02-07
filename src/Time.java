// Thelma Andrews,CSC526,Homework2 (Part3)
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

public class Time implements Comparable<Time>{
    int hours;
    int minutes;
    boolean pmamcheck;
    public Time(int hour,int minute,boolean pm){
        hours = hour;
        minutes = minute;
        pmamcheck = pm;
        if(hours<=0 || hours>12){
            throw new IllegalArgumentException("Invalid hour/minutes values");
        }else if(minutes<0 || minutes>59){
            throw new IllegalArgumentException("hour "+ hour + " and/or minute " + minute + " isPM "+ pm + " is invalid");
        }
    }
    public static Time fromString(String str){
        Time createTime=null;
        String[] timeslices=str.split(":");
        if(timeslices!=null && timeslices.length==2){
            if(timeslices[0]!=null && (timeslices[0].length()==1 || Integer.parseInt(timeslices[0])>12)){
                throw new IllegalArgumentException("str is invalid format");
            }
            String[] subslices=timeslices[1].split(" ");
            if(subslices!=null && subslices.length==2){
                if(subslices[0]!=null && (subslices[0].length()==1 || Integer.parseInt(subslices[0])>59)){
                    throw new IllegalArgumentException("str is invalid format");
                }
                if(!(subslices[1].equals("AM") || subslices[1].equals("PM"))){
                    throw new IllegalArgumentException("str is invalid format");
                }
                if(subslices[1].equalsIgnoreCase("false") || subslices[1].equalsIgnoreCase("AM")) {
                    createTime=new Time(Integer.parseInt(timeslices[0]), Integer.parseInt(subslices[0]), false);
                }else{
                    createTime=new Time(Integer.parseInt(timeslices[0]), Integer.parseInt(subslices[0]), true);
                }
            }else{
                createTime=new Time(Integer.parseInt(timeslices[0]), Integer.parseInt(subslices[0]), true);
            }
        }else{
            throw new IllegalArgumentException("str is invalid format");
        }
        return createTime;
    }
    public Object clone() throws CloneNotSupportedException{
        return this;
    }
    public int compareTo(Time time){
        return this.toString().compareTo(time.toString());
    }
    public boolean equals(Object o){
        return this==o;
    }
    public int getHour(){
        return hours;
    }
    public int getMinute(){
        return minutes;
    }
    public boolean isPM(){
        return  pmamcheck;
    }
    public void shift(int minute) throws IllegalArgumentException{
        if(minutes<0){
            throw new IllegalArgumentException("Invalid minutes values in Time change");
        }else {
            String timestring=this.toString();
            int hourint=Integer.parseInt(timestring.split(":")[0]);
            int minint=Integer.parseInt(timestring.split(":")[1].split(" ")[0]);
            String ampm=timestring.split(":")[1].split(" ")[1];
            minint=minint+minute;
            int hoursgap=minint/60;
            minint=minint%60;
            if(minint>=60){
                hourint=hourint+hoursgap+1;
                minint=0;
            }else{
                hourint=hourint+hoursgap;
            }
            if(hourint>=12){
                int clockrotate=hourint/12;
                hourint=hourint%12;
                if(hourint==0){
                    hourint=12;
                }
                if((hoursgap==1 || hoursgap>12) && clockrotate%2==1 && ampm.equalsIgnoreCase("PM")){
                    pmamcheck=false;
                }else if((hoursgap==1 || hoursgap>12) && clockrotate%2==1 && ampm.equalsIgnoreCase("AM")){
                    pmamcheck=true;
                }
            }
            hours=hourint;
            minutes=minint;
        }
    }
    public String toString(){
        String timepattern=hours + ":" + minutes;
        if(hours>=10 && minutes>=10) {
            timepattern=hours+":" + minutes;
        }else if(hours<10 && minutes<10){
            timepattern="0"+hours+ ":0"+minutes;
        }else if(hours<10){
            timepattern="0"+hours+ ":" + minutes;
        }else if(minutes<10){
            timepattern=hours+":0"+minutes;
        }
        if(pmamcheck==true) {
            timepattern+=" PM";
        }
        if(pmamcheck==false) {
            timepattern+=" AM";
        }
        return timepattern;
    }
}
