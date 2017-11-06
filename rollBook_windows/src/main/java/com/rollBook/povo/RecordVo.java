package com.rollBook.povo;

import com.rollBook.po.Record;

/**
 * Created by 周太宇 on 2017/9/12.
 */
public class RecordVo extends Record {
    private int score;//这件事的分值
    private String name;//学生姓名
    private String thingName;//这件事的名字，如早退
    private String theDate;//事情发生的时间字符串
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getTheDate() {
        return theDate;
    }

    public void setTheDate(String theDate) {
        this.theDate = theDate;
    }
}
