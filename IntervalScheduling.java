import java.util.*;

public class IntervalScheduling {
    static class Job implements Comparable<Job> {
        int start, end;
        Job(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Job other) {
            return this.end - other.end;  // sort by earliest end time
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int instances = sc.nextInt();
        for (int t = 0; t < instances; t++) {
            int n = sc.nextInt();
            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                jobs[i] = new Job(s, e);
            }
            Arrays.sort(jobs);  // O(n log n)
            int count = 0;
            int currentEnd = 0;
            for (Job job : jobs) {
                if (job.start >= currentEnd) {
                    count++;
                    currentEnd = job.end;
                }
            }
            System.out.println(count);
        }
        sc.close();
    }
}

