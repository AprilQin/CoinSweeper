package com.example.ak.coinseeker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button buttons[][];
    int rows, cols, coins;
    CoinSweeper game = new CoinSweeper();
    SoundPool BGMsound;
    int win_sound, coin_sound, brick_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //get rid of the top bar
        ExtractConfigurationData();
        setupButtons(rows, cols);
        ((TextView)findViewById(R.id.total_coins)).setText(Integer.toString(coins));
        game.newGame(rows,cols,coins);

        BGMsound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        win_sound = BGMsound.load(this, R.raw.win_sound, 1);
        coin_sound = BGMsound.load(this, R.raw.coin_sound, 1);
        brick_sound = BGMsound.load(this, R.raw.brick_sound, 1);

    }

    private void setupButtons(final int num_rows, final int num_col) {
        buttons  = new Button[num_rows][num_col]; //initialize the buttons array
        TableLayout game_table = (TableLayout)findViewById(R.id.game_grid);
        for (int row = 0; row < num_rows; row++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            game_table.addView(tableRow);
            for (int col = 0; col < num_col; col++)
            {
                final int x = row;
                final int y = col;
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                tableRow.addView(button);
                buttons[x][y] = button; //save the buttons into buttons array

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int result = game.checkbutton(x,y);
                        LockButtonSize(rows,cols);
                        if (result == 1){
                            ((TextView)findViewById(R.id.Scans)).setText(Integer.toString(game.getScanCount()));
                            ((TextView)findViewById(R.id.Coins)).setText(Integer.toString(game.getCoinsFound()));
                            displayImage(x,y,true);
                            for (int i = 0; i < rows; i++)
                            {
                                for (int j = 0; j < cols; j++)
                                {
                                    if (game.getMatrixValue(i,j) == 2 || game.getMatrixValue(i,j) == 3)
                                    {
                                        buttons[i][j].setText(Integer.toString(game.getHiddenCoinsCount(i,j)));
                                    }
                                }
                            }
                            //if all coins are found, restart the game or go back to main page using popup diag
                            if(game.getCoinsFound() == coins){
                                congratulationDialog();
                            }
                        }
                        else if (result == 2){
                            ((TextView)findViewById(R.id.Scans)).setText(Integer.toString(game.getScanCount()));
                            button.setText(Integer.toString(game.getHiddenCoinsCount(x,y)));
                            displayImage(x,y,false);

                        }
                        else if (result == 3){
                            ((TextView)findViewById(R.id.Scans)).setText(Integer.toString(game.getScanCount()));
                            button.setText(Integer.toString(game.getHiddenCoinsCount(x,y)));
                        }


                    }
                });
            }
        }
    }

    public void displayImage(int row, int col, boolean found){
        Button button = buttons[row][col];
        if (found) { //found a coin
            int Coin_width = button.getWidth();
            int Coin_height = button.getHeight();
            if(game.getCoinsFound() == coins){ //last coin found
                BGMsound.play(win_sound, 1, 1, 1, 0, 1);

                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,Coin_width, Coin_height, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource,scaledBitmap));
                return;
            }

            BGMsound.play(coin_sound, 1, 1, 1, 0, 1);

            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.coin_second);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,Coin_width, Coin_height, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource,scaledBitmap));
        }
        else{ // did NOT find a coin
            BGMsound.play(brick_sound, 1, 1, 1, 0, 1);

            int Coin_width = button.getWidth();
            int Coin_height = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,Coin_width, Coin_height,false);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource,scaledBitmap));
        }
    }



    private void congratulationDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle("You Win!");
        alertDialog.setMessage("Congratulations! You have found all coins!");
        alertDialog.setIcon(R.drawable.fly_mario);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Restart",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });
        alertDialog.show();
    }

    private void ExtractConfigurationData() {
        Intent intent = getIntent();
        rows = intent.getIntExtra("number of rows", 0);
        cols = intent.getIntExtra("number of columns", 0);
        coins = intent.getIntExtra("number of coins", 0);
    }

    private void LockButtonSize(int num_rows, int num_col) {
        for (int row = 0; row < num_rows; row++)
        {
            for (int col = 0; col < num_col; col++)
            {
                Button button = buttons[row][col];
                int width = button.getWidth();
                int height = button.getHeight();
                //fix the button width to its current width
                button.setMinWidth(width);
                button.setMaxWidth(width);
                //fix the button height to its current height
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

}

