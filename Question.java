import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Question{

     public static void main(String []args){
        System.out.println("Hello World");
       GetStatusOfDoctorOpenedOrClosed(new int [] {0,3,5} ,new int [] {12,23,5} ,new int [] {4,24,1});
     }
     public static String from24to12(String hour) {
            try {
                String input = hour;
                //Date/time pattern of input date
                DateFormat inputFormat = new SimpleDateFormat("HH");
                //Date/time pattern of desired output date
                DateFormat outputFormat = new SimpleDateFormat("hh aa");
                Date date = null;
                String output = null;
                //Conversion of input String to date
                date = inputFormat.parse(input);
                //old date format to new date format
                output = outputFormat.format(date);
                return output;
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            return "";
        }
      public static void GetStatusOfDoctorOpenedOrClosed ( int[] doctorDays, int[] fromHour, int[] toHour) {
            Calendar currentCalendar = Calendar.getInstance();
            String[] daysOfWeek = {"Sat", "Sun", "Mon",
                    "Tue", "Wen", "Thu", "Fri"};

            int todayIndex = 0; //today index in doctorDays array
            int nextDayIndex = 0; // index of the nearst day from  today at which the doctor is  available
            int doctorDaysLength = doctorDays.length;

            /*
             map the day number to be ordered
            corresponding to the daysOfWeek array
            */
            int today = currentCalendar.get(Calendar.DAY_OF_WEEK) % 7;
            int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);


/*
            for (; todayIndex < 7; todayIndex++) {
                if (daysOfWeek[todayIndex] == today) {
                    break;
                }
            }
            */

            Arrays.sort(doctorDays);


            for (int i = 0; i < doctorDays.length; i++) {
                if (doctorDays[i] == today) {
                    todayIndex = i;
                    nextDayIndex = i;
                    break;
                } else if (doctorDays[i] > today) {
                    nextDayIndex = i;
                    break;
                }
            }

            if (nextDayIndex == todayIndex) {

                if (currentHour >= fromHour[todayIndex] && currentHour <= toHour[todayIndex]) {
                    String closeHour = from24to12(Integer.toString(toHour[todayIndex]));
                    System.out.println("“Opened: Closes at " + closeHour);  //ex : Opened: Closes at 11 pm
                } else if (currentHour < fromHour[todayIndex]) {
                    String openHour = from24to12(Integer.toString(fromHour[todayIndex])); //ex : Closed: Opens at 8 am

                    System.out.println("“Closed: Opens at " + openHour);
                } else {
                    nextDayIndex = (todayIndex + 1) % doctorDaysLength;  //use mod when reach the end of the week set the nextDay to first of the week
                    String openHour = from24to12(Integer.toString(fromHour[nextDayIndex]));
                    String nextDayString = daysOfWeek[doctorDays[nextDayIndex]]; // get the name of the next day
                    System.out.println("“Closed: Opens at " + nextDayString + " " + openHour);   //ex : Closed: opens at Mon 8 am
                }

            } else {
                String openHour = from24to12(Integer.toString(fromHour[nextDayIndex]));
                String nextDayString = daysOfWeek[doctorDays[nextDayIndex]];
                System.out.println("“Closed: Opens at " + nextDayString + " " + openHour);
            }
        }
}
