package cn.edu.uoh.cs.jwgl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.uoh.cs.ws.Exam;

public class TeacherExamFetcher extends ExamFetcher {
	private ArrayList<Exam> exams = new ArrayList<>();

	public TeacherExamFetcher(String userId, String userName, String cookie) {
		super(userId, userName, cookie);
	}

	public ArrayList<Exam> fetch() throws IOException {
		String url = JwglUrl.HOST + "js_ksap.aspx?zgh=" + userId + "&xm=" + userName + JwglUrl.Gnmkdm;
		System.out.println("监考安排:" + url);

		// 上级url
		String mainUrl = JwglUrl.HOST + "js_main.aspx?xh=" + userId;
		String html = HttpHelper.getHtml(url, cookie, mainUrl, JwglUrl.Encoding);

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
}
