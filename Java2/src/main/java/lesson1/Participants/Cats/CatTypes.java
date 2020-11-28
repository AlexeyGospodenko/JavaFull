package lesson1.Participants.Cats;

public enum CatTypes {
    BLACK("Черный", 6, 230),
    WHITE("Белый", 10, 180),
    GRAY("Серый", 8, 200);

    private final String type;
    private final int jumpHeight;
    private final int runLength;

    CatTypes(String type, int jumpHeight, int runLength) {
        this.type = type;
        this.jumpHeight = jumpHeight;
        this.runLength = runLength;
    }

    public String getType() {
        return type;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getRunLength() {
        return runLength;
    }

    @Override
    public String toString() {
        return "CatTypes{" +
                "type='" + type + '\'' +
                ", jumpHeight=" + jumpHeight +
                ", runLength=" + runLength +
                '}';
    }
}
