package com.example.tictactoagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tictactoagame.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player);

        binding.sumbitNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = binding.player1NameEt.getText().toString();
                String player2 = binding.player2NameEt.getText().toString();

                Intent intent = new Intent(PlayerActivity.this, DisplayGameActivity.class);
                intent.putExtra("PLAYER_NAMES", new String[] {player1, player2});
                startActivity(intent);
            }
        });

    }
}