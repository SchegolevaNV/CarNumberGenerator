import java.io.PrintWriter;

public class Writer {

    protected static void threadForWrite(StringBuffer carNumbers, PrintWriter writer) throws InterruptedException {
        StringBuffer buffer = new StringBuffer(carNumbers);
        Thread thread = new Thread(() -> {
            synchronized (writer) {
                writer.write(buffer.toString());
            }
        });
        thread.start();
        thread.join();
    }
}