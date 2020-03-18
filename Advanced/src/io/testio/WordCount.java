package io.testio;

import java.util.ArrayList;
import java.util.List;

/**
 * 词频统计类
 */
public class WordCount {
    // 词频二元组
    public class Tuple {
        // 单词
        public String word;
        // 出现次数
        public int count;
    }

    /**
     * 统计指定文件中各个单词的出现次数，并按单词的出现次数逆序排序，
     * 返回拍好序的List
     * 注意：只考虑输入英文的文件，统计时把所有单词转换为小写字母
     * 例如：输入 txtfile\THE TRAGEDY OF ROMEO AND JULIET.txt
     * 输出：[('and', 720), ('the', 681), ('i', 658), ('to', 577), ('a', 470),
     * ('of', 401), ('my', 361), ('that', 355), ('is', 349), ('in', 320)]
     * @param fn 要统计的文件名
     * @return
     */
    public List<Tuple> count(String fn) {
        List<Tuple> words = new ArrayList<>();

        return words;
    }
}
