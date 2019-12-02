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

    Button submit = findViewById(R.id.submit);
    submit.setVisibility(View.GONE);

    Button roll = findViewById(R.id.roll);
    roll.setOnClickListener(v -> {
      int [] myArray = new int[12];
      myArray = RandomXYPositions.method();
      Log.d(TAG, "MyArray " + Arrays.toString(myArray));
      roll.setVisibility(View.GONE);
      submit.setVisibility(View.VISIBLE);

      ImageView die1 = findViewById(R.id.die_1);
      ImageView die2 = findViewById(R.id.die_2);
      ImageView die3 = findViewById(R.id.die_3);
      ImageView die4 = findViewById(R.id.die_4);
      ImageView die5 = findViewById(R.id.die_5);
      ImageView die6 = findViewById(R.id.die_6);

      die1.setX(myArray[0]);
      die1.setY(myArray[myArray.length-1]);

      die2.setX(myArray[1]);
      die2.setY(myArray[myArray.length-2]);

      die3.setX(myArray[2]);
      die3.setY(myArray[myArray.length-3]);

      die4.setX(myArray[3]);
      die4.setY(myArray[myArray.length-4]);

      die5.setX(myArray[4]);
      die5.setY(myArray[myArray.length-5]);

      die6.setX(myArray[5]);
      die6.setY(myArray[myArray.length-6]);

    });

    submit.setOnClickListener(v -> {
      submit.setVisibility(View.GONE);
      roll.setVisibility(View.VISIBLE);
    });
  }

}
