import java.util.*;

public class FurthestInFuturePaging {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int instances = sc.nextInt();
        for (int inst = 0; inst < instances; inst++) {
            int k = sc.nextInt(); // cache size
            int n = sc.nextInt(); // number of requests
            int[] requests = new int[n];
            for (int i = 0; i < n; i++) {
                requests[i] = sc.nextInt();
            }

            // Build future positions map
            Map<Integer, Queue<Integer>> future = new HashMap<>();
            for (int i = 0; i < n; i++) {
                future.putIfAbsent(requests[i], new LinkedList<>());
                future.get(requests[i]).add(i);
            }

            Set<Integer> cache = new HashSet<>();
            int faults = 0;

            for (int i = 0; i < n; i++) {
                int page = requests[i];
                future.get(page).poll(); // remove this occurrence

                if (cache.contains(page)) {
                    continue; // hit
                }

                faults++; // miss
                if (cache.size() < k) {
                    cache.add(page);
                } else {
                    // Need to evict: find page used furthest in the future
                    int toRemove = -1;
                    int maxFuture = -1;
                    for (int p : cache) {
                        Queue<Integer> q = future.get(p);
                        int nextUse = q.isEmpty() ? Integer.MAX_VALUE : 
q.peek();
                        if (nextUse > maxFuture) {
                            maxFuture = nextUse;
                            toRemove = p;
                        }
                    }
                    cache.remove(toRemove);
                    cache.add(page);
                }
            }

            System.out.println(faults);
        }
        sc.close();
    }
}

