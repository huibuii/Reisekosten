public class SearchResult <T> {
    private final T result;
    private final int index;

    public SearchResult(T result, int index) {
        this.result = result;
        this.index = index;
    }

    public T getResult() {
        return result;
    }

    public int getIndex() {
        return index;
    }
}
