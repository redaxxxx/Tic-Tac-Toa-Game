package com.example.tictactoagame;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;

public class GameLogic {
    int[][] gameBoard;
    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] names = {"Player 1, Player 2"};
    int player = 1;
    GameLogic(){
        gameBoard = new int[3][3];
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row - 1][col - 1] == 0){
            gameBoard[row - 1][col - 1] = player;

            if (player == 1){
                playerTurn.setText(names[1] + "'s Turn");
            }
            else {
                playerTurn.setText(names[0] + "'s Turn");
            }
            return true;
        }else {
            return false;
        }
    }

    public void resetGame(){
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                gameBoard[r][c] = 0;
            }
        }
        player = 1;
        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
        playerTurn.setText(names[0] + "'s Turn");
    }

    public boolean gameWinner(){
        boolean isWinner = false;

        // check player win with rows
        for (int r = 0; r < 3; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] &&
            gameBoard[r][0] != 0){
                isWinner = true;
            }
        }

        for (int c = 0; c < 3; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] &&
                    gameBoard[0][c] != 0){
                isWinner = true;
            }
        }

        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] &&
        gameBoard[0][0] != 0){
            isWinner = true;
        }

        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] &&
        gameBoard[2][0] != 0){
            isWinner = true;
        }

        int boardFilled = 0;
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if (gameBoard[r][c] != 0){
                    boardFilled += 1;
                }
            }
        }

        if (isWinner){
            playAgainButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText(names[player - 1] + " won!!!!!!!");
            return true;
        }else if (boardFilled == 9){
            playAgainButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game!!!!!!");
            return true;
        }else {
            return false;
        }
    }

    public void setPlayAgainButton(Button playAgainButton) {
        this.playAgainButton = playAgainButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
