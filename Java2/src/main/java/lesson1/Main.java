//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
//Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о
//действии в консоль).
//2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
//участники должны выполнять соответствующие действия (бежать или прыгать),
//результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.
//д.). У препятствий есть длина (для дорожки) или высота (для стены), а участников
//ограничения на бег и прыжки.
//3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников
//пройти этот набор препятствий. Если участник не смог пройти одно из препятствий, то
//дальше по списку он препятствий не идет.


package lesson1;

import lesson1.Obstacles.Course;
import lesson1.Obstacles.Obstacle;
import lesson1.Obstacles.Wall;
import lesson1.Obstacles.Cross;
import lesson1.Participants.Cats.Cat;
import lesson1.Participants.Humans.Human;
import lesson1.Participants.MemberAction;
import lesson1.Participants.Robots.Robot;
import lesson1.Participants.Team;

public class Main {
    public static void main(String[] args) {

        Team team1 = new Team("NaVy", new MemberAction[]{new Cat(), new Human(), new Human(), new Human()});
        System.out.println(team1.toString());

        Course course1 = new Course(new Obstacle[] {new Wall(), new Cross(), new Wall(), new Cross()});
        System.out.println(course1);

        course1.doIt(team1);

        System.out.println();
        team1.showResults();


        Team team2 = new Team("Alliance", new MemberAction[]{new Cat(), new Cat(), new Robot(), new Robot()});
        System.out.println(team2.toString());

        Course course2 = new Course(new Obstacle[] {new Wall(), new Cross(), new Wall(), new Cross()});
        System.out.println(course2);

        course2.doIt(team2);

        System.out.println();
        team2.showResults();

    }
}
