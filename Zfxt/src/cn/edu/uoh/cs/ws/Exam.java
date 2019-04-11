package cn.edu.uoh.cs.ws;

import java.util.Comparator;

public class Exam implements Comparable, TeachingInfo {
    // 考试科目
    private String subjectName;

    // 监考时间， 字符串类型
    private String stringTime;

    // 开始时间
    private long beginTime;

    // 结束时间
    private long endTime;

    // 考试教室
    private String classroom;

    // 考试取卷时间地点
    private String whereTestPaper;

    // zuow座位号
    private String seat;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getWhereTestPaper() {
        return whereTestPaper;
    }

    public void setWhereTestPaper(String whereTestPaper) {
        this.whereTestPaper = whereTestPaper;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "subjectName='" + subjectName + '\'' +
                ", stringTime='" + stringTime + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", classroom='" + classroom + '\'' +
                ", whereTestPaper='" + whereTestPaper + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }

    @Override
    public String toClassroom() {
        return classroom + (seat == null ? "" : "(" + seat + ")");
    }

    @Override
    public String toLabel() {
        return stringTime.substring(5) + " " + subjectName + " " + classroom;
    }

    @Override
    public String toTime() {
        return stringTime.substring(5);
    }

    @Override
    public String toSubjectName() {
        return subjectName;
    }

    @Override
    public String toMemo() {
        return whereTestPaper;
    }

    @Override
    public int compareTo(Object another) {
        return comparator.compare(this, (Exam) another);
    }

    public static final ExamComparator comparator = new ExamComparator();

    static class ExamComparator implements Comparator<Exam> {
        @Override
        public int compare(Exam lhs, Exam rhs) {
            long time = System.currentTimeMillis();
            long lkey = lhs.beginTime > time ? lhs.beginTime - time : lhs.beginTime;
            long rkey = rhs.beginTime > time ? rhs.beginTime - time : lhs.beginTime;
            return (int) (lkey - rkey);
        }
    }
}