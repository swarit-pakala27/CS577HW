import java.io.*;
import java.util.*;

public class BipartiteMatching {
    static class Edge {
        int to, cap, rev;
        Edge(int to, int cap, int rev) {
            this.to = to; this.cap = cap; this.rev = rev;
        }
    }

    int N;                          // total number of vertices
    List<Edge>[] graph;
    boolean[] visited;

    @SuppressWarnings("unchecked")
    public BipartiteMatching(int n) {
        N = n;
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // add directed edge u->v with capacity c, and reverse edge v->u with 
capacity 0
    void addEdge(int u, int v, int c) {
        graph[u].add(new Edge(v, c, graph[v].size()));
        graph[v].add(new Edge(u, 0, graph[u].size() - 1));
    }

    // DFS to find an augmenting path, returns flow pushed
    int dfs(int u, int t, int f) {
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

    // Ford-Fulkerson max flow from s to t
    int maxFlow(int s, int t) {
        int flow = 0;
        while (true) {
            visited = new boolean[N];
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
            int m = Integer.parseInt(st.nextToken()); // size of A
            int n = Integer.parseInt(st.nextToken()); // size of B
            int q = Integer.parseInt(st.nextToken()); // number of edges
            // total vertices = source + A + B + sink = 2 + m + n
            int source = 0;
            int offsetA = 1;
            int offsetB = offsetA + m;
            int sink = offsetB + n;
            BipartiteMatching mf = new BipartiteMatching(sink + 1);
            // source -> A
            for (int i = 0; i < m; i++) {
                mf.addEdge(source, offsetA + i, 1);
            }
            // B -> sink
            for (int j = 0; j < n; j++) {
                mf.addEdge(offsetB + j, sink, 1);
            }
            // edges A -> B
            for (int e = 0; e < q; e++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1; // in A
                int v = Integer.parseInt(st.nextToken()) - 1; // in B
                if (u >= 0 && u < m && v >= 0 && v < n) {
                    mf.addEdge(offsetA + u, offsetB + v, 1);
                }
            }
            int matching = mf.maxFlow(source, sink);
            boolean perfect = (matching == m && matching == n);
            sb.append(matching).append(' ').append(perfect ? 'Y' : 
'N').append('\n');
        }
        System.out.print(sb.toString());
    }
}

