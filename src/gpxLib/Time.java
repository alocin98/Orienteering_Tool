package gpxLib;

import java.util.regex.*;

/**
 * Time format class. Stores a time from either a String or by hours, minutes and seconds.
 */
public class Time {
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
        if(timeMatcher.find()){
            this.time_String = timeMatcher.group(0);
        }
        extractHHMMSS(time_String);
    }

    /**
     * Helper method to extract time text
     * @param time
     */
    private void extractHHMMSS(String time){
        int[] numbers = new int[3];
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
        return this.time_String;
    }
}
