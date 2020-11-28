package lesson1.Participants.Humans;

public enum HumanTypes {
    SWIMMER("Пловец", 6, 200),
    RUNNER("Бегун", 2, 230),
    JUMPER("Прыгун", 12, 180),
    FIGHTER("Боец", 3, 180);

    private final String type;
    private final int jumpHeight;
    private final int runLength;

    HumanTypes(String type, int jumpHeight, int runLength) {
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
        return "HumanTypes{" +
                "type='" + type + '\'' +
                ", jumpHeight=" + jumpHeight +
                ", runLength=" + runLength +
                '}';
    }
}
