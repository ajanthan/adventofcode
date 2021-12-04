package com.github.ajanthan.adventofcode.day04;

import com.github.ajanthan.adventofcode.utils.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BingoGame {
    class WinData {
        int board;
        int draw;

        public WinData(int board, int draw) {
            this.board = board;
            this.draw = draw;
        }
    }

    public WinData findLastWinBoard(int[] draws, int[][][] boards, int[][][] marked) {
        int maxDraWin = 0, maxDrawBoard = -1;
        for (int i = 0; i < boards.length; i++) {
            int bingoMark = isBingo(draws, boards[i], marked[i]);
            if (bingoMark >= 0) {
                if (bingoMark > maxDraWin) {
                    maxDraWin = bingoMark;
                    maxDrawBoard = i;
                }
            }
        }
        return new WinData(maxDrawBoard, maxDraWin);
    }

    public WinData findFistWinBoard(int[] draws, int[][][] boards, int[][][] marked) {
        int minDraWin = draws.length, minDrawBoard = -1;
        for (int i = 0; i < boards.length; i++) {
            int bingoMark = isBingo(draws, boards[i], marked[i]);
            if (bingoMark >= 0) {
                if (bingoMark < minDraWin) {
                    minDraWin = bingoMark;
                    minDrawBoard = i;
                }
            }
        }
        return new WinData(minDrawBoard, minDraWin);
    }

    private int isBingo(int[] draw, int[][] board, int[][] marked) {
        for (int k = 0; k < draw.length; k++) {
            for (int i = 1; i < marked.length; i++) {
                for (int j = 1; j < marked[0].length; j++) {
                    if (marked[i][j] != 1 && board[i - 1][j - 1] == draw[k]) {
                        marked[i][j] = 1;
                        marked[i][0]++;
                        if (marked[i][0] == board.length) return k;
                        marked[0][j]++;
                        if (marked[0][j] == board[0].length) return k;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] draws1 = {7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1};
        int[][][] boards1 = {
                {
                        {22, 13, 17, 11, 0},
                        {8, 2, 23, 4, 24},
                        {21, 9, 14, 16, 7},
                        {6, 10, 3, 18, 5},
                        {1, 12, 20, 15, 19}
                },
                {
                        {3, 15, 0, 2, 22},
                        {9, 18, 13, 17, 5},
                        {19, 8, 7, 25, 23},
                        {20, 11, 10, 24, 4},
                        {14, 21, 16, 12, 6}
                },
                {
                        {14, 21, 17, 24, 4},
                        {10, 16, 15, 9, 19},
                        {18, 8, 23, 26, 20},
                        {22, 11, 13, 6, 5},
                        {2, 0, 12, 3, 7}
                }
        };
        int[][][] marked = new int[boards1.length][boards1[0].length + 1][boards1[0][0].length + 1];
        BingoGame bingoGame = new BingoGame();
        WinData win1 = bingoGame.findLastWinBoard(draws1, boards1, marked);
        System.out.println("First win board: " + win1.board);
        System.out.println("Winning draw number:" + draws1[win1.draw]);
        int unmarkedSum = 0;
        for (int i = 0; i < boards1[win1.board].length; i++) {
            for (int j = 0; j < boards1[win1.board][0].length; j++) {
                if (marked[win1.board][i + 1][j + 1] == 0) unmarkedSum += boards1[win1.board][i][j];
            }
        }
        System.out.println("Unmarked Sum: " + unmarkedSum);
        System.out.println("Score: " + draws1[win1.draw] * unmarkedSum);

        //final result
        List<String> lines = null;

        try {
            lines = InputFile.readInputFile("input-day04.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] draws2 = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).mapToInt(i -> i).toArray();
        List<List<int[]>> boardsList = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            List<int[]> board = new ArrayList<>();
            while (line.length() != 0) {
                board.add(Arrays.stream(line.trim().split("\\s+")).map(Integer::parseInt).mapToInt(n -> n).toArray());
                if (i < lines.size() - 1) line = lines.get(++i);
                else break;
            }
            boardsList.add(board);
        }
        int[][][] boards2 = new int[boardsList.size()][boardsList.get(0).size()][boardsList.get(0).get(0).length];
        for (int i = 0; i < boardsList.size(); i++) {
            boards2[i] = boardsList.get(i).toArray(new int[0][]);
        }
        int[][][] marked2 = new int[boards2.length][boards2[0].length + 1][boards2[0][0].length + 1];

        WinData win2 = bingoGame.findLastWinBoard(draws2, boards2, marked2);
        System.out.println("Final input");
        System.out.println("First win board: " + win2.board);
        System.out.println("Winning draw number:" + draws2[win2.draw]);
        int unmarkedSum2 = 0;
        for (int i = 0; i < boards2[win2.board].length; i++) {
            for (int j = 0; j < boards2[win2.board][0].length; j++) {
                if (marked2[win2.board][i + 1][j + 1] == 0) unmarkedSum2 += boards2[win2.board][i][j];
            }
        }
        System.out.println("Unmarked Sum: " + unmarkedSum2);
        System.out.println("Score 2: " + draws2[win2.draw] * unmarkedSum2);

    }
}
