package common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
    static Logger logger = LoggerFactory.getLogger(TestLogger.class.getName());
//    static Logger logger = LogManager.getLogger(TestLogger.class.getName());

    public static void main(String[] args) {
        logger.trace("跟踪信息");
        // 注意参数的使用方式
        logger.debug("调试信息，参数1：{}，参数2：{}", 10, 20);
        logger.info("一般信息");
        logger.warn("警告信息");
        logger.error("错误信息", new Exception("错误信息"));
    }
}
