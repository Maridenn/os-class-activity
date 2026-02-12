import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {

    private int start;
    private int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
  
    @Override
    protected Integer compute() {

        if (end - start <= 5) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(
                Thread.currentThread().getName() +
                " computing sum from " + start + " to " + end
            );
            return sum;
        }

        int mid = (start + end) / 2;

        SumTask leftTask = new SumTask(start, mid);
        SumTask rightTask = new SumTask(mid + 1, end);

        leftTask.fork();

        int rightResult = rightTask.compute();

        int leftResult = leftTask.join();

        return leftResult + rightResult;
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(1, 2000);

        int result = pool.invoke(task);

        System.out.println("Final Result = " + result);
    }
}
