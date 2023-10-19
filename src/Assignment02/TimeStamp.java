/*
*Jamie Parker
*20101511
 */
package Assignment02;

//Gets Current Time / Calculates difference between First Created time and Current Time / Calculates Age
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import java.sql.Timestamp;

public class TimeStamp {

    public Timestamp getTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public long getTimeDifference(Timestamp timeStamp) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(timeStamp.toLocalDateTime(), now);
        long minutesDifference = duration.toMinutes();
        return minutesDifference;
    }

    public double timePassed(Timestamp savedTime) {
        TimeStamp timeStamp = new TimeStamp();
        Long minutes = timeStamp.getTimeDifference(savedTime);
        double hours = minutes / 60;
        return hours;
    }

    public static String ageCalc(long minutes) {//Uses minutes difference to determine total age since first created - Doesnt include seconds
        StringBuilder age = new StringBuilder();
        if (minutes < 60) {//If created and checked within a minute
            age.append("New Born Baby!");
        } else {
            int minutesPerDay = 24 * 60;
            int minutesPerYear = 365 * minutesPerDay;
            int minutesPerMonth = 30 * minutesPerDay;
            int minutesPerHour = 60;
            Map<String, Long> ageMap = new HashMap<>();//Hashmap saves string and value 
            ageMap.put("years", minutes / minutesPerYear);
            minutes %= minutesPerYear;
            ageMap.put("months", minutes / minutesPerMonth);
            minutes %= minutesPerMonth;
            ageMap.put("days", minutes / minutesPerDay);
            minutes %= minutesPerDay;
            ageMap.put("hours", minutes / minutesPerHour);
            minutes %= minutesPerHour;
            ageMap.put("minutes", minutes);
            String[] timeUnits = {"years", "months", "days", "hours", "minutes"};
            for (String unit : timeUnits) {//Appends age string if value has met above 0 limit 
                long value = ageMap.get(unit);
                if (value > 0) {
                    age.append(value).append(" ").append(unit);
                    if (value == 1) {//Removes 's' at the end of date variable if only 1
                        age.deleteCharAt(age.length() - 1);
                    }
                    if (!unit.equals("minutes")) {//Adds ', ' until final date variable minutes
                        age.append(", ");
                    } else {
                        age.append("");
                    }
                }
            }
            age.append(" old");
        }
        return age.toString();
    }
}
