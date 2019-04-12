package cn.edu.uoh.cs.jwgl;

public abstract class Fetcher {
    // 学号，教工号
    String userId;

    // 用户名
    String userName;

    // 获取验证码时设置这个值
    String cookie = "";

    public Fetcher(String userId, String userName, String cookie) {
        this.userId = userId;
        this.userName = userName;
        this.cookie = cookie;
    }
}
