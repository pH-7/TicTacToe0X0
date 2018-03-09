/**
 * Created by Pierre-Henry Soria <hi@ph7.me>. Feb 2018
 */

package me.ph7.tictacgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;

    private int winer = -1;

    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();

    int activePlayer = 1; // 1 = first, 2 = second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        Button btnSelected = (Button)view;
        int cellId = 0; // Default value

        switch (btnSelected.getId()) {
            case R.id.btn1:
                cellId = 1;
                break;

            case R.id.btn2:
                cellId = 2;
                break;

            case R.id.btn3:
                cellId = 3;
                break;

            case R.id.btn4:
                cellId = 4;
                break;

            case R.id.btn5:
                cellId = 5;
                break;

            case R.id.btn6:
                cellId = 6;
                break;

            case R.id.btn7:
                cellId = 7;
                break;

            case R.id.btn8:
                cellId = 8;
                break;

            case R.id.btn9:
                cellId = 9;
                break;
        }

        playGame(cellId, btnSelected);
    }

    public void playGame(int cellId, Button btnSelected) {
        btnSelected.setBackgroundColor(Color.RED);

        Log.d("Player:", String.valueOf(cellId));
        Log.d("Button Text:", btnSelected.getText().toString());

        if (activePlayer == 1) {
            btnSelected.setText("X");
            btnSelected.setBackgroundColor(Color.GREEN);
            player1.add(cellId);
            activePlayer = 2;
        } else if (activePlayer == 2) {
            btnSelected.setText("0");
            btnSelected.setBackgroundColor(Color.YELLOW);
            player2.add(cellId);
            activePlayer = 1;
        }

        btnSelected.setEnabled(false);
        checkWinner();
    }

    private void checkWinner()
    {
        checkWinnerInRows();
        checkWinnerInColumns();

        if (isThereAWinner()) {
            if (winer == PLAYER_ONE_ID) {
                Toast.makeText(this, "Player 1 is the winner!", Toast.LENGTH_LONG).show();
            }

            if (winer == PLAYER_TWO_ID) {
                Toast.makeText(this, "Player 2 is the winner!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkWinnerInRows() {
        int[] row1 = {1, 2, 3};
        int[] row2 = {4, 5, 6};
        int[] row3 = {7, 8, 9};

        // First Row
        if (Arrays.asList(row1).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row1).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }

        // Second Row
        if (Arrays.asList(row2).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row2).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }

        // Third Row
        if (Arrays.asList(row3).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row3).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }
    }

    private void checkWinnerInColumns()
    {
        int[] col1 = {1, 4, 7};
        int[] col2 = {2, 5, 8};
        int[] col3 = {3, 6, 9};

        // First Column
        if (Arrays.asList(col1).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col1).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }

        // Second Column
        if (Arrays.asList(col2).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col2).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }

        // Third Column
        if (Arrays.asList(col3).contains(player1)) {
            winer = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col3).contains(player2)) {
            winer = PLAYER_TWO_ID;
        }
    }

    private boolean isThereAWinner() {
        return winer != 0;
    }
}
