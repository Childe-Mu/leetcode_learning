package com.moon.book.algorithm_4th.no4_graph;
/******************************************************************************
 *  Compilation:  javac TopologicalX.java
 *  Execution:    java TopologicalX V E F
 *  Dependencies: Queue.java Digraph.java
 *
 *  Compute topological ordering of a DAG using queue-based algorithm.
 *  Runs in O(E + V) time.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DigraphGenerator;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * The {@code TopologicalX} class represents a data type for
 * determining a topological order of a <em>directed acyclic graph</em> (DAG).
 * A digraph has a topological order if and only if it is a DAG.
 * The <em>hasOrder</em> operation determines whether the digraph has
 * a topological order, and if so, the <em>order</em> operation
 * returns one.
 * <p>
 * This implementation uses a nonrecursive, queue-based algorithm.
 * The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the worst
 * case, where <em>V</em> is the number of vertices and <em>E</em>
 * is the number of edges.
 * Each instance method takes &Theta;(1) time.
 * It uses &Theta;(<em>V</em>) extra space (not including the digraph).
 * <p>
 * See {@link DirectedCycle}, {@link DirectedCycleX}, and
 * {@link EdgeWeightedDirectedCycle} to compute a
 * directed cycle if the digraph is not a DAG.
 * See {@link Topological} for a recursive version that uses depth-first search.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class TopologicalX {
    private final int[] ranks;              // ranks[v] = order where vertex v appers in order
    private Queue<Integer> order;     // vertices in topological order

    /**
     * Determines whether the digraph {@code G} has a topological order and, if so,
     * finds such a topological order.
     *
     * @param G the digraph
     */
    public TopologicalX(Digraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // initialize 
        ranks = new int[G.V()];
        order = new Queue<>();
        int count = 0;

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        if (count != G.V()) {
            order = null;
        }

        assert check(G);
    }

    /**
     * Determines whether the edge-weighted digraph {@code G} has a
     * topological order and, if so, finds such a topological order.
     *
     * @param G the digraph
     */
    public TopologicalX(EdgeWeightedDigraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // initialize 
        ranks = new int[G.V()];
        order = new Queue<Integer>();
        int count = 0;

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        if (count != G.V()) {
            order = null;
        }

        assert check(G);
    }

    /**
     * Unit tests the {@code TopologicalX} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);

        Digraph G1 = DigraphGenerator.dag(V, E);

        // corresponding edge-weighted digraph
        EdgeWeightedDigraph G2 = new EdgeWeightedDigraph(V);
        for (int v = 0; v < G1.V(); v++)
            for (int w : G1.adj(v))
                G2.addEdge(new DirectedEdge(v, w, 0.0));

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            G1.addEdge(v, w);
            G2.addEdge(new DirectedEdge(v, w, 0.0));
        }

        StdOut.println(G1);
        StdOut.println();
        StdOut.println(G2);

        // find a directed cycle
        TopologicalX topological1 = new TopologicalX(G1);
        if (!topological1.hasOrder()) {
            StdOut.println("Not a DAG");
        }

        // or give topologial sort
        else {
            StdOut.print("Topological order: ");
            for (int v : topological1.order()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        // find a directed cycle
        TopologicalX topological2 = new TopologicalX(G2);
        if (!topological2.hasOrder()) {
            StdOut.println("Not a DAG");
        }

        // or give topologial sort
        else {
            StdOut.print("Topological order: ");
            for (int v : topological2.order()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

    /**
     * Returns a topological order if the digraph has a topologial order,
     * and {@code null} otherwise.
     *
     * @return a topological order of the vertices (as an interable) if the
     * digraph has a topological order (or equivalently, if the digraph is a DAG),
     * and {@code null} otherwise
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * Does the digraph have a topological order?
     *
     * @return {@code true} if the digraph has a topological order (or equivalently,
     * if the digraph is a DAG), and {@code false} otherwise
     */
    public boolean hasOrder() {
        return order != null;
    }

    /**
     * The the rank of vertex {@code v} in the topological order;
     * -1 if the digraph is not a DAG
     *
     * @param v vertex
     * @return the position of vertex {@code v} in a topological order
     * of the digraph; -1 if the digraph is not a DAG
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return ranks[v];
        else return -1;
    }

    // certify that digraph is acyclic
    private boolean check(Digraph G) {

        // digraph is acyclic
        if (hasOrder()) {
            // check that ranks are a permutation of 0 to V-1
            boolean[] found = new boolean[G.V()];
            for (int i = 0; i < G.V(); i++) {
                found[rank(i)] = true;
            }
            for (int i = 0; i < G.V(); i++) {
                if (!found[i]) {
                    System.err.println("No vertex with rank " + i);
                    return false;
                }
            }

            // check that ranks provide a valid topological order
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (rank(v) > rank(w)) {
                        System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                v, w, v, rank(v), w, rank(w));
                        return false;
                    }
                }
            }

            // check that order() is consistent with rank()
            int r = 0;
            for (int v : order()) {
                if (rank(v) != r) {
                    System.err.println("order() and rank() inconsistent");
                    return false;
                }
                r++;
            }
        }


        return true;
    }

    // certify that digraph is acyclic
    private boolean check(EdgeWeightedDigraph G) {

        // digraph is acyclic
        if (hasOrder()) {
            // check that ranks are a permutation of 0 to V-1
            boolean[] found = new boolean[G.V()];
            for (int i = 0; i < G.V(); i++) {
                found[rank(i)] = true;
            }
            for (int i = 0; i < G.V(); i++) {
                if (!found[i]) {
                    System.err.println("No vertex with rank " + i);
                    return false;
                }
            }

            // check that ranks provide a valid topological order
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (rank(v) > rank(w)) {
                        System.err.printf("%d-%d: rank(%d) = %d, rank(%d) = %d\n",
                                v, w, v, rank(v), w, rank(w));
                        return false;
                    }
                }
            }

            // check that order() is consistent with rank()
            int r = 0;
            for (int v : order()) {
                if (rank(v) != r) {
                    System.err.println("order() and rank() inconsistent");
                    return false;
                }
                r++;
            }
        }


        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = ranks.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}