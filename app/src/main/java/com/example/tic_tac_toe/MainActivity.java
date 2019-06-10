package com.example.tic_tac_toe;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playbtn = (Button) findViewById(R.id.playbtn);
        playbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText player1 = (EditText) findViewById(R.id.P1E);
                EditText player2 = (EditText) findViewById(R.id.P2E);
                String players = player1.getText().toString() + " "+player2.getText().toString();

                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startIntent.putExtra("org.codestromer.playernames", players);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        //final MediaPlayer exit2=MediaPlayer.create(this,R.raw.warning);
        //exit2.start();
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Closing Application")
                .setMessage("Are you sure you want to close this game?")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if(exit2.isPlaying())
                        //    exit2.stop();

                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
