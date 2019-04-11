package cn.edu.uoh.cs.ws;

import java.util.Calendar;

public class LessonTime {
    private int num;
    private int hour;
    private int minute;
    private int endHour;
    private int endMinute;

    private int alarmHour;
    private int alarmMinute;

    public final static LessonTime[] lessonTimes = {
            new LessonTime(0, 8, 0, 8, 45),
            new LessonTime(1, 8, 0, 8, 45),
            new LessonTime(2, 8, 55, 9, 40),
            new LessonTime(3, 10, 0, 10, 45),
            new LessonTime(4, 10, 55, 11, 40),
            new LessonTime(5, 13, 0, 13, 45),
            new LessonTime(6, 14, 0, 14, 45),
            new LessonTime(7, 14, 55, 15, 40),
            new LessonTime(8, 16, 0, 16, 45),
            new LessonTime(9, 16, 55, 17, 40),
            new LessonTime(10, 19, 0, 19, 45),
            new LessonTime(11, 19, 55, 20, 40),
            new LessonTime(12, 20, 50, 21, 35)
    };

    private LessonTime(int num, int hour, int minute, int endHour, int endMinute) {
        this.num = num;
        this.hour = hour;
        this.minute = minute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * 根据提前的时间计算闹铃的开始时间
     * @param beforeMinute 提前闹铃的分钟数
     */
    public void calculateAlarmTime(int beforeMinute) {
        alarmMinute = minute - beforeMinute;
        alarmHour = hour;
        while (alarmMinute < 0) {
            alarmMinute += 60;
            alarmHour--;
        }
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public int getAlarmMinute() {
        return alarmMinute;
    }

    /**
     * 获取指定课程的上课，下课时间
     * @param lesson
     * @param begin
     * @param end
     */
    public static void getBeginEndTime(Lesson lesson, Calendar begin, Calendar end) {
        int[] nums = lesson.getNum();
        LessonTime bt = lessonTimes[nums[0]];
        LessonTime et = lessonTimes[nums[nums.length-1]];

        begin.set(Calendar.HOUR_OF_DAY, bt.hour);
        begin.set(Calendar.MINUTE, bt.minute);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);

        end.set(Calendar.HOUR_OF_DAY, et.endHour);
        end.set(Calendar.MINUTE, et.endMinute);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
    }
}

