package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Lesson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentCourseFetcher extends CourseFetcher {
    private static final String[] col2week = {
            "一",
            "二",
            "三",
            "四",
            "五",
            "六",
            "日",
    };
    private ArrayList<Lesson> lessons = new ArrayList<>();

    public StudentCourseFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
    }

    public ArrayList<Lesson> fetch() throws IOException {
        String url = JwglUrl.HOST + "xskbcx.aspx?xh=" + userId + "&xm=" + userName + "&gnmkdm=N121603";
        System.out.println("学生课表url:" + url);

        // 上级url
        String html = HttpHelper.getHtml(url, cookie, url, JwglUrl.Encoding);

        Document doc = Jsoup.parse(html);
        Elements rows = doc.select("#Table1 tbody").get(0).children();
        System.out.println(rows.html());
        if (rows.size() < 14) {
            return lessons;
        }
        // 第2行: 上午第1-2节课的内容
        fetchRow(2, 1, rows.get(2).children());
        // 第4行: 上午第3-4节课的内容
        fetchRow(1, 3, rows.get(4).children());
        // 第6行: 第5节课的内容
        fetchRow(2, 5, rows.get(6).children());
        // 第7行: 第6-7节课的内容
        fetchRow(1, 6, rows.get(7).children());
        // 第9行: 第8-9节课的内容
        fetchRow(1, 8, rows.get(9).children());
        // 第11行: 第10-12节课的内容
        fetchRow(2, 10, rows.get(11).children());

        return lessons;
    }

    void fetchRow(int scol, int snum, Elements cols) {
        for (int i=scol, len=Math.min(scol+7, cols.size()), week=i-scol; i<len; i++, week++) {
            String s = cols.get(i).html();
            System.out.println(s);
            int[] num;
            String rowspan = cols.get(i).attr("rowspan");
            if ("2".equals(rowspan)) {
                num = new int[]{snum, snum+1};
            } else if ("3".equals(rowspan)) {
                num = new int[]{snum, snum+1, snum+2};
            } else {
                num = new int[]{snum};
            }
            if (have5(col2week[week], snum)) {
                week++;
            }
            fetchOne(col2week[week], num, s);
        }
    }

    // TODO 测试
    boolean have5(String week, int snum) {
        // 如果有第5节课，并且三节连上，6，7节课要特殊处理
        // 判断是否已有 周week，的第snum节课，如果有返回true
        return lessons.stream().anyMatch(
                lesson -> lesson.getDayOfWeek().equals(week)
                        && Arrays.stream(lesson.getNum()).anyMatch(n -> n == snum));
    }

    /**
     *
     * @param week 周?
     * @param text 获取到的上课信息：集成原理与工具<br>{第1-18周|2节/周}<br>教工号|教师姓名<br>任美福楼418
     */
    void fetchOne(String week, int[] num, String text) {
        String[] ls = text.split("<br>");
        if (ls.length < 4) {
            return;
        }
        Lesson lesson = new Lesson();
        lesson.setDayOfWeek(week);
        lesson.setNum(num);
        lesson.setSubjectName(ls[0]);
        parseLessonTime(lesson, ls[1]);
        lesson.setNo(userId);
        lesson.setName(userName);
        lesson.setTeacherName(ls[2]);
        lesson.setClassRoom(ls[3]);

        lessons.add(lesson);
    }

    private static final Pattern lessonTimePattern = Pattern.compile("第(\\d+)-(\\d+)周");

    private boolean parseLessonTime(Lesson lesson, String time) {
        Matcher m = lessonTimePattern.matcher(time);
        if (!m.find()) {
            return false;
        }
        lesson.setStartWeek(Integer.parseInt(m.group(1)));
        lesson.setEndWeek(Integer.parseInt(m.group(2)));
        return true;
    }
}
