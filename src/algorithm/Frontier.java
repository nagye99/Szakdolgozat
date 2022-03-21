package algorithm;

public interface Frontier<N extends Node> {

    boolean isEmpty();

    void add(N node);

    N removeNext();

}
