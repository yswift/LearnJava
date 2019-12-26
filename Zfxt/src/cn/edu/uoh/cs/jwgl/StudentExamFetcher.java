package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Exam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class StudentExamFetcher extends ExamFetcher {
    private ArrayList<Exam> exams = new ArrayList<>();

    public StudentExamFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
    }

    public ArrayList<Exam> fetch() throws IOException {
        // http://jwgl.uoh.edu.cn/xskscx.aspx?xh=201701030110&xm=%CF%F2%B5%A4%B5%A4&gnmkdm=N121604
        String url = JwglUrl.HOST + "xskscx.aspx?xh=" + userId + "&xm=" + userName + "&gnmkdm=N121604";
        System.out.println("监考安排:" + url);

        // 上级url
        String mainUrl = JwglUrl.HOST + "xs_main.aspx?xh=" + userId;
        String html = HttpHelper.getHtml(url, cookie, mainUrl, JwglUrl.Encoding);
        System.out.println("学生考试查询: " + html);

        Document doc = Jsoup.parse(html);
        Elements rows = doc.select("#DataGrid1 tbody").get(0).children();
        System.out.println(rows.html());
//        <tr>
//		      <td>(2019-2020-1)-52030103-19840003-1</td>
//            <td>计算机网络</td>
//            <td>姓名</td>
//            <td>2020年01月02日(14:30-16:30)</td>
//            <td>博远楼B203</td>
//            <td>&nbsp;</td>
//            <td>14</td>
//            <td>校本部</td>
//	    </tr>
        for (int i=1; i<rows.size(); i++) {
            Elements cols = rows.get(i).children();
            if (cols.size() < 4) {
                continue;
            }
            Exam exam = new Exam();
            exam.setSubjectName(cols.get(1).text());
            exam.setStringTime(cols.get(3).text());
            exam.setClassroom(cols.get(4).text());
            exam.setSeat(cols.get(6).text());
            parseExamTime(exam);
            exams.add(exam);
        }
        return exams;
    }

}
