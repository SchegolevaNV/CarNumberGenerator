import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Loader {

    private static final short THREADS_COUNT = 4;
    private static short region_count = 99;
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private static PrintWriter writer;

    public static void main(String[] args) throws FileNotFoundException {

        long start = System.currentTimeMillis();
        writer = new PrintWriter("res/numbers.txt");

        ArrayList<Thread> threads = new ArrayList<>();
        int regionsByThread = region_count / THREADS_COUNT;
        int startRegion = 1;

        for (int i = 1; i <= THREADS_COUNT; i++) {
            if (i != THREADS_COUNT) {
                threads.add(threadCreation(startRegion, startRegion + regionsByThread));
                startRegion += regionsByThread;
            } else {
                int stopRegion = startRegion + (region_count - startRegion) + 1;
                threads.add(threadCreation(startRegion, stopRegion));
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static Thread threadCreation(int startRegion, int stopRegion) {
        return new Thread(() -> {

            StringBuffer buffer = new StringBuffer();

            String[] numbers = Concatenation.getNumbers();
            String[] regionCodes = Concatenation.getRegionsCode();

            for (int regionCode = startRegion; regionCode < stopRegion; regionCode++) {
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {

                                StringBuilder builder = new StringBuilder();

                                builder.append(firstLetter);
                                builder.append(numbers[number]);
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(regionCodes[regionCode]);
                                builder.append("\n");

                                buffer.append(builder.toString());

                                if (buffer.length() >= 15_536_448) {
                                    try {
                                        Writer.threadForWrite(buffer, writer);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    buffer = new StringBuffer();
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
