package io.testio;

import io.txtfile.TxtFile;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTWordCount {
    @Test
    public void testWordCount() throws Exception {
        // 使用 io.txtfile 中的 THE TRAGEDY OF ROMEO AND JULIET.txt
        String fn = TxtFile.getFullName("THE TRAGEDY OF ROMEO AND JULIET.txt");
        WordCount wordCount = new WordCount();
        List<WordCount.Tuple> words = wordCount.count(fn);

        assertTrue("单词数 > 3500", words.size() > 3500);

        // 前5位的单词
        String[] top5 = {"and", "the", "i", "to", "a"};
        for (int i=0; i<top5.length; i++) {
            WordCount.Tuple tuple = words.get(i);
            assertEquals("前5位单词", top5[i], tuple.word);
        }

        // 验证前20次序
        int c = words.get(0).count;
        for (int i=0; i<20; i++) {
            WordCount.Tuple tuple = words.get(i);
            assertTrue("验证排序", c >= tuple.count);
            c = tuple.count;
        }
    }
}
