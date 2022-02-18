package com.example.peg;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PegGame {

    private int[][] board = new int[7][7]; //-1: outOfBounds, 0: no peg, 1: peg, 2: selected peg
    private boolean selected = false;
    private int selectedPegI;
    private int selectedPegJ;

    public PegGame() {
        generateBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void generateBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i < 2 || i > 4) && (j < 2 || j > 4)) {
                    board[i][j] = -1;
                } else if(i == 3 && j == 3) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = 1;
                }
            }
        }
    }

    public void clickPeg(View view) {
        int i = Character.getNumericValue(view.getTag().toString().charAt(1));
        int j = Character.getNumericValue(view.getTag().toString().charAt(0));
        if (!selected) {
            if (board[j][i] == 1) {
                selectPeg(i, j);
            }
        } else {
            if (selectedPegI == i && selectedPegJ == j) {
                deselectPeg(i, j);
            } else if (board[j][i] == 1) {
                deselectPeg(selectedPegI, selectedPegJ);
                selectPeg(i, j);
            } else if (board[j][i] == 0) {
                movimiento(i, j);
            }
        }
    }

    public void selectPeg(int i, int j) {
        selectedPegI = i;
        selectedPegJ = j;
        board[j][i] = 2;
        selected = true;
    }

    public void deselectPeg(int i, int j) {
        board[j][i] = 1;
        selected = false;
    }

    public void movimiento(int i, int j) {
        if (selectedPegI == i) {
            if ((selectedPegJ > j) && (selectedPegJ - j == 2)) { //abajo --> arriba
                if (board[selectedPegJ-1][i] == 1) {
                    board[selectedPegJ-1][i] = 0;
                    board[selectedPegJ][selectedPegI] = 0;
                    board[j][i] = 1;
                }
            } else if ((selectedPegJ < j) && (j - selectedPegJ == 2)) { //arriba --> abajo
                if (board[j-1][i] == 1) {
                    board[j-1][i] = 0;
                    board[selectedPegJ][selectedPegI] = 0;
                    board[j][i] = 1;
                }
            }
        } else if (selectedPegJ == j) {
            if ((selectedPegI > i) && (selectedPegI - i == 2)) { //derecha --> izquierda
                if (board[j][selectedPegI-1] == 1) {
                    board[j][selectedPegI-1] = 0;
                    board[selectedPegJ][selectedPegI] = 0;
                    board[j][i] = 1;
                }
            } else if ((selectedPegI < i) && (i - selectedPegI == 2)) { //izquierda --> derecha
                if (board[j][i-1] == 1) {
                    board[j][i-1] = 0;
                    board[selectedPegJ][selectedPegI] = 0;
                    board[j][i] = 1;
                }
            }
        } else {
            deselectPeg(i, j);
        }
    }

}
