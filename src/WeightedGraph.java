import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> vertices = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
    }

    public void addEdge(V sourceData, V destData, double weight) {
        addVertex(sourceData);
        addVertex(destData);

        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        if (source.equals(dest)) return; // reject self-loops

        // reject parallel edges
        if (source.getAdjacentVertices().containsKey(dest)) return;

        source.addAdjacentVertex(dest, weight);

        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public boolean hasVertex(V data) {
        return vertices.containsKey(data);
    }

    public boolean hasEdge(V sourceData, V destData) {
        if (!hasVertex(sourceData) || !hasVertex(destData)) return false;
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);
        return source.getAdjacentVertices().containsKey(dest);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }

    public List<Vertex<V>> adjacencyList(Vertex<V> vertex) {
        return new ArrayList<>(vertex.getAdjacentVertices().keySet());
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : vertices.values()) {
            count += v.getAdjacentVertices().size();
        }
        if (undirected) count /= 2;
        return count;
    }
}