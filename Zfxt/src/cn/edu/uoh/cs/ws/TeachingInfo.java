package cn.edu.uoh.cs.ws;

/**
 * 教学信息接口，用于获取显示的内容
 * Created by yan on 2016/11/1.
 */

public interface TeachingInfo {
    String toLabel();

    String toTime();

    String toSubjectName();

    String toClassroom();

    String toMemo();
}

