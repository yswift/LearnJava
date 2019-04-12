package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Exam;

import java.io.IOException;
import java.util.List;

public abstract class ExamFetcher extends Fetcher{
    public ExamFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
    }

    public abstract List<Exam> fetch() throws IOException;
}
