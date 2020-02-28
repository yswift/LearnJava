package cn.edu.uoh.cs.jwgl;

import cn.edu.uoh.cs.ws.Exam;
import cn.edu.uoh.cs.ws.Lesson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainTest {
    private JTextField txtUserId;
    private JTextField txtPwd;
    private JTextField txtCode;
    private JButton btnLogin;
    private JButton btnCourse;
    private JButton btnExam;
    private JTextArea txtInfo;
    private JLabel lblCode;
    private JPanel mainPanel;
    private JButton btnScore;
    AccountTools tools = new AccountTools();


    public MainTest() throws IOException {
        init();
        btnLogin.addActionListener((e) -> {
            login();
        });
        btnCourse.addActionListener((e) -> {
            fetchCourse();
        });
        btnExam.addActionListener((e) -> {
            fetchExam();
        });
        btnScore.addActionListener((e) -> {
            fetchScore();
        });
    }

    void init() throws IOException {
        byte[] buf = tools.getSecretCode();
        String code = tools.verifyCode(buf);
        txtCode.setText(code);
        Image img = Toolkit.getDefaultToolkit().createImage(buf);
        lblCode.setIcon(new ImageIcon(img));
    }

    void login() {
        String id = txtUserId.getText().trim();
        String pwd = txtPwd.getText().trim();
        String code = txtCode.getText().trim();
        tools.init(id, pwd);
        String s;
        try {
            if (tools.login(code)) {
                s = "登陆成功, 姓名: " + tools.userName;
            } else {
                s = "登陆失败";
            }
        } catch (IOException e) {
            s = e.getMessage();
            e.printStackTrace();
        }
        txtInfo.setText(s);
    }

    void fetchCourse() {
        try {
            CourseFetcher fetcher = tools.createCourseFetcher();
            List<Lesson> lessonList = fetcher.fetch();
            showInfo(lessonList);
        } catch (IOException e) {
            txtInfo.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    void fetchExam() {
        try {
            ExamFetcher fetcher = tools.createExamFetcher();
            List<Exam> exmList = fetcher.fetch();
            showInfo(exmList);
        } catch (IOException e) {
            txtInfo.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    void fetchScore() {
        try {
            StudentScoreFetcher fetcher = tools.createScoreFetcher();
            String html = fetcher.fetch();
            txtInfo.setText(html);
        } catch (IOException e) {
            txtInfo.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    void showInfo(List list) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o).append("\n");
        }
        txtInfo.setText(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("MainTest");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MainTest().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
