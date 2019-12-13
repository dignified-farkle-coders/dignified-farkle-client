package io.farkle.dignifiedfarkleclient;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
  private Button buyButton;
  ImageView upArrow;
  ImageView downArrow;
  private int basket = 0;
  private int dieCount = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_market);
    super.onCreate(savedInstanceState);

    upArrow = findViewById(R.id.scroll_up);
    downArrow = findViewById(R.id.scroll_down);
    downArrow.setVisibility(View.INVISIBLE);
    buyButton = findViewById(R.id.buy_button);
    victoryPoints = findViewById(R.id.victory_points);
    diePresentation1 = findViewById(R.id.die_presentation_1);
    diePresentation1.setColorFilter(Color.argb(100, 255, 0, 0));
    diePresentation2 = findViewById(R.id.die_presentation_2);
    diePresentation2.setColorFilter(Color.argb(100, 0, 255, 0));
    diePresentation3 = findViewById(R.id.die_presentation_3);
    diePresentation3.setColorFilter(Color.argb(100, 0, 0, 255));
    diePresentation4 = findViewById(R.id.die_presentation_4);

    victoryPoints.setText(String.valueOf(StartFragment.playerPoints));

    diePresentation1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
      }
    });

    diePresentation2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
      }
    });

    diePresentation3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
      }
    });

    diePresentation4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
      }
    });

    buyButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        System.out.println("POINTS: " + StartFragment.playerPoints);
        if(StartFragment.playerPoints >= basket) {
          System.out.println(StartFragment.playerPoints - basket);
          StartFragment.player.setVictoryPoints(StartFragment.playerPoints - basket);
          victoryPoints.setText(String.valueOf(StartFragment.player.getVictoryPoints()));
        }
      }
    });

      upArrow.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(dieCount < 6) {
            System.out.println(dieCount);
            downArrow.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.VISIBLE);
            dieCount += 1;
          } else {
            upArrow.setVisibility(View.INVISIBLE);
          }
        }
      });

      downArrow.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(dieCount > 1) {
            System.out.println(dieCount);
            downArrow.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.VISIBLE);
            dieCount -= 1;
          } else {
            upArrow.setVisibility(View.INVISIBLE);
          }
        }
      });


  }
}

