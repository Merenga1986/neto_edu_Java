package algo.orgraph;

import java.util.*;

public class DAG<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
        Map<Vertex<T>, Integer> paths = new HashMap<>();
        paths.put(from, 0);

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Set<Vertex<T>> added = new HashSet<>();
        queue.add(from);
        added.add(from);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll(); // вынимаем следующий элемент из очереди

            if (v.equals(to)) {
                return paths.get(v); // нашли город назначения — возвращаем расстояние
            }

            for (Vertex<T> neighbour : v.getAdjacent()) {
                if (!added.contains(neighbour)) {
                    added.add(neighbour);
                    queue.add(neighbour);
                    paths.put(neighbour, paths.get(v) + 1); // перелётов до соседа = перелётов до v + 1
                }
            }
        }

        return -1; // город назначения недостижим
    }
}