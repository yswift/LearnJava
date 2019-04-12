package cn.edu.uoh.cs.jwgl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.uoh.cs.ws.Lesson;

public class TeacherCourseFetcher extends CourseFetcher {
	private ArrayList<Lesson> lessons = new ArrayList<>();

	public TeacherCourseFetcher(String userId, String userName, String cookie) {
		super(userId, userName, cookie);
	}

	public ArrayList<Lesson> fetch() throws IOException {
		String url = JwglUrl.HOST + "js_xkqk.aspx?zgh=" + userId + "&xm=" + userName + JwglUrl.Gnmkdm;
		System.out.println("选课情况url:" + url);

		// 上级url
		String mainUrl = JwglUrl.HOST + "js_main.aspx?xh=" + userId;
		String html = HttpHelper.getHtml(url, cookie, mainUrl, JwglUrl.Encoding);

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

	private static final Pattern coursePattern = Pattern.compile("(\\S+)\\s*【(.*)/(.*)】");

	void fetchOne(String no, String text) {
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
			lesson.setNo(userId);
			lesson.setName(userName);
			lesson.setTeacherName(userName);
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
