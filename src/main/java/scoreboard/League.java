package scoreboard;

public enum League {
    AVES("Aves"), NHL("NHL"), TEST("Test");

    public final String label;

    private League(String label) {
        this.label = label;
    }
}
