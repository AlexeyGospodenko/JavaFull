package lesson1.Obstacles;

import lesson1.Participants.MemberAction;

public class Wall extends Obstacle{

    private final static int MAXHEIGHT = 12;
    private final static int MINHEIGHT = 8;
    private int wallHeight;

    public Wall() {
        this.wallHeight = MINHEIGHT + (int) (Math.random() * (MAXHEIGHT - MINHEIGHT + 1));
    }

    @Override
    public boolean overcome (MemberAction member) {
        return member.jump(wallHeight);
     }

    @Override
    public String toString() {
        return "\n\tWall{" +
                "wallHeight=" + wallHeight +
                '}';
    }
}
