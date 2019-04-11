package cn.edu.uoh.cs.jwgl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.uoh.cs.ws.Exam;

public class TeacherExamFetcher {
	private Account account;
	private ArrayList<Exam> exams = new ArrayList<>();

	public TeacherExamFetcher(Account account) {
		this.account = account;
	}
	
	public ArrayList<Exam> fetch() throws IOException {
		String url = JwglUrl.HOST + "js_ksap.aspx?zgh=" + account.userId + "&xm=" + account.userName + JwglUrl.Gnmkdm;
		System.out.println("监考安排:" + url);

		String html = HttpHelper.getHtml(url, account.cookie, account.mainUrl, JwglUrl.Encoding);

		Document doc = Jsoup.parse(html);
		Elements rows = doc.select("#Datagrid1 tbody").get(0).children();
		System.out.println(rows.html());
//		<tr> 
//		 <td>2017年03月05日(09:00-11:00)</td>
//		 <td>文鼎楼102</td>
//		 <td>5</td>
//		 <td>请提前30分钟到文鼎楼101领卷，考试完毕试卷送回美术学院204.</td>
//		 <td>&nbsp;</td> 
//		</tr>
		for (int i=1; i<rows.size(); i++) {
			Elements cols = rows.get(i).children();
			if (cols.size() < 4) {
				continue;
			}
			Exam exam = new Exam();
			exam.setStringTime(cols.get(0).text());
			exam.setClassroom(cols.get(1).text());
			exam.setWhereTestPaper(cols.get(3).text());
			parseExamTime(exam);
			exams.add(exam);
		}
		return exams;
	}
	
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
