package com.example.peachandwatermelongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        one, two, noOne
    }

    Player currentPlayer = Player.one;

    Player[] playerChoices = new Player[9];

    int [] [] winnerRowsColumns = {{0,1,2} ,{3,4,5}, {6,7,8}, {0,3,6},{1,4,7}, {3,5,8}, {0,4,8},{2,4,6}};

    private boolean gameOver = false;

    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*playerChoices[0] = Player.noOne;
        playerChoices[1] = Player.noOne;
        playerChoices[2] = Player.noOne;
        playerChoices[3] = Player.noOne;
        playerChoices[4] = Player.noOne;
        playerChoices[5] = Player.noOne;
        playerChoices[6] = Player.noOne;
        playerChoices[7] = Player.noOne;
        playerChoices[8] = Player.noOne;*/

        for (int index =0; index < playerChoices.length; index++) {
            playerChoices[index] = Player.noOne;
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout= findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resettheGame();

            }
        });


    }
    public void imageViewisTapped (View imageView) {

        ImageView imagetappedView = (ImageView) imageView;
        int titag = Integer.parseInt(imagetappedView.getTag().toString());

        if (playerChoices[titag] == Player.noOne && gameOver == false) {

        imagetappedView.setTranslationX(-2000);

        playerChoices[titag] = currentPlayer;

        if(currentPlayer == Player.one) {
            imagetappedView.setImageResource(R.drawable.peach);
            currentPlayer = Player.two;
        }else if (currentPlayer == Player.two) {
            imagetappedView.setImageResource(R.drawable.watermelon);
            currentPlayer=Player.one;
        }
        imagetappedView.animate().translationXBy(2000).alpha(1).rotation(360).setDuration(1000);

        Toast.makeText(this,imagetappedView.getTag().toString(),Toast.LENGTH_LONG).show();

        for (int [] winnerColums : winnerRowsColumns) {
            if (playerChoices [winnerColums[0]] ==
                    playerChoices[winnerColums[1]]
                    && playerChoices[winnerColums[1]]
                    ==playerChoices[winnerColums[2]] && playerChoices[winnerColums[0]]!= Player.noOne) {

                btnReset.setVisibility( View.VISIBLE);
                gameOver = true;

                String winnerofGame = "";

                if (currentPlayer == Player.one) {
                    winnerofGame = "Player two";
                } else if (currentPlayer == Player.two) {
                    winnerofGame = "Player one";
                }
                Toast.makeText(this,winnerofGame + " is the winner!",Toast.LENGTH_LONG).show();

            }
        }

        }
    }

    //Reset game function
    private  void resettheGame () {

        for (int index = 0; index <gridLayout.getChildCount(); index++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
        Player currentPlayer = Player.one;
       /* playerChoices[0] = Player.noOne;
        playerChoices[1] = Player.noOne;
        playerChoices[2] = Player.noOne;
        playerChoices[3] = Player.noOne;
        playerChoices[4] = Player.noOne;
        playerChoices[5] = Player.noOne;
        playerChoices[6] = Player.noOne;
        playerChoices[7] = Player.noOne;
        playerChoices[8] = Player.noOne;*/

       for (int index =0; index < playerChoices.length; index++) {
           playerChoices[index] = Player.noOne;
       }

        gameOver=false;

        btnReset.setVisibility(View.VISIBLE);

    }
}