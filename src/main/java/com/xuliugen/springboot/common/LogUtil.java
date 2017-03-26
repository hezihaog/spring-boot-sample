package com.xuliugen.springboot.common;

/**
 * Created by xuliugen on 2017/3/26.
 */
public class LogUtil {

    public LogUtil() {
    }

    public static String getTraceId(String identityId, String entryMethodName) {
        String startTime = DateTime.now().toString();
        identityId = identityId != null ? identityId.toString() : "null";
        entryMethodName = entryMethodName != null ? entryMethodName : "null";
        return identityId + "-" + entryMethodName + "-" + startTime;
    }

    public static String getTraceId(Object identityId, String entryMethodName) {
        String startTime = DateTime.now().toString();
        String identityId1 = identityId != null ? identityId.toString() : "null";
        entryMethodName = entryMethodName != null ? entryMethodName : "null";
        return identityId1 + "-" + entryMethodName + "-" + startTime;
    }

    public static String getTraceId(String entryMethodName) {
        String startTime = DateTime.now().toString();
        entryMethodName = entryMethodName != null ? entryMethodName : "null";
        return entryMethodName + "-" + startTime;
    }

}
