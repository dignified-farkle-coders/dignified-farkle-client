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
      roll.setVisibility(View.GONE);
      submit.setVisibility(View.VISIBLE);

      ConstraintSet set = new ConstraintSet();
      ImageView die1 = findViewById(R.id.die_1);
      ImageView die2 = findViewById(R.id.die_2);
      ImageView die3 = findViewById(R.id.die_3);
      ImageView die4 = findViewById(R.id.die_4);
      ImageView die5 = findViewById(R.id.die_5);
      ImageView die6 = findViewById(R.id.die_6);
      ConstraintLayout farkle_time = findViewById(R.id.farkle_time);
      set.clone(farkle_time);

      set.setVerticalBias(R.id.die_1, (float) Math.random());
      die1.setVisibility(View.VISIBLE);

      set.setVerticalBias(R.id.die_2, (float) Math.random());
      die2.setVisibility(View.VISIBLE);

      set.setVerticalBias(R.id.die_3, (float) Math.random());
      die3.setVisibility(View.VISIBLE);

      set.setVerticalBias(R.id.die_4, (float) Math.random());
      die4.setVisibility(View.VISIBLE);

      set.setVerticalBias(R.id.die_5, (float) Math.random());
      die5.setVisibility(View.VISIBLE);

      set.setVerticalBias(R.id.die_6, (float) Math.random());
      die6.setVisibility(View.VISIBLE);

      set.applyTo(farkle_time);

    });

    submit.setOnClickListener(v -> {
      submit.setVisibility(View.GONE);
      roll.setVisibility(View.VISIBLE);
    });
  }

}
