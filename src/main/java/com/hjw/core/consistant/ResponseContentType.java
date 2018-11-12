package com.hjw.core.consistant;

/**
 * Created by ho on 2018/11/12.
 */
public enum ResponseContentType {

    JSON("application/json;charset=UTF-8"),
    XML("text/xml"),
    HTML("text/html;"),
    TEXT("text/plain"),
    JSONP("application/javascript");

    public final String type;
    ResponseContentType(String type) {
        this.type = type;
    }

    public static ResponseContentType match(String name){
        if (name != null) {
            for (ResponseContentType item: ResponseContentType.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }
}
