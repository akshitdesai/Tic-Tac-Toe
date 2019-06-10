package com.example.tic_tac_toe;

import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] poss = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    int scorey = 0;
    int scorer = 0;

    boolean gameActive = true;


    String p1,p2;

    public void dropIn(View view) {

        TextView tv1 = (TextView) findViewById(R.id.P1S);
        TextView tv2 = (TextView) findViewById(R.id.P2S);

        MediaPlayer tap=MediaPlayer.create(this,R.raw.coin);



        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (state[tappedCounter] == 2 && gameActive) {

            state[tappedCounter] = activePlayer;
            tap.start();

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }
            counter.animate().rotation(3600).setDuration(300);

            for (int[] pos : poss) {

                if (state[pos[0]] == state[pos[1]] && state[pos[1]] == state[pos[2]] && state[pos[0]] != 2) {

                    // Somone has won!

                    gameActive = false;

                    String winner = "";

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    if (activePlayer == 1) {


                        winner = "Yellow";
                        scorey++;
                        winnerTextView.setTextColor(Color.YELLOW);
                        tv1.setText(p1+" : "+Integer.toString(scorey));


                    } else {


                        winner = "Red";
                        scorer++;
                        winnerTextView.setTextColor(Color.RED);
                        tv2.setText(p2+" : "+Integer.toString(scorer));
                    }


                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }

                int ok=0;
                for(int i=0;i<state.length;i++){
                    if(state[i]!=2)
                        ok=1;
                    else{
                        ok=0;break;
                    }
                }
                if(ok==1 && gameActive){

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setTextColor(Color.BLUE);

                    winnerTextView.setText("No one wins!! Game Over");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }

            }
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);
        MediaPlayer over=MediaPlayer.create(this,R.raw.over);

        over.start();

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }
        for (int i=0; i<state.length; i++) {

            state[i] = 2;

        }
        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent().hasExtra("org.codestromer.playernames")){


            String text = getIntent().getExtras().getString("org.codestromer.playernames");
            String[] plays = text.split(" ");

            TextView tv1 = (TextView) findViewById(R.id.P1S);
            tv1.setTextColor(Color.YELLOW);
            TextView tv2 = (TextView) findViewById(R.id.P2S);
            tv2.setTextColor(Color.RED);

            p1=plays[0];
            p2=plays[1];

            tv1.setText(plays[0]+" : 0");
            tv2.setText(plays[1]+" : 0");
        }

    }
}
