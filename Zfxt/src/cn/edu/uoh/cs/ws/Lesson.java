package cn.edu.uoh.cs.ws;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;

public class Lesson implements Comparable, TeachingInfo {
    // 单周
    public final static int OddWeek = 0x01;
    // 双周
    public final static int DualWeek = 0x02;

    public final static int PerWeek = 0x03;

    private final static HashMap<String, Integer> chinese2CalendarDayOfWeek = new HashMap<>();

    static {
        chinese2CalendarDayOfWeek.put("一", Calendar.MONDAY);
        chinese2CalendarDayOfWeek.put("二", Calendar.TUESDAY);
        chinese2CalendarDayOfWeek.put("三", Calendar.WEDNESDAY);
        chinese2CalendarDayOfWeek.put("四", Calendar.THURSDAY);
        chinese2CalendarDayOfWeek.put("五", Calendar.FRIDAY);
        chinese2CalendarDayOfWeek.put("六", Calendar.SATURDAY);
        chinese2CalendarDayOfWeek.put("日", Calendar.SUNDAY);
    }

    // 教学班号
    private String subjectId;
    // 课程名称
    private String subjectName;
    // 学号或教工号
    private String no;
    // 学号或教工对应的名字
    private String name;
    // 教师姓名
    private String teacherName;
    // 星期几？
    private String dayOfWeek;
    private int[] num; // 第几节课
    private String classRoom; // 教室
    private int startWeek; // 课是从第几周到第几周
    private int endWeek;
    // 单双周
    private int weekly = OddWeek | DualWeek;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getCalendarDayOfWeek() {
        return chinese2CalendarDayOfWeek.get(this.dayOfWeek);
    }

    public int[] getNum() {
        return num;
    }

    public void setNum(int[] num) {
        this.num = num;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", num=" + Arrays.toString(num) +
                ", classRoom='" + classRoom + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", weekly=" + weekly +
                '}';
    }

    public String toLabel() {
        return "周" + dayOfWeek + "(" + toNumString() + ")" + subjectName + "," + classRoom;
    }

    public String toTime() {
        return "周" + dayOfWeek + "(" + toNumString() + ")" + toWeekString();
    }


    public String toSubjectName() {
        return this.subjectName +
                (name.equals(teacherName) ? "" : ":" + teacherName);
    }

    @Override
    public String toClassroom() {
        return classRoom;
    }

    @Override
    public String toMemo() {
        return null;
    }

    public String toNumString() {
        if (num == null || num.length < 1) {
            return "";
        }
        return num[0] + "-" + num[num.length - 1];
    }

    public String toWeekString() {
        String s = "";
        if (this.weekly == Lesson.OddWeek) {
            s = "单周：";
        }
        if (this.weekly == Lesson.DualWeek) {
            s = "双周：";
        }
        s += "第" + startWeek + "-" + endWeek + "周";
        return s;
    }

    @Override
    public int compareTo(Object another) {
        return comparator.compare(this, (Lesson) another);
    }

    public static final LessonComparator comparator = new LessonComparator();

    static class LessonComparator implements Comparator<Lesson> {
        @Override
        public int compare(Lesson la1, Lesson la2) {
            int ret;
            // 比较星期
            ret = la1.getCalendarDayOfWeek() - la2.getCalendarDayOfWeek();
            if (ret != 0) {
                return ret;
            }
            // 比较时段
            ret = la1.getNum()[0] - la2.getNum()[0];
            return ret;
        }
    }
}
