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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;

    private int winner = -1;

    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();

    int activePlayer = PLAYER_ONE_ID; // 1 = first, 2 = second

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

        log(cellId, btnSelected);

        if (activePlayer == PLAYER_ONE_ID) {
            btnSelected.setText("X");
            btnSelected.setBackgroundColor(Color.GREEN);
            player1.add(cellId);
            activePlayer = PLAYER_TWO_ID;

            artificialPlayer();
        } else if (activePlayer == PLAYER_TWO_ID) {
            btnSelected.setText("0");
            btnSelected.setBackgroundColor(Color.YELLOW);
            player2.add(cellId);
            activePlayer = PLAYER_ONE_ID;
        }

        btnSelected.setEnabled(false);
        checkWinner();
    }

    private void checkWinner()
    {
        checkWinnerInRows();
        checkWinnerInColumns();

        if (isThereAWinner()) {
            if (winner == PLAYER_ONE_ID) {
                Toast.makeText(this, "Player 1 is the winner!", Toast.LENGTH_LONG).show();
            }

            if (winner == PLAYER_TWO_ID) {
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
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row1).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }

        // Second Row
        if (Arrays.asList(row2).contains(player1)) {
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row2).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }

        // Third Row
        if (Arrays.asList(row3).contains(player1)) {
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(row3).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }
    }

    private void checkWinnerInColumns()
    {
        int[] col1 = {1, 4, 7};
        int[] col2 = {2, 5, 8};
        int[] col3 = {3, 6, 9};

        // First Column
        if (Arrays.asList(col1).contains(player1)) {
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col1).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }

        // Second Column
        if (Arrays.asList(col2).contains(player1)) {
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col2).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }

        // Third Column
        if (Arrays.asList(col3).contains(player1)) {
            winner = PLAYER_ONE_ID;
        }
        if (Arrays.asList(col3).contains(player2)) {
            winner = PLAYER_TWO_ID;
        }
    }

    private void artificialPlayer() {
        ArrayList<Integer> unselectedCells = new ArrayList<Integer>();

        for (int cellId = 1; cellId < 10; cellId++) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                unselectedCells.add(cellId);
            }
        }

        runArtificialPlayer(unselectedCells);
    }

    private void runArtificialPlayer(ArrayList<Integer> unselectedCells) {

        Random random = new Random();
        int randomIndex = random.nextInt(unselectedCells.size() - 0) + 1;
        int cellId = unselectedCells.get(randomIndex);

        Button btnSelected;
        switch (cellId) {
            case 1:
                btnSelected = findViewById(R.id.btn1);
                break;

            case 2:
                btnSelected = findViewById(R.id.btn2);
                break;

            case 3:
                btnSelected = findViewById(R.id.btn3);
                break;

            case 4:
                btnSelected = findViewById(R.id.btn4);
                break;

            case 5:
                btnSelected = findViewById(R.id.btn5);
                break;

            case 6:
                btnSelected = findViewById(R.id.btn6);
                break;

            case 7:
                btnSelected = findViewById(R.id.btn7);
                break;

            case 8:
                btnSelected = findViewById(R.id.btn8);
                break;

            case 9:
                btnSelected = findViewById(R.id.btn9);
                break;

            default:
                btnSelected = findViewById(R.id.btn1);
        }

        playGame(cellId, btnSelected);
    }

    private boolean isThereAWinner() {
        return winner != -1;
    }

    private void log(int cellId, Button btnSelected) {
        Log.d("Player:", String.valueOf(cellId));
        Log.d("Button Text:", btnSelected.getText().toString());
    }
}
