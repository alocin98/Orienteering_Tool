package gpxLib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.*;

/**
 * Time format class. Stores a time from either a String or by hours, minutes and seconds.
 */
public class Time implements Comparable<Time>{

    private final String TIME_PATTERN = "[\\d]{2}+[:]+[\\d]{2}+[:]+[\\d]{2}";
    private final String HHMMSS_PATTERN = "[\\d]{2}";

    private String time_String;
    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Stores the Time with the given constants.
     * @param hours             hour of the day, HH 00-24
     * @param minutes           minutes of the hour, MM 00-60
     * @param seconds           seconds of the minute, SS 00-60
     */
    public Time(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.time_String = ""+hours+minutes+seconds;
    }

    /**
     * Parses Time data from a String. String must be in format: HH:MM:SS. It can have text before and after it.
     * So for example:
     *      the line: 2018-04-25T15:25:41.000Z
     *      is parsed to    Hours = 15
     *                      Minutes = 25
     *                      Seconds = 41
     *
     * @param time          The Time in text format, as shown above.
     */
    public Time(String time){
        Pattern timePattern = Pattern.compile(TIME_PATTERN);
        Matcher timeMatcher = timePattern.matcher(time);
        String time_string;
        if(timeMatcher.find()){
            time_String = timeMatcher.group(0);
        }
        extractHHMMSS(time_String);
    }

    /**
     * Initializes a Time with the given seconds. Calculates them into minutes and seconds.
     * @param seconds
     */
    public Time(int seconds){
        int tmp = seconds % 60;
        this.minutes = seconds / 60;
        this.seconds = tmp;
    }

    /**
     * Helper method to extract time text
     * @param time
     */
    private void extractHHMMSS(String time){
        Pattern two_digits = Pattern.compile(HHMMSS_PATTERN);
        Matcher m = two_digits.matcher(time);
        if(m.find())
            this.hours = Integer.parseInt(m.group());
        if(m.find())
            this.minutes = Integer.parseInt(m.group());
        if(m.find())
            this.seconds = Integer.parseInt(m.group());
    }

    /**
     * @return                  hour of the day, HH 00-24
     */
    public int getHours(){
        return hours;
    }

    /**
     * @return                  minutes of the hour, MM 00-60
     */
    public int getMinutes(){
        return minutes;
    }

    /**
     * @return                  seconds of the minute, SS 00-60
     */
    public int getSeconds(){
        return seconds;
    }

    /**
     * @return                    String representation of this Time, format HH:MM:SS
     */
    public String toString(){
        return String.format("%02d:%02d:%02d",hours, minutes, seconds);
    }

    @Override
    public int compareTo(Time time) {
        int secondsFromMidnightTime = time.getTimeInSeconds();
        int secondsFromMidnightThis = this.getTimeInSeconds();
        int dif = secondsFromMidnightTime - secondsFromMidnightThis;
        if(dif > 0)
            return -1;
        else if(dif < 0)
            return 1;
        return 0;
    }

    public int getTimeInSeconds(){
        return (this.hours * 60 * 60) + (this.minutes * 60) + this.seconds;
    }
}
