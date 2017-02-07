// Thelma Andrews,CSC526,Homework2 (Part3)
public class DateTime {
    public int getDurationBetweenDates(Course course1,Course course2){
        try {
            String temptime = course1.starttime.toString();
            int temphour = Integer.parseInt(temptime.split(":")[0]);
            int tempminute = Integer.parseInt(temptime.split(":")[1].split(" ")[0]);
            temptime = course2.starttime.toString();
            int temphours = Integer.parseInt(temptime.split(":")[0]);
            int tempminutes = Integer.parseInt(temptime.split(":")[1].split(" ")[0]);
            return ((temphours - temphour) * 60) + (tempminutes - tempminute);
        }catch (Exception e){}
        return 0;
    }
    public int getDurationBetweenTimes(Time time1,Time time2){
        try {
            String temptimestring = time1.toString();
            int temptimehour = Integer.parseInt(temptimestring.split(":")[0]);
            int temptimeminute = Integer.parseInt(temptimestring.split(":")[1].split(" ")[0]);
            temptimestring = time2.toString();
            int temptimehours = Integer.parseInt(temptimestring.split(":")[0]);
            int temptimeminutes = Integer.parseInt(temptimestring.split(":")[1].split(" ")[0]);
            return ((temptimehours - temptimehour) * 60) + (temptimeminutes - temptimeminute);
        }catch (Exception e){}
        return 0;
    }
    public Time getNewTimeWithDuration(Time starttime,int duration){
        try{
            String temptime=starttime.toString();
            int temphour=Integer.parseInt(temptime.split(":")[0]);
            int tempminute=Integer.parseInt(temptime.split(":")[1].split(" ")[0]);
            String ampm=temptime.split(":")[1].split(" ")[1];
            tempminute=tempminute+duration;
            int diffhours=tempminute/60;
            tempminute=tempminute%60;
            if(tempminute>=60){
                temphour=temphour+diffhours+1;
                tempminute=0;
            }else{
                temphour=temphour+diffhours;
            }
            if(temphour>=12){
                int rotate=temphour/12;
                temphour=temphour%12;
                if(temphour==0){
                    temphour=12;
                }
                if(rotate%2==1 && ampm.equalsIgnoreCase("PM")){
                    return new Time(temphour,tempminute,false);
                }else if(rotate%2==1 && ampm.equalsIgnoreCase("AM")){
                    return new Time(temphour,tempminute,true);
                }
            }
            return new Time(temphour,tempminute,starttime.pmamcheck);
        }catch (Exception e){

        }
        return null;
    }
}
