package lesson1.Obstacles;

import lesson1.Participants.Team;

import java.util.Arrays;

public class Course {
    Obstacle[] obstacle;

    public Course(Obstacle[] obstacle) {
        this.obstacle = obstacle;
    }

    public void doIt(Team team) {
        int curMember = 0;

        for (int i = 0; i < obstacle.length; i++) {
            for (int j = curMember; j < team.getMembersCount(); j++) {
                System.out.println(obstacle[i].toString());
                if (obstacle[i].overcome(team.getTeamMember(j))) {
                    if (i == obstacle.length - 1) team.setFinished(true);
                    break;
                } else {
                    curMember++;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "obstacle=" + Arrays.toString(obstacle) + '\n' +
                '}';
    }
}
