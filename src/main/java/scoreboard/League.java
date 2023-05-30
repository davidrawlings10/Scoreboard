package scoreboard;

public enum League {
    AVES("Aves"), NHL("NHL"), TEST("Test"), NFL("NFL"), NBA("NBA"), NCAA("NCAA");

    public final String label;

    private League(String label) {
        this.label = label;
    }
}
