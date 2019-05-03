package cn.edu.uoh.cs.springbootdemo.utils;

import java.util.function.Function;

/**
 * 用于存储web页面dropdown list或checkbox项的对象
 */
public class SelectListItem {
    private String value;
    private String text;
    private boolean selected;

    public SelectListItem() {
    }

    public SelectListItem(String value, String text, boolean selected) {
        this.value = value;
        this.text = text;
        this.selected = selected;
    }

    public SelectListItem(String value, String text, Function<String,Boolean> f) {
        this.value = value;
        this.text = text;
        this.selected = f.apply(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
