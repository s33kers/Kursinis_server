package solver;

/**
 * Created by tadas.
 */
public class Unsolvable extends Exception {

    private String error;

    public Unsolvable() {}

    public Unsolvable(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
