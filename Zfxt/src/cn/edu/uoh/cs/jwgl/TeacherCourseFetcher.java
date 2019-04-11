package cn.edu.uoh.cs.jwgl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.uoh.cs.ws.Lesson;

public class TeacherCourseFetcher {
	private Account account;
	private ArrayList<Lesson> lessons = new ArrayList<>();

	public TeacherCourseFetcher(Account account) {
		this.account = account;
	}

	public ArrayList<Lesson> fetch() throws IOException {
		String url = JwglUrl.HOST + "js_xkqk.aspx?zgh=" + account.userId + "&xm=" + account.userName + JwglUrl.Gnmkdm;
		System.out.println("选课情况url:" + url);

		String html = HttpHelper.getHtml(url, account.cookie, account.mainUrl, JwglUrl.Encoding);

		Document doc = Jsoup.parse(html);
		Elements es = doc.select("#kcmc");
		if (es == null || es.size() < 1) {
			return lessons;
		}
		Element e = es.get(0);
		System.out.println(e.html());
		for (Element o : e.children()) {
			fetchOne(o.val(), o.text());
		}
		return lessons;
	}

	public List<Lesson> fetch2() throws IOException {
		String[] courses = {
"Java程序设计【周三第8,9节{第1-9周};周四第3,4节{第1-18周}/文鼎楼505;文鼎楼415】",
"Java程序设计【周三第6,7节{第1-18周};周四第1,2节{第1-18周}/博远楼B305;任美福楼418】",
"计算机科学与技术专业毕业设计（论文）【/】",
"(2016-2017-2)-52040165-20090007-1",
"Java高级应用【周三第10,11节{第1-18周};周四第10,11节{第1-18周}/博远楼B304;任美福楼514】",
"(2016-2017-2)-52040180-20090007-1",
"计算机科学与技术专业课外科技创新【/】",
		};
		for (String c : courses) {
			fetchOne(c, c);
		}
		return lessons;
	}

	private static final Pattern coursePattern = Pattern.compile("(\\S+)\\s*【(.*)/(.*)】");

	private void fetchOne(String no, String text) {
		Matcher m = coursePattern.matcher(text);
		if (!m.matches()) {
			return;
		}
		// 课程名称
		String name = m.group(1);
		// 时段
		String[] times = m.group(2).split(";");
		// 教室
		String[] classrooms = m.group(3).split(";");

		if (classrooms.length != times.length) {
			return;
		}

		for (int i = 0; i < classrooms.length; i++) {
			Lesson lesson = new Lesson();
			lesson.setNo(account.userId);
			lesson.setName(account.userName);
			lesson.setTeacherName(account.userName);
			lesson.setSubjectName(name);
			lesson.setSubjectId(no);

			lesson.setClassRoom(classrooms[i]);

			if (parseLessonTime(lesson, times[i])) {
				lessons.add(lesson);
			}
		}
	}

	private static final Pattern lessonTimePattern = Pattern
			.compile("周([一二三四五六日]{1})第([\\d,]+)节\\{第(\\d+)-(\\d+)周(\\|([单双])周)?\\}");

	protected boolean parseLessonTime(Lesson lesson, String time) {
		Matcher m = lessonTimePattern.matcher(time);
		if (!m.find()) {
			return false;
		}
		lesson.setDayOfWeek(m.group(1));
		String[] ns = m.group(2).split(",");
		int[] ins = new int[ns.length];
		for (int i = 0; i < ns.length; i++) {
			ins[i] = Integer.parseInt(ns[i]);
		}
		lesson.setNum(ins);
		lesson.setStartWeek(Integer.parseInt(m.group(3)));
		lesson.setEndWeek(Integer.parseInt(m.group(4)));
		String week = m.group(6);
		if (week != null) {
			if ("单".endsWith(week)) {
				lesson.setWeekly(Lesson.OddWeek);
			} else if ("双".endsWith(week)) {
				lesson.setWeekly(Lesson.DualWeek);
			}
		}
		return true;
	}

}
