package lesson1.Participants.Robots;

public enum RobotTypes {
    R2D2("R2D2", 1, 100),
    OPTIMUS("Оптимус Прайм", 30, 2000),
    SYROX("Сайрокс", 16, 300);

    private final String type;
    private final int jumpHeight;
    private final int runLength;

    RobotTypes(String type, int jumpHeight, int runLength) {
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
}
