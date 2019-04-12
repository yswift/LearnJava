package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Lesson;

import java.io.IOException;
import java.util.List;

/**
 * 获取课程表的接口
 */
public abstract class CourseFetcher extends Fetcher {
    public CourseFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
    }

    /**
     * 获取课程表
     * @return 课程表list
     * @throws IOException 网络错误
     */
    public abstract List<Lesson> fetch() throws IOException;
}
