package lesson1.Participants;

import java.util.Arrays;

public class Team {
    private final String teamName;
    private boolean isFinished = false;
    private final MemberAction[] team;

    public Team(String teamName, MemberAction[] members) {
        this.teamName = teamName;
        this.team = members;
    }

    public MemberAction getTeamMember(int memberNum) {
        return team[memberNum];
    }

    public int getMembersCount () {
        return team.length;
    }

    public void showResults() {
        System.out.println("Команда \"" + teamName + "\" cправилась c полосой препятствий: " + isFinished);
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "\nTeam{" +
                "\n\tteamName='" + teamName + '\'' +
                ",\n\tisFinished=" + isFinished +
                ",\n\tteam=" + Arrays.toString(team) + '\n' +
                '}'  + '\n';
    }

}
