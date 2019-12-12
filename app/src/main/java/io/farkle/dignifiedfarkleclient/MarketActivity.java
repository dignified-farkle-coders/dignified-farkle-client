package io.farkle.dignifiedfarkleclient;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignifiedfarkleclient.model.entity.Player;

public class MarketActivity extends AppCompatActivity {

  private ImageView diePresentation1;
  private ImageView diePresentation2;
  private ImageView diePresentation3;
  private ImageView diePresentation4;
  private TextView victoryPoints;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_market);
    super.onCreate(savedInstanceState);

    victoryPoints = findViewById(R.id.victory_points);
    diePresentation1 = findViewById(R.id.die_presentation_1);
    diePresentation1.setColorFilter(Color.argb(100, 255, 0, 0));
    diePresentation2 = findViewById(R.id.die_presentation_2);
    diePresentation2.setColorFilter(Color.argb(100, 0, 255, 0));
    diePresentation3 = findViewById(R.id.die_presentation_3);
    diePresentation3.setColorFilter(Color.argb(100, 0, 0, 255));
    diePresentation4 = findViewById(R.id.die_presentation_4);

    victoryPoints.setText(String.valueOf(StartFragment.playerPoints));
  }
}

