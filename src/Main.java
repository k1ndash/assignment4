public class Main {

    public static void main(String[] args) {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);
        fillWithWeights(weightedGraph);

        Vertex<String> almaty = weightedGraph.getVertex("Almaty");
        Vertex<String> kyzylorda = weightedGraph.getVertex("Kyzylorda");

        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(weightedGraph, almaty);
        outputPath(djk, kyzylorda);

        System.out.println("--------------------------------");

        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(weightedGraph, almaty);
        outputPath(bfs, kyzylorda);
    }

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void outputPath(Search<String> search, Vertex<String> target) {
        Iterable<Vertex<String>> path = search.pathTo(target);
        if (path == null) {
            System.out.println("No path found to " + target);
            return;
        }
        for (Vertex<String> v : path) {
            System.out.print(v + " -> ");
        }
        System.out.println();
    }
}
