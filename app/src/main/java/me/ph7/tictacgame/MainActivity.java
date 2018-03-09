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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;
    public static final int MAX_CELLS = 9;

    private int winnerId = -1;

    private ArrayList<Integer> player1 = new ArrayList<Integer>();
    private ArrayList<Integer> player2 = new ArrayList<Integer>();

    private int activePlayer = PLAYER_ONE_ID; // 1 = first, 2 = second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        Button btnSelected = (Button) view;
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

    private void checkWinner() {
        checkWinnerInRows();
        checkWinnerInColumns();

        if (isThereAWinner()) {
            if (winnerId == PLAYER_ONE_ID) {
                Toast.makeText(this, "Player 1 is the winnerId!", Toast.LENGTH_LONG).show();
            }

            if (winnerId == PLAYER_TWO_ID) {
                Toast.makeText(this, "Player 2 is the winnerId!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkWinnerInRows() {
        // First Row
        for (int row = 1; row <= 3; row++) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 1; row <= 3; row++) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }

        // Second Row
        for (int row = 4; row <= 6; row++) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 4; row <= 6; row++) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }

        // Third Row
        for (int row = 7; row <= 9; row++) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 7; row <= 9; row++) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }
    }

    private void checkWinnerInColumns() {
        // First Column
        for (int row = 1; row <= 7; row += 3) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 1; row <= 7; row += 3) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }

        // Second Column
        for (int row = 2; row <= 8; row += 3) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 2; row <= 8; row += 3) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }

        // Third Column
        for (int row = 3; row <= 9; row += 3) {
            if (player1.contains(row)) {
                winnerId = PLAYER_ONE_ID;
            }
        }
        for (int row = 3; row <= 9; row += 3) {
            if (player2.contains(row)) {
                winnerId = PLAYER_TWO_ID;
            }
        }
    }

    private void artificialPlayer() {
        ArrayList<Integer> unselectedCells = new ArrayList<Integer>();

        for (int cellId = 1; cellId <= MAX_CELLS; cellId++) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                unselectedCells.add(cellId);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(unselectedCells.size());
        int cellId = unselectedCells.get(randomIndex);

        Button btnSelected = getArtificialSelectedButton(cellId);
        playGame(cellId, btnSelected);
    }

    private Button getArtificialSelectedButton(int cellId) {
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

        return btnSelected;
    }

    private boolean isThereAWinner() {
        return winnerId != -1;
    }

    private void log(int cellId, Button btnSelected) {
        Log.d("Player:", String.valueOf(cellId));
        Log.d("Button Text:", btnSelected.getText().toString());
    }
}
