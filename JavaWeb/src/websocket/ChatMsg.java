package websocket;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

enum ChatType {
    Connection, // 连接
    Msg,  // 聊天
}
public class ChatMsg {
    String type;
    String nickname;
    String msg;
    List<String> users = new ArrayList<>(); // 在线用户昵称数组

    // 客户端发来的消息
    static ChatMsg fromClientMsg(String msg) {
        Gson gson = new Gson();
        ChatMsg cm = gson.fromJson(msg, ChatMsg.class);
        // cm.type = ChatType.Msg.name();
        return cm;
    }

    static ChatMsg newUser(String newUser, List<String> users) {
        ChatMsg cm = new ChatMsg();
        cm.type = ChatType.Msg.name();
        cm.nickname = "系统消息";
        cm.msg = "【" + newUser + "】上线了";
        cm.users = users;
        return cm;
    }

    String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
