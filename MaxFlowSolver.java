import java.io.*;
import java.util.*;

public class MaxFlowSolver {
    static class Edge {
        int to, cap, rev;
        Edge(int to, int cap, int rev) {
            this.to = to; this.cap = cap; this.rev = rev;
        }
    }

    static int n;
    static List<Edge>[] graph;
    static boolean[] visited;

    static void addEdge(int u, int v, int c) {
        graph[u].add(new Edge(v, c, graph[v].size()));
        graph[v].add(new Edge(u, 0, graph[u].size() - 1));
    }

    static int dfs(int u, int t, int f) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder output = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                if (c > 0) {
                    addEdge(u, v, c);
                }
            }
            int source = 0, sink = n - 1;
            int flow = 0;
            while (true) {
                visited = new boolean[n];
                int pushed = dfs(source, sink, Integer.MAX_VALUE);
                if (pushed == 0) break;
                flow += pushed;
            }
            output.append(flow).append('\n');
        }
        System.out.print(output.toString());
    }
}

