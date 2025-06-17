import java.util.*;

public class WeightedIntervalScheduling {
    static class Job implements Comparable<Job> {
        int start, end;
        long weight;
        Job(int s, int e, long w) {
            start = s;
            end = e;
            weight = w;
        }
        public int compareTo(Job other) {
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int instances = sc.nextInt();
            for (int t = 0; t < instances; t++) {
                int n = sc.nextInt();
                if (n == 0) {
                    System.out.println(0);
                    continue;
                }
                Job[] jobs = new Job[n];
                for (int i = 0; i < n; i++) {
                    int start = sc.nextInt();
                    int end = sc.nextInt();
                    long weight = sc.nextLong();
                    jobs[i] = new Job(start, end, weight);
                }
                System.out.println(solve(jobs));
            }
        } catch (Exception e) {
            System.out.println("Invalid input format.");
        } finally {
            sc.close();
        }
    }

    static long solve(Job[] jobs) {
        Arrays.sort(jobs);
        int n = jobs.length;
        long[] dp = new long[n + 1];
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
            long include = jobs[i - 1].weight;
            if (p[i - 1] != -1) include += dp[p[i - 1] + 1];
            dp[i] = Math.max(dp[i - 1], include);
        }

        return dp[n];
    }
}

