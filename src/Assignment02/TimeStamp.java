/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class TimeStamp {//Gets Current Time / Calculates difference between First Created time and Current Time / Calculates Age

    public String getTime() {//Uses Date Time to get current local time

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public long getTimeDifference(String time) {//Splits time string into date variables then calculates total minutes from first created or last saved time to current time

        String[] timeValues = time.split(":");

        int year = Integer.parseInt(timeValues[0]);
        int month = Integer.parseInt(timeValues[1]);
        int day = Integer.parseInt(timeValues[2]);
        int hour = Integer.parseInt(timeValues[3]);
        int minute = Integer.parseInt(timeValues[4]);
        int second = Integer.parseInt(timeValues[5]);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime savedDateTime = LocalDateTime.of(year, month, day, hour, minute, second);

        long minutesDifference = ChronoUnit.MINUTES.between(savedDateTime, now);//calculates difference in minutes 

        return minutesDifference;
    }

    public static String ageCalc(long minutes) {//Uses minutes difference to determine total age since first created - Doesnt include seconds

        StringBuilder age = new StringBuilder();
        age.append("Pet Age: ");

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
        age.append("\n");
        return age.toString();
    }
}
