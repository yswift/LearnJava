package cn.edu.uoh.cs.ws;

import java.util.ArrayList;
import java.util.Calendar;

public class TermTools {
    public static class Term {
        public int fromYear;
        public int toYear;
        public int term;

        public String getSYear() {
            return fromYear + "-" + toYear;
        }

        public String getSTerm() {
            return String.valueOf(term);
        }

        @Override
        public String toString() {
            return fromYear +
                    "-" + toYear +
                    "-" + term;
        }
    }

    public static Term parse(String st) {
        String[] t = st.split("-");
        Term term = new Term();
        term.fromYear = Integer.parseInt(t[0]);
        term.toYear = Integer.parseInt(t[1]);
        term.term = Integer.parseInt(t[2]);
        return term;
    }

    /**
     * 获取前3个学期及后一个学期
     *
     * @return 获取前3个学期及后一个学期
     */
    public static ArrayList<Term> getTerms() {
        Term t = getCurrentTerm();
        ArrayList<Term> terms = getBeforeTerm(t, 3);
        terms.add(t);
        terms.add(getAfterTerm(t));
        return terms;
    }

    public static Term getCurrentTerm(Calendar calendar) {
        Term term = new Term();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (month >= Calendar.AUGUST) {
            // 月份大于等于8月
            term.fromYear = year;
            term.toYear = year + 1;
            term.term = 1;
            return term;
        }
        if (month >= Calendar.FEBRUARY) {
            term.fromYear = year - 1;
            term.toYear = year;
            term.term = 2;
            return term;
        }
        term.fromYear = year - 1;
        term.toYear = year;
        term.term = 1;
        return term;
    }

    /**
     * 根据现在的时间计算属于哪个学年和学期
     *
     * @return 根据现在的时间计算属于哪个学年和学期
     */
    public static Term getCurrentTerm() {
        Calendar calendar = Calendar.getInstance();
        return getCurrentTerm(calendar);
    }

    /**
     * 取term学期的前termCount个学期
     *
     * @param term 从该学期开始
     * @param termCount 向前推termCount个学期
     * @return 学期列表
     */
    public static ArrayList<Term> getBeforeTerm(Term term, int termCount) {
        ArrayList<Term> termList = new ArrayList<>();
        Term currentTerm = term;
        for (int i = 0; i < termCount; i++) {
            if (currentTerm.term == 2) {
                Term t = new Term();
                t.fromYear = currentTerm.fromYear;
                t.toYear = currentTerm.toYear;
                t.term = 1;
                termList.add(0, t);
                currentTerm = t;
            } else {
                Term t = new Term();
                t.fromYear = currentTerm.fromYear - 1;
                t.toYear = currentTerm.toYear - 1;
                t.term = 2;
                termList.add(0, t);
                currentTerm = t;
            }
        }
        return termList;
    }

    public static Term getAfterTerm(Term term) {
        if (term.term == 2) {
            Term t = new Term();
            t.fromYear = term.fromYear + 1;
            t.toYear = term.toYear + 1;
            t.term = 1;
            return t;
        } else {
            Term t = new Term();
            t.fromYear = term.fromYear;
            t.toYear = term.toYear;
            t.term = 2;
            return t;
        }
    }
}

