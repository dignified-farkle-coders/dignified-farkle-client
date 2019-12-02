package io.farkle.dignifiedfarkleclient;

import android.graphics.Color;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import io.farkle.dignifiedfarkleclient.model.pojo.RandomXYPositions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

  private static final String TAG = "playgameactivity";
  Random rng = new Random();
  ImageView die1;
  ImageView die2;
  ImageView die3;
  ImageView die4;
  ImageView die5;
  ImageView die6;
  int randomDie;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_play_game);
    super.onCreate(savedInstanceState);

    ImageView die1 = findViewById(R.id.die_1);
    die1.setVisibility(View.GONE);
    ImageView die2 = findViewById(R.id.die_2);
    die2.setVisibility(View.GONE);
    ImageView die3 = findViewById(R.id.die_3);
    die3.setVisibility(View.GONE);
    ImageView die4 = findViewById(R.id.die_4);
    die4.setVisibility(View.GONE);
    ImageView die5 = findViewById(R.id.die_5);
    die5.setVisibility(View.GONE);
    ImageView die6 = findViewById(R.id.die_6);
    die6.setVisibility(View.GONE);

    Button reRoll = findViewById(R.id.re_roll);
    reRoll.setVisibility(View.GONE);

    Button stay = findViewById(R.id.stay);
    stay.setVisibility(View.GONE);

    Button roll = findViewById(R.id.roll);
    roll.setOnClickListener(v -> {
      int[] myArray = new int[6];

      for (int i = 0; i < myArray.length - 1; i++) {
        myArray[i] = rng.nextInt(5) + 1;
      }

      
      roll.setVisibility(View.GONE);
      reRoll.setVisibility(View.VISIBLE);
      stay.setVisibility(View.VISIBLE);

//      die1.setX(myArray[0]);
//      die1.setY(myArray[myArray.length-1]);
////      die1.setRotation(rng.nextInt(359));
      dieImage(myArray[0],die1);
      die1.setVisibility(View.VISIBLE);
//
//      die2.setX(myArray[1]);
//      die2.setY(myArray[myArray.length-2]);
////      die2.setRotation(rng.nextInt(359));
      dieImage(myArray[1],die2);
      die2.setVisibility(View.VISIBLE);
//
//      die3.setX(myArray[2]);
//      die3.setY(myArray[myArray.length-3]);
////      die3.setRotation(rng.nextInt(359));
      dieImage(myArray[2],die3);
      die3.setVisibility(View.VISIBLE);
//
//      die4.setX(myArray[3]);
//      die4.setY(myArray[myArray.length-4]);
////      die4.setRotation(rng.nextInt(359));
      dieImage(myArray[3],die4);
      die4.setVisibility(View.VISIBLE);
//
//      die5.setX(myArray[4]);
//      die5.setY(myArray[myArray.length-5]);
////      die5.setRotation(rng.nextInt(359));
      dieImage(myArray[4],die5);
      die5.setVisibility(View.VISIBLE);
//
//      die6.setX(myArray[5]);
//      die6.setY(myArray[myArray.length-6]);
////      die6.setRotation(rng.nextInt(359));
      dieImage(myArray[5],die6);
      die6.setVisibility(View.VISIBLE);
    });

    reRoll.setOnClickListener(v -> {
      reRoll.setVisibility(View.GONE);
      roll.setVisibility(View.VISIBLE);
      stay.setVisibility(View.GONE);
    });
    stay.setOnClickListener(v -> {
      stay.setVisibility(View.GONE);
      reRoll.setVisibility(View.GONE);
      roll.setVisibility(View.VISIBLE);
    });
  }

  private void dieImage(int value, ImageView die) {
    if (value == 1) {
      die.setImageResource(R.drawable.generic_1);
    }
    if (value == 2) {
      die.setImageResource(R.drawable.generic_2);
    }
    if (value == 3) {
      die.setImageResource(R.drawable.generic_3);
    }
    if (value == 4) {
      die.setImageResource(R.drawable.generic_4);
    }
    if (value == 5) {
      die.setImageResource(R.drawable.generic_5);
    }
    if (value == 6) {
      die.setImageResource(R.drawable.generic_6);
    }
  }

}
