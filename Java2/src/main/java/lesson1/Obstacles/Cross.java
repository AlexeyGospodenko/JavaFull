package lesson1.Obstacles;

import lesson1.Participants.MemberAction;
import lesson1.Participants.Team;

public class Cross extends Obstacle {

    private final static int MAXLENGTH = 150;
    private final static int MINLENGTH = 230;
    private int crossLength;

    public Cross() {
        this.crossLength = MINLENGTH + (int) (Math.random() * (MAXLENGTH - MINLENGTH + 1));
    }

    @Override
    public boolean overcome (MemberAction member) {
        return member.run(crossLength);
    }

    @Override
    public String toString() {
        return "\n\tCross{" +
                "crossLength=" + crossLength +
                '}';
    }
}

