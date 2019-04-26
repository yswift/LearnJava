package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@ServerEndpoint("/websocket")
public class ChatServer {
    //concurrent包的线程安全Set，用来存放每个客户端对应的ChatServer对象。
    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<>();

    // 昵称
    private String nickname;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        List<String> users = webSocketSet.stream().map(u -> u.nickname).collect(Collectors.toList());
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.type = ChatType.Msg.name();
        chatMsg.nickname = "系统消息";
        chatMsg.msg = "【" + nickname + "】下线";
        chatMsg.users = users;
        sendMsg2All(chatMsg, true);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        ChatMsg chatMsg = ChatMsg.fromClientMsg(message);

        if (chatMsg.type.equals(ChatType.Connection.name())) {
            nickname = chatMsg.nickname;
            List<String> users = webSocketSet.stream().map(u -> u.nickname).collect(Collectors.toList());
            chatMsg = ChatMsg.newUser(nickname, users);
            sendMsg2All(chatMsg, false);
            return;
        }
        sendMsg2All(chatMsg, true);
    }

    /*
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void sendMsg2All(ChatMsg chatMsg, boolean withoutMyself) {
        String msg = chatMsg.toJson();
        for (ChatServer item : webSocketSet) {
            if (withoutMyself && this == item) {
                continue;
            }
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
