package com.example.tictactoagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayGameActivity extends AppCompatActivity {

    private TicTacToaBoard ticTacToaBoard;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_game);

        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        homeBtn = (Button) findViewById(R.id.homeBtn);
        ticTacToaBoard = findViewById(R.id.ticTacToaBoard);
        playerTurn = (TextView) findViewById(R.id.playerNameTV);

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");
        if (playerNames != null){
            playerTurn.setText(playerNames[0] + "'s Turn");
        }

        ticTacToaBoard.setupGame(
                playAgainBtn,
                homeBtn,
                playerTurn,
                playerNames

        );
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToaBoard.resetGame();
                ticTacToaBoard.invalidate();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayGameActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}