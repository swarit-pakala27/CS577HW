import java.io.*;
import java.util.*;

public class BipartiteMatching {
    static class Edge {
        int to, cap, rev;
        Edge(int to, int cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    private final List<Edge>[] graph;
    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public BipartiteMatching(int n) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // add an edge u→v with capacity c, plus the reverse edge v→u of 
capacity 0
    public void addEdge(int u, int v, int c) {
        graph[u].add(new Edge(v, c, graph[v].size()));
        graph[v].add(new Edge(u, 0, graph[u].size() - 1));
    }

    // DFS to find one augmenting path; returns flow pushed
    private int dfs(int u, int t, int f) {
        if (u == t) return f;
        visited[u] = true;
        for (Edge e : graph[u]) {
            if (e.cap > 0 && !visited[e.to]) {
                int pushed = dfs(e.to, t, Math.min(f, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph[e.to].get(e.rev).cap += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    // Ford–Fulkerson max‐flow from s to t
    public int maxFlow(int s, int t) {
        int flow = 0;
        while (true) {
            visited = new boolean[graph.length];
            int pushed = dfs(s, t, Integer.MAX_VALUE);
            if (pushed == 0) break;
            flow += pushed;
        }
        return flow;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new 
InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int source  = 0;
            int offsetA = 1;
            int offsetB = offsetA + m;
            int sink    = offsetB + n;
            BipartiteMatching mf = new BipartiteMatching(sink + 1);

            // source → A
            for (int i = 0; i < m; i++) {
                mf.addEdge(source, offsetA + i, 1);
            }
            // B → sink
            for (int j = 0; j < n; j++) {
                mf.addEdge(offsetB + j, sink, 1);
            }
            // A → B edges
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                if (u >= 0 && u < m && v >= 0 && v < n) {
                    mf.addEdge(offsetA + u, offsetB + v, 1);
                }
            }

            int matching = mf.maxFlow(source, sink);
            boolean perfect = (matching == m && matching == n);
            sb.append(matching)
              .append(' ')
              .append(perfect ? 'Y' : 'N')
              .append('\n');
        }
        System.out.print(sb);
    }
}
