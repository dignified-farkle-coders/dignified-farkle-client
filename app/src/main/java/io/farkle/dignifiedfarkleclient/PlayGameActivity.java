package io.farkle.dignifiedfarkleclient;

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

  private static final String TAG = "playgameactivity" ;
  Random rng = new Random();

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
      int [] myArray = new int[12];
      myArray = RandomXYPositions.method();
      roll.setVisibility(View.GONE);
      reRoll.setVisibility(View.VISIBLE);

//      die1.setX(myArray[0]);
//      die1.setY(myArray[myArray.length-1]);
////      die1.setRotation(rng.nextInt(359));
        die1.setVisibility(View.VISIBLE);
//
//      die2.setX(myArray[1]);
//      die2.setY(myArray[myArray.length-2]);
////      die2.setRotation(rng.nextInt(359));
        die2.setVisibility(View.VISIBLE);
//
//      die3.setX(myArray[2]);
//      die3.setY(myArray[myArray.length-3]);
////      die3.setRotation(rng.nextInt(359));
        die3.setVisibility(View.VISIBLE);
//
//      die4.setX(myArray[3]);
//      die4.setY(myArray[myArray.length-4]);
////      die4.setRotation(rng.nextInt(359));
        die4.setVisibility(View.VISIBLE);
//
//      die5.setX(myArray[4]);
//      die5.setY(myArray[myArray.length-5]);
////      die5.setRotation(rng.nextInt(359));
        die5.setVisibility(View.VISIBLE);
//
//      die6.setX(myArray[5]);
//      die6.setY(myArray[myArray.length-6]);
////      die6.setRotation(rng.nextInt(359));
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

}
