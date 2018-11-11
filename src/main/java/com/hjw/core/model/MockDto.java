package com.hjw.core.model;

/**
 * Created by ho on 2018/11/11.
 */
public class MockDto {

    private String name;
    private int workDay;
    private String respType;        // Response Content-type：如JSON、XML、HTML、TEXT、JSONP
    private String respExample;     // Response Content

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getRespExample() {
        return respExample;
    }

    public void setRespExample(String respExample) {
        this.respExample = respExample;
    }
}
