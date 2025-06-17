import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int instances = sc.nextInt();

        for (int t = 0; t < instances; t++) {
            int n = sc.nextInt();       // number of items
            int W = sc.nextInt();       // knapsack capacity
            int[] weights = new int[n];
            int[] values = new int[n];

            for (int i = 0; i < n; i++) {
                weights[i] = sc.nextInt();
                values[i] = sc.nextInt();
            }

            int result = knapsack(n, W, weights, values);
            System.out.println(result);
        }
    }

    static int knapsack(int n, int W, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {
            for (int w = W; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        return dp[W];
    }
}

