package com.example.gopherhunting;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class FieldActivity extends AppCompatActivity {

    private Button stop;
    int GOPHER_POSITION;
    final int GOPHER = 0;
    final static int PLAYER1 = 1;
    final static int PLAYER2 = 2;
    //Move Outcomes
    final int SUCCESS = 1;
    final int NEAR_MISS = 2;
    final int CLOSE_GUESS = 3;
    final int COMPLETE_MISS = 4;

    public Handler playerOneHandler;
    public Handler playerTwoHandler;


    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg) {
            int value = msg.what;
            switch (value) {
                case PLAYER1:
                    placePlayer(msg.arg1, PLAYER1);
                    player1Feedback.setText(outcomes[msg.arg2]);
                    if (msg.arg2 == SUCCESS)
                        gameStatus.setText("Player 1 won!");
                    break;
                case PLAYER2:
                    placePlayer(msg.arg1, PLAYER2);
                    player2Feedback.setText(outcomes[msg.arg2]);
                    if (msg.arg2 == SUCCESS)
                        gameStatus.setText("Player 2 won!");
                    break;
            }
        }
    };

    final int[] Table_Player1 = {
            R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6,R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt10,
            R.id.txt11, R.id.txt12, R.id.txt13, R.id.txt14, R.id.txt15, R.id.txt16, R.id.txt17, R.id.txt18, R.id.txt19, R.id.txt20,
            R.id.txt21, R.id.txt22, R.id.txt23, R.id.txt24, R.id.txt25, R.id.txt26, R.id.txt27, R.id.txt28, R.id.txt29, R.id.txt30,
            R.id.txt31, R.id.txt32, R.id.txt33, R.id.txt34, R.id.txt35, R.id.txt36, R.id.txt37, R.id.txt38, R.id.txt39, R.id.txt40,
            R.id.txt41, R.id.txt42, R.id.txt43, R.id.txt44, R.id.txt45, R.id.txt46, R.id.txt47, R.id.txt48, R.id.txt49, R.id.txt50,
            R.id.txt51, R.id.txt52, R.id.txt53, R.id.txt54, R.id.txt55, R.id.txt56, R.id.txt57, R.id.txt58, R.id.txt59, R.id.txt60,
            R.id.txt61, R.id.txt62, R.id.txt63, R.id.txt64, R.id.txt65, R.id.txt66, R.id.txt67, R.id.txt68, R.id.txt69, R.id.txt70,
            R.id.txt71, R.id.txt72, R.id.txt73, R.id.txt74, R.id.txt75, R.id.txt76, R.id.txt77, R.id.txt78, R.id.txt79, R.id.txt80,
            R.id.txt81, R.id.txt82, R.id.txt83, R.id.txt84, R.id.txt85, R.id.txt86, R.id.txt87, R.id.txt88, R.id.txt89, R.id.txt90,
            R.id.txt91, R.id.txt92, R.id.txt93, R.id.txt94, R.id.txt95, R.id.txt96, R.id.txt97, R.id.txt98, R.id.txt99, R.id.txt100
    };

    final int[] Table_Player2 = {
            R.id.txt101,R.id.txt102,R.id.txt103,R.id.txt104,R.id.txt105,R.id.txt106,R.id.txt107,R.id.txt108,R.id.txt109,R.id.txt110,
            R.id.txt111, R.id.txt112, R.id.txt113, R.id.txt114, R.id.txt115, R.id.txt116, R.id.txt117, R.id.txt118, R.id.txt119, R.id.txt120,
            R.id.txt121, R.id.txt122, R.id.txt123, R.id.txt124, R.id.txt125, R.id.txt126, R.id.txt127, R.id.txt128, R.id.txt129, R.id.txt130,
            R.id.txt131, R.id.txt132, R.id.txt133, R.id.txt134, R.id.txt135, R.id.txt136, R.id.txt137, R.id.txt138, R.id.txt139, R.id.txt140,
            R.id.txt141, R.id.txt142, R.id.txt143, R.id.txt144, R.id.txt145, R.id.txt146, R.id.txt147, R.id.txt148, R.id.txt149, R.id.txt150,
            R.id.txt151, R.id.txt152, R.id.txt153, R.id.txt154, R.id.txt155, R.id.txt156, R.id.txt157, R.id.txt158, R.id.txt159, R.id.txt160,
            R.id.txt161, R.id.txt162, R.id.txt163, R.id.txt164, R.id.txt165, R.id.txt166, R.id.txt167, R.id.txt168, R.id.txt169, R.id.txt170,
            R.id.txt171, R.id.txt172, R.id.txt173, R.id.txt174, R.id.txt175, R.id.txt176, R.id.txt177, R.id.txt178, R.id.txt179, R.id.txt180,
            R.id.txt181, R.id.txt182, R.id.txt183, R.id.txt184, R.id.txt185, R.id.txt186, R.id.txt187, R.id.txt188, R.id.txt189, R.id.txt190,
            R.id.txt191, R.id.txt192, R.id.txt193, R.id.txt194, R.id.txt195, R.id.txt196, R.id.txt197, R.id.txt198, R.id.txt199, R.id.txt200
    };


    int[] CheckPosition = new int[100];
    Thread t1 = new Thread(new Player1Runnable());
    Thread t2 = new Thread(new Player2Runnable());


    boolean isGopherFound = false;

    TextView player1Feedback;
    TextView player2Feedback;
    TextView gameStatus;
    final String[] outcomes = {"","Success","Near Miss", "Close Guess", "Complete Miss"};
    public void placePlayer(int pos, int item){
        TextView player1text;
        TextView player2text;
        switch(item){
            case GOPHER: {
                player1text = findViewById(Table_Player1[pos]);
                player1text.setBackgroundResource(R.drawable.image3);
                CheckPosition[pos] = GOPHER;
                player2text = findViewById(Table_Player2[pos]);
                player2text.setBackgroundResource(R.drawable.image3);
                CheckPosition[pos] = GOPHER;
                break;
            }
            case PLAYER1: {
                player1text = findViewById(Table_Player1[pos]);
                player1text.setBackgroundResource(R.drawable.image1);
                CheckPosition[pos] = PLAYER1;
                break;
            }
            case PLAYER2: {
                player1text = findViewById(Table_Player2[pos]);
                player1text.setBackgroundResource(R.drawable.image2);
                CheckPosition[pos] = PLAYER2;
                break;
            }
        }
    }

    public int proximityCalculator(int pos) {
        int prow = pos/10;
        int pcol = pos%10;
        int gorow = GOPHER_POSITION/10;
        int gocol = GOPHER_POSITION%10;

        if (pos == GOPHER_POSITION) {
            return SUCCESS;
        }
        else if ((prow == gorow && Math.abs(pcol - gocol) == 1) || (pcol == gocol && Math.abs(prow - gorow) == 1) || (Math.abs(pcol - gocol) == 1 && Math.abs(prow - gorow) == 1)) {
            return NEAR_MISS;
        }
        else if ((prow == gorow && Math.abs(pcol - gocol) == 2) || (pcol == gocol && Math.abs(prow - gorow) == 2) || (Math.abs(pcol - gocol) == 2 && Math.abs(prow - gorow) == 2)) {
            return CLOSE_GUESS;
        }
        else
            return COMPLETE_MISS;
    }


    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        stop = findViewById(R.id.stop);


        Arrays.fill(CheckPosition, -1);


        GOPHER_POSITION = (int) (Math.random() * 100) % 100;
        placePlayer(GOPHER_POSITION, GOPHER);

        player1Feedback = findViewById(R.id.player1Status);
        player2Feedback = findViewById(R.id.player2Status);
        gameStatus = findViewById(R.id.gameStatus);

        t1.start();
        t2.start();

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }



    // Posting Runnable
    class Player1Runnable implements Runnable {
        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            while (!isGopherFound) {
                final int myPos = (int) (Math.random() * 100) % 100;
                final int outcome = proximityCalculator(myPos);
                uiHandler.post(new Runnable() {
                    public void run() {
                        placePlayer(myPos, PLAYER1);
                        player1Feedback.setText(outcomes[outcome]);
                    }
                });
                if (outcome == SUCCESS) {
                    isGopherFound = true;
                    uiHandler.post(new Runnable() {
                        public void run() {
                            gameStatus.setText("Player 1 won!");
                            stop.setVisibility(Button.INVISIBLE);
                        }
                    });
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Looper.loop();
        }
    }

    // Sending Messages
    class Player2Runnable implements Runnable{
        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            int counter=0;

            while(!isGopherFound && counter < 100){
                final int outcome = proximityCalculator(counter);
                final int myPos = counter;
                Message msg = uiHandler.obtainMessage(FieldActivity.PLAYER2) ;
                msg.arg1 = myPos ;
                msg.arg2 = outcome;
                uiHandler.sendMessage(msg);

                if(outcome == SUCCESS){
                    isGopherFound =true;
                    stop.setVisibility(Button.INVISIBLE);
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }

//            playerTwoHandler = new Handler(Looper.getMainLooper()) {
//                public void handleMessage(Message msg) {
//                    uiHandler.sendMessage(msg);
//                }
//            };
            Looper.loop();
        }
    }

}