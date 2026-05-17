import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<Vertex<V>> unsettledNodes;
    private final Map<Vertex<V>, Double> distances;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        dijkstra(graph);
    }

    private void dijkstra(WeightedGraph<V> graph) {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex<V> current = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(current);
            unsettledNodes.remove(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double edgeWeight = entry.getValue();
                double newDistance = getShortestDistance(current) + edgeWeight;

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, current);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private Vertex<V> getVertexWithMinimumWeight(Set<Vertex<V>> vertices) {
        Vertex<V> minimum = null;
        for (Vertex<V> vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex<V> v) {
        Double d = distances.get(v);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
