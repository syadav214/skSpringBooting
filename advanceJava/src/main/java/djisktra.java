import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Node {
    String label;
    List<Edge> adjacantNodes;
    HashMap<String, Integer> shortestPath;

    public Node(String label) {
        this.label = label;
        this.adjacantNodes = new ArrayList<>();
        this.shortestPath = new HashMap<>();
    }

    public void addEdge(Node node, int distance){
        this.adjacantNodes.add(new Edge(node, distance));
    }
}

class Edge {
    Node node;
    int distance;
    public Edge(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class djisktra {
    public static void main(String[] args){
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        a.addEdge(b,10);
        a.addEdge(c,15);

        b.addEdge(a,10);
        b.addEdge(d,12);
        b.addEdge(f, 15);

        c.addEdge(a,15);
        c.addEdge(e, 10);;

        d.addEdge(b,12);
        d.addEdge(e, 2);
        d.addEdge(f,1);

        e.addEdge(c,10);
        e.addEdge(d,2);
        e.addEdge(f,5);

        f.addEdge(b, 15);
        f.addEdge(d, 1);
        f.addEdge(e, 5);

        HashSet<Node> graph = new HashSet<>();
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);

        getDistance(graph, "A");

    }

    static void getDistance(HashSet<Node> graph, String startVertex) {
        graph.forEach(node -> {
            if(node.label.equals(startVertex)){
                System.out.println("A: 0");
            } else {

            }
        });
    }
}
