package java_core.worker;

public class Main {
    public static void main(String[] args) {
        // System.out::println подходит как реализация onDone(String result),
        // так как сигнатуры совпадают: void println(String x)
        OnTaskDoneListener listener = System.out::println;

        Worker worker = new Worker(listener);
        worker.start();
    }
}
