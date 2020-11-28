package lesson1.Participants.Cats;

import lesson1.Participants.MemberAction;

public class Cat implements MemberAction {

    private final String type;
    private final int jumpHeight;
    private final int runLength;

    public Cat() {
        CatTypes[] catTypes = CatTypes.values();
        int typeIdx = (int) (Math.random() * catTypes.length);
        this.type = catTypes[typeIdx].getType();
        this.jumpHeight = catTypes[typeIdx].getJumpHeight();
        this.runLength = catTypes[typeIdx].getRunLength();

    }

    @Override
    public boolean run(int runLength) {
        if (this.runLength >= runLength) {
            System.out.println(this.getObjName() + " Справился с бегом");
            return true;
        } else {
            System.out.println(this.getObjName() + " Не справился с бегом");
            return false;
        }
    }

    @Override
    public boolean jump(int jumpHeight) {
        if (this.jumpHeight >= jumpHeight) {
            System.out.println(this.getObjName() + " Справился с прыжком");
            return true;
        } else {
            System.out.println(this.getObjName() + " Не справился с прыжком");
            return false;
        }
    }

    public String getObjName() {
        return this.getClass().getSimpleName() + " " + type;
    }

    @Override
    public String toString() {
        return "\n\t\tCat{" +
                "type='" + type + '\'' +
                ", jumpHeight=" + jumpHeight +
                ", runLength=" + runLength +
                '}';
    }
}
