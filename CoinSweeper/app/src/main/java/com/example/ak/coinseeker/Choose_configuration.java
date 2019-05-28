package com.example.ak.coinseeker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Choose_configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_configuration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SetupCancelButton();
        SetupEnterButton();

    }

    private void SetupCancelButton() {
        Button cancel = (Button)findViewById(R.id.cancel_configuration);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void SetupEnterButton() {
        final EditText rows = (EditText) findViewById(R.id.enter_rows);
        final EditText columns = (EditText) findViewById(R.id.enter_columns);
        final EditText coins = (EditText) findViewById(R.id.enter_coins);

        //set focus to the first text field
        rows.requestFocus();

        Button enter = (Button) findViewById(R.id.enter_configuration);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int N_of_rows = Integer.parseInt(rows.getText().toString());
                int N_of_cols = Integer.parseInt(columns.getText().toString());
                int N_of_coins = Integer.parseInt(coins.getText().toString());

                if(N_of_rows <= 0){
                    Toast.makeText(Choose_configuration.this, "Invalid number of rows: number of rows must be greater than 0", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(N_of_cols <= 0){
                    Toast.makeText(Choose_configuration.this, "Invalid number of columns: number of columns must be greater than 0", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(N_of_coins <= 0){
                    Toast.makeText(Choose_configuration.this, "Invalid number of coins: number of coins must be greater than 0", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(N_of_rows > 6){
                    Toast.makeText(Choose_configuration.this, "Invalid number of rows: max number of rows is 6", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(N_of_cols > 15){
                    Toast.makeText(Choose_configuration.this, "Invalid number of columns: max number of columns is 15", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(N_of_coins > (N_of_rows*N_of_cols)){
                    Toast.makeText(Choose_configuration.this, "Invalid number of coins: number of coins must be less than the number of bricks", Toast.LENGTH_SHORT).show();
                    finish();
                }


                Intent returnIntent = new Intent();
                returnIntent.putExtra("number of rows", N_of_rows);
                returnIntent.putExtra("number of columns", N_of_cols);
                returnIntent.putExtra("number of coins", N_of_coins);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }


}
