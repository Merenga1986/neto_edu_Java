package java_core.worker;

@FunctionalInterface
public interface OnTaskDoneListener {
    void onDone(String result);
}
