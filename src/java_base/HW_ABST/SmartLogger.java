package java_base.HW_ABST;

import java.time.LocalDateTime;

public class SmartLogger implements Logger {
    private int callCount = 0;

    @Override
    public void log(String msg) {
        callCount++;
        String level = msg.toLowerCase().contains("error") ? "ERROR" : "INFO";
        System.out.println(level + "#" + callCount + " [" + LocalDateTime.now() + "] " + msg);
    }
}