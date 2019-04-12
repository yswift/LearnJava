package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Lesson;

import java.io.IOException;
import java.util.List;

public class UTParseTearchrCourse {
    public void fetch2() throws IOException {
        String[] courses = {
                "Java程序设计【周三第8,9节{第1-9周};周四第3,4节{第1-18周}/文鼎楼505;文鼎楼415】",
                "Java程序设计【周三第6,7节{第1-18周};周四第1,2节{第1-18周}/博远楼B305;任美福楼418】",
                "计算机科学与技术专业毕业设计（论文）【/】",
                "(2016-2017-2)-52040165-20090007-1",
                "Java高级应用【周三第10,11节{第1-18周};周四第10,11节{第1-18周}/博远楼B304;任美福楼514】",
                "(2016-2017-2)-52040180-20090007-1",
                "计算机科学与技术专业课外科技创新【/】",
        };
        TeacherCourseFetcher tcf = new TeacherCourseFetcher("20090007", "晏立", null);
        for (String c : courses) {
            tcf.fetchOne(c, c);
        }
    }
}
