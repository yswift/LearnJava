package cn.edu.uoh.cs.ws;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by yanli on 13-6-10.
 */
public class UTTermTools { //extends TestCase {

    public static void main(String[] args) {
        UTTermTools t = new UTTermTools();
        t.testGetCurrentTerm();
    }

    @Test
    public void testGetCurrentTerm() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2013, Calendar.JANUARY, 10);
        TermTools.Term t1 = TermTools.getCurrentTerm(c1);
        System.out.println(t1.toString());
        assertEquals("2012-2013-1", t1.toString());

        Calendar c2 = Calendar.getInstance();
        c2.set(2013, 2, 10);
        TermTools.Term t2 = TermTools.getCurrentTerm(c2);
        System.out.println(t2.toString());
        assertEquals("2012-2013-2", t2.toString());

        Calendar c3 = Calendar.getInstance();
        c3.set(2013, 8, 10);
        TermTools.Term t3 = TermTools.getCurrentTerm(c3);
        System.out.println(t3.toString());
        assertEquals("2013-2014-1", t3.toString());

        ArrayList<TermTools.Term> terms = TermTools.getTerms();
        for (TermTools.Term t : terms) {
            System.out.println(t);
        }
    }
}
