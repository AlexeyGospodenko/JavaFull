package Chat;

import java.util.Arrays;
import java.util.Scanner;

public class XO {

    private static final int MAPSIZE = 3;
    private static final char[][] map = new char[MAPSIZE][MAPSIZE];
    private static final char USER = 'X';
    private static final char PC = 'O';
    private static final char DEFAULT = '_';

    public static void main(String[] args) {
        fillMap();
        printMap();

        while (true) {
            if (isMapFull()) break;
            turnPlayer();
            if (checkWin(USER)) break;

            if (isMapFull()) break;
            turnAi();
            if (checkWin(PC)) break;
        }
    }

    //Заполнение поля
    public static void fillMap() {
        for (char[] chars : map) {
            Arrays.fill(chars, DEFAULT);
        }
    }

    //Печать поля
    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
    }

    //Проверка на заполненность игрового поля
    public static boolean isMapFull() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DEFAULT) return false;
            }
        }
        System.out.println("Ничья - ходов больше нет");
        return true;
    }

    //Рисование хода на поле
    public static void flagMap(int x, int y, char player) {
        map[y][x] = player;
        printMap();
    }

    //Проверка на победу
    public static boolean checkWin(char player) {
        //Проверка диагоналей
        boolean lrDiag = true, rlDiag = true;
        for (int i = 0; i < map.length; i++) {
            lrDiag = lrDiag & (map[i][i] == player);
            rlDiag = rlDiag & (map[i][map.length - i - 1] == player);
        }
        if (lrDiag || rlDiag) {
            System.out.println("\n" + player + " Победил!");
            return true;
        }
        //Проверка вертикалей и горизонатлей
        for (int i = 0; i < map.length; i++) {
            boolean vertLine = true, HorLine = true;
            for (int j = 0; j < map[i].length; j++) {
                vertLine = vertLine & (map[j][i] == player);
                HorLine = HorLine & (map[i][j] == player);
            }
            if (vertLine || HorLine) {
                System.out.println("\n" + player + " Победил!");
                return true;
            }
        }

        return false;
    }

    //Ход игрока
    public static void turnPlayer() {
        System.out.println("\nВаш ход: ");
        Scanner sc = new Scanner(System.in);
        boolean checkTurn = false;
        int x, y;
        while (!checkTurn) {
            try {
                x = sc.nextInt();
                y = sc.nextInt();
                if (map[y - 1][x - 1] != DEFAULT) {
                    System.out.println("Эта клетка занята!");
                    continue;
                }
                checkTurn = true;
                flagMap(x - 1, y - 1, USER);

            } catch (Exception e) {
                System.out.println("Введите корректные данные");
                sc.nextLine();
                //continue; --Warning unnecessary
            }
        }
    }

    //Ход компьютера
    public static void turnAi() {
        System.out.println("\nХод компьютера: ");

        int[] bestTurn = checkBestTurn(USER);

        if (bestTurn != null) {
            flagMap(bestTurn[0], bestTurn[1], PC);
            return;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DEFAULT) {
                    flagMap(j, i, PC);
                    return;
                }
            }
        }
    }

    //4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
    public static int[] checkBestTurn(char player) {
        //char player = USER;
        int[] idx1 = new int[2];
        int[] idx2 = new int[2];
        int sum1 = 0, sum2 = 0;

        //Проверка диагоналей
        idx1[0] = -1; idx1[1] = -1;
        idx2[0] = -1; idx2[1] = -1;
        for (int i = 0; i < map.length; i++) {
            if (map[i][i] == player) sum1++;
            if (map[i][i] == DEFAULT) {
                idx1[0] = i;
                idx1[1] = i;
            }

            if (map[i][map.length - i - 1] == USER) sum2++;
            if (map[i][map.length - i - 1] == DEFAULT) {
                idx2[0] = map.length - i - 1;
                idx2[1] = i;
            }
        }
        if (sum1 == 2 && idx1[0] != -1) return idx1;
        if (sum2 == 2 && idx2[0] != -1) return idx2;

        //Проверка вертикалей и горизонталей
        for (int i = 0; i < map.length; i++) {
            idx1[0] = -1; idx1[1] = -1;
            idx2[0] = -1; idx2[1] = -1;
            sum1 = 0; sum2 = 0;
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] == player) sum1++;
                if (map[j][i] == DEFAULT) {
                    idx1[0] = i;
                    idx1[1] = j;
                }
                if (map[i][j] == player) sum2++;
                if (map[i][j] == DEFAULT) {
                    idx2[0] = j;
                    idx2[1] = i;
                }
            }
            if (sum1 == 2 && idx1[0] != -1) return idx1;
            if (sum2 == 2 && idx2[0] != -1) return idx2;
        }
        return null;
    }

}
