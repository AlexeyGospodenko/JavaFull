package lesson8;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private static final int MAP_SIZE = 3;

    private static final ImageIcon ICON_X = new ImageIcon(Game.class.getResource("X.png"));
    private static final int ICON_X_HASH = ICON_X.hashCode();
    private static final ImageIcon ICON_DEF = new ImageIcon(Game.class.getResource("default.png"));
    private static final ImageIcon ICON_O = new ImageIcon(Game.class.getResource("O.png"));
    private static final int ICON_O_HASH = ICON_O.hashCode();
    private final JButton[][] map = new JButton[3][3];

    public Game() {
        super();
        setTitle("Крестики нолики");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        initMap();
        setVisible(true);
    }

    private void initMap() {
        JPanel jPanel = new JPanel(new GridLayout(MAP_SIZE, MAP_SIZE));
        add(jPanel);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new JButton(ICON_DEF);
                JButton btn = map[i][j];
                btn.addActionListener(e -> {
                    btn.setDisabledIcon(ICON_X);
                    btn.setEnabled(false);
                    if (checkWin(map, ICON_X_HASH)) {
                        isNewGame("Вы победили!");
                    } else {
                        if (isMapFull(map)) isNewGame("Ничья.");
                        turnAI(map);
                        if (checkWin(map, ICON_O_HASH)) isNewGame("Компьютер победил :(");
                    }
                });
                jPanel.add(btn);
            }
        }
    }

    //Проверка на заполненность игрового поля
    private boolean isMapFull(JButton[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isEnabled()) return false;
            }
        }
        return true;
    }

    //Проверка на победу
    private boolean checkWin(JButton[][] map, int playerHash) {
        //Проверка диагоналей
        boolean lrDiag = true, rlDiag = true;
        for (int i = 0; i < map.length; i++) {
            lrDiag = lrDiag & (map[i][i].getDisabledIcon().hashCode() == playerHash);
            rlDiag = rlDiag & (map[i][map.length - i - 1].getDisabledIcon().hashCode() == playerHash);
        }
        if (lrDiag || rlDiag) {
            return true;
        }
        //Проверка вертикалей и горизонатлей
        for (int i = 0; i < map.length; i++) {
            boolean vertLine = true, HorLine = true;
            for (int j = 0; j < map[i].length; j++) {
                vertLine = vertLine & (map[j][i].getDisabledIcon().hashCode() == playerHash);
                HorLine = HorLine & (map[i][j].getDisabledIcon().hashCode() == playerHash);
            }
            if (vertLine || HorLine) {
                return true;
            }
        }
        return false;
    }

    //"Диалоговое" окно на повторную игру
    private void isNewGame(String textWin) {

        setEnabled(false);

        JFrame frame = new JFrame();
        frame.setSize(300, 120);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        frame.add(grid);

        JButton closeGame = new JButton("Нет");
        closeGame.addActionListener(e -> {
            dispose();
            frame.dispose();
        });

        JButton newGame = new JButton("Да");
        newGame.addActionListener(e -> {
            new Game();
            dispose();
            frame.dispose();
        });

        grid.add(closeGame);
        grid.add(newGame);

        JLabel label = new JLabel(textWin + " Хотите сыграть еще раз?", SwingConstants.CENTER);
        frame.add(label);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        Container container = frame.getContentPane();
        container.add(flow, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void turnAI(JButton[][] map) {

        int[] bestTurn = checkBestTurn(map);

        if (bestTurn != null) {
            flagMap(map, bestTurn[0], bestTurn[1]);
            return;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isEnabled()) {
                    flagMap(map, j, i);
                    return;
                }
            }
        }
    }

    private void flagMap(JButton[][] map, int i, int j) {
        map[j][i].setDisabledIcon(ICON_O);
        map[j][i].setEnabled(false);
    }

    private int[] checkBestTurn(JButton[][] map) {

        int[] idx1 = new int[2];
        int[] idx2 = new int[2];
        int sum1 = 0, sum2 = 0;

        //Проверка диагоналей
        idx1[0] = -1;
        idx1[1] = -1;
        idx2[0] = -1;
        idx2[1] = -1;
        for (int i = 0; i < map.length; i++) {
            if (map[i][i].getDisabledIcon().hashCode() == ICON_X_HASH) sum1++;
            if (map[i][i].isEnabled()) {
                idx1[0] = i;
                idx1[1] = i;
            }

            if (map[i][map.length - i - 1].getDisabledIcon().hashCode() == ICON_X_HASH) sum2++;
            if (map[i][map.length - i - 1].isEnabled()) {
                idx2[0] = map.length - i - 1;
                idx2[1] = i;
            }
        }
        if (sum1 == 2 && idx1[0] != -1) return idx1;
        if (sum2 == 2 && idx2[0] != -1) return idx2;

        //Проверка вертикалей и горизонталей
        for (int i = 0; i < map.length; i++) {
            idx1[0] = -1;
            idx1[1] = -1;
            idx2[0] = -1;
            idx2[1] = -1;
            sum1 = 0;
            sum2 = 0;
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i].getDisabledIcon().hashCode() == ICON_X_HASH) sum1++;
                if (map[j][i].isEnabled()) {
                    idx1[0] = i;
                    idx1[1] = j;
                }
                if (map[i][j].getDisabledIcon().hashCode() == ICON_X_HASH) sum2++;
                if (map[i][j].isEnabled()) {
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
