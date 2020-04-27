package regex;

public class RegexTest {
    /**
     * 编写验证学号的方法
     * 学号规则：12位数字组成，前4位代表年，后8位是序号
     * 例如：201812345678 是合法的学号，210012345678 不是合法的学号
     * @param no 学号
     * @return true 合法，false 不合法
     */
    public boolean checkNo(String no) {
        return true;
    }

    /**
     * 编写验证手机号的方法
     * 手机号规则: 11位数字，第一位数字是1
     * 例如：13908731234 是合法的学号，23908731234 不是合法的学号
     * @param tel 手机号
     * @return true 合法，false 不合法
     */
    public boolean checkTel(String tel) {
        return true;
    }

    /**
     * 编写验证教学班号的方法，若合法，并返回教工号
     * 教学班号规则: (学年-学年-学期)-课程代码-教工号-序号
     * 学年: 4位数字
     * 学期：1或2
     * 课程代码: 8位数字
     * 教工号: 8位数字
     * 序号: 1位数字
     * 例如: (2019-2020-2)-52040161-20090007-1是合法的教学班号
     * @return 若合法，并返回教工号; 不合法，返回null
     */
    public String checkClassNo(String no) {
        return null;
    }
}
