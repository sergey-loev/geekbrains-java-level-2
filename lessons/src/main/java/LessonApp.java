import java.util.Arrays;

public class LessonApp {

    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;

    public float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public void runOneThread() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long a = System.currentTimeMillis();

        arr = calculate(arr);
        System.out.println("One thread method ends with: " + (System.currentTimeMillis() - a));
    }

    public void runTwoThreads() throws InterruptedException {
        float[] arr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];

        Arrays.fill(arr, 1.0f);

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);

        Thread thread1 =new Thread(() -> {
            float[] a1 = calculate(arr1);
            System.arraycopy(a1, 0, arr1, 0, a1.length);
        });

        Thread thread2 = new Thread(() -> {
            float[] a2 = calculate(arr2);
            System.arraycopy(a2, 0, arr2, 0, a2.length);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        System.out.println("Two threads method ends with: " + (System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {
        LessonApp lesson = new LessonApp();
        lesson.runOneThread();
        try {
            lesson.runTwoThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
