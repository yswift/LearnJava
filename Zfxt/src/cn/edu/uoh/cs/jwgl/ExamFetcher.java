package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Exam;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExamFetcher extends Fetcher{
    public ExamFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
    }

    public abstract List<Exam> fetch() throws IOException;

    private static Pattern examTimePattern = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日\\((\\d+):(\\d+)-(\\d+):(\\d+)\\)\\s*");

    protected void parseExamTime(Exam exam) {
        String st = exam.getStringTime();
        Matcher matcher = examTimePattern.matcher(st);
        if (matcher.matches()) {
            int y = Integer.parseInt(matcher.group(1));
            int m = Integer.parseInt(matcher.group(2));
            int d = Integer.parseInt(matcher.group(3));
            int h1 = Integer.parseInt(matcher.group(4));
            int m1 = Integer.parseInt(matcher.group(5));
            int h2 = Integer.parseInt(matcher.group(6));
            int m2 = Integer.parseInt(matcher.group(7));
            Calendar c = Calendar.getInstance();
            c.set(y, m - 1, d, h1, m1);
            exam.setBeginTime(c.getTimeInMillis());
            c.set(y, m - 1, d, h2, m2);
            exam.setEndTime(c.getTimeInMillis());
        }
    }
}
