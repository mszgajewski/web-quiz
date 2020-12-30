import java.util.*;

class Main {
    static class Graph {
        int nVertices;
        List<Set<Integer>> adjacency;

        public Graph(int nVertices) {
            this.nVertices = nVertices;
            adjacency = new ArrayList<>(nVertices);
            for (int i = 0; i < nVertices; i++) {
                adjacency.add(new HashSet<>());
            }
        }

        public void addEdge(int from, int to) {
            adjacency.get(from - 1).add(to - 1);
        }

        public boolean hasCycles() {
            class DFS {
                boolean[] visited = new boolean[nVertices];
                boolean[] visiting = new boolean[nVertices];
                boolean hasCycle = false;

                void dfs(int from) {
                    visiting[from] = true;
                    for (Integer to : adjacency.get(from)) {
                        if (visiting[to]) {
                            hasCycle = true;
                            return;
                        } else if (!visited[to]) {
                            dfs(to);
                        }
                    }
                    visiting[from] = false;
                    visited[from] = true;
                }

                boolean check() {
                    for (int i = 0; i < nVertices; i++) {
                        if (!visited[i]) {
                            dfs(i);
                        }
                        if (hasCycle) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return new DFS().check();
        }

    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        Graph g = new Graph(v);
        while (e-- > 0) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            g.addEdge(from, to);
        }
        System.out.println(g.hasCycles() ? "NO" : "YES");
    }
}
