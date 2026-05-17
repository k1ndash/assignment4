import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> start) {
        marked.add(start);

        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.remove();

            for (Vertex<V> neighbor : graph.adjacencyList(current)) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }
}