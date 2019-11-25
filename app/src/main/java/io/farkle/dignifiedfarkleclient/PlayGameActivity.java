package io.farkle.dignifiedfarkleclient;

import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

  public class PlayGameActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_play_game);
    super.onCreate(savedInstanceState);

    ImageView die1 = findViewById(R.id.die_1);
    ImageView die2 = findViewById(R.id.die_2);
    ImageView die3 = findViewById(R.id.die_3);
    ImageView die4 = findViewById(R.id.die_4);
    ImageView die5 = findViewById(R.id.die_5);
    ImageView die6 = findViewById(R.id.die_6);

    Button submit = findViewById(R.id.submit);
    submit.setVisibility(View.GONE);

    Button roll = findViewById(R.id.roll);
    roll.setOnClickListener(v -> {
      roll.setVisibility(View.GONE);
      submit.setVisibility(View.VISIBLE);
    });

    submit.setOnClickListener(v -> {
      submit.setVisibility(View.GONE);
      roll.setVisibility(View.VISIBLE);
    });
  }

}
