package com.animated_anand.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt_reset_game;
    CardView cv_header;
    TextView tv_player_turn;
    int[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningCombo = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int current_player = 0, winner = 0,tap_count=0;
    Button[] btArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);

        bt_reset_game = findViewById(R.id.bt_reset_game);
        cv_header = findViewById(R.id.cv_tic_tac_toe);
        tv_player_turn = findViewById(R.id.tv_player_turn);

        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);

        btArray = new Button[]{bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8};
        bt_reset_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_player_turn.setText("Player O turn");
                current_player = 0;
                winner = 0;
                tap_count=0;
                for (int i = 0; i < 9; i++) {
                    btArray[i].setText("");
                    array[i] = 0;
                    btArray[i].setBackgroundColor(Color.parseColor("#FF6200EE"));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button current_button = findViewById(v.getId());
        int cbv = Integer.parseInt(v.getTag().toString());
        if (array[cbv] == 0) {
            tap_count++;
            if (current_player == 0) {
                current_button.setText("O");
                array[cbv] = 1;
                if (winner == 0)
                    winner = showResponse(0);
                if (winner == 0 && tap_count<9) tv_player_turn.setText("Player X turn");
                current_player = 1;
            } else {
                current_button.setText("X");
                array[cbv] = 2;
                if (winner == 0)
                    winner = showResponse(1);
                if (winner == 0 && tap_count<9) tv_player_turn.setText("Player O turn");
                current_player = 0;
            }
        }
    }

    private int showResponse(int current_player) {
        int cur_win = 0;
        for (int i = 0; i < 8; i++) {
            if ((array[winningCombo[i][0]] > 0) && (array[winningCombo[i][0]] == array[winningCombo[i][1]])
                    && (array[winningCombo[i][0]] == array[winningCombo[i][2]])) {
                if (current_player == 0) {
                    tv_player_turn.setText("Player O won!");
                    cur_win = 1;
                } else {
                    tv_player_turn.setText("Player X won!");
                    cur_win = 2;
                }
                btArray[winningCombo[i][0]].setBackgroundColor(Color.parseColor("#A62B2B"));
                btArray[winningCombo[i][1]].setBackgroundColor(Color.parseColor("#A62B2B"));
                btArray[winningCombo[i][2]].setBackgroundColor(Color.parseColor("#A62B2B"));
                return cur_win;
            }
            if(tap_count==9) tv_player_turn.setText("Game drawn!");
        }
        return 0;
    }
}