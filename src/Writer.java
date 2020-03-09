import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArraySet;

public class Writer {

    protected static CopyOnWriteArraySet<String> buffer = new CopyOnWriteArraySet<>();

    protected static void threadForWrite(StringBuilder builder, PrintWriter writer) throws InterruptedException {

        buffer.add(builder.toString());

        if (buffer.size() >= 1_726_272) {
            Thread thread = new Thread(() ->
            {
                for (String number : buffer) {
                    writer.write(number);
                }
            });

            thread.start();
            thread.join();
        }
    }
}
