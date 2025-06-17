import java.util.*;
import java.io.*;

public class WeightedIntervalScheduling {
    static class Job implements Comparable<Job> {
        int start, end, weight;
        Job(int s, int e, int w) {
            start = s;
            end = e;
            weight = w;
        }
        public int compareTo(Job other) {
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int instances = sc.nextInt();
        for (int t = 0; t < instances; t++) {
            int n = sc.nextInt();
            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                int weight = sc.nextInt();
                jobs[i] = new Job(start, end, weight);
            }
            System.out.println(solve(jobs));
        }
    }

    static int solve(Job[] jobs) {
        Arrays.sort(jobs);
        int n = jobs.length;
        int[] dp = new int[n + 1];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1;
            p[i] = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (jobs[mid].end <= jobs[i].start) {
                    p[i] = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int include = jobs[i - 1].weight;
            if (p[i - 1] != -1) include += dp[p[i - 1] + 1];
            dp[i] = Math.max(dp[i - 1], include);
        }

        return dp[n];
    }
}

