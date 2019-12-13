package io.farkle.dignifiedfarkleclient;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
  ImageView userDie;
  private int basket = 0;
  private int dieCount = 1;
  private TextView dieId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_market);
    super.onCreate(savedInstanceState);
    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
    Editor editor = pref.edit();

    dieId = findViewById(R.id.die_id);
    dieId.setText(Html.fromHtml("&#35; " + 1));
    userDie = findViewById(R.id.die_1);
    userDie.setColorFilter(Color.parseColor(pref.getString(String.valueOf(1), "#00FFFFFF"))); // getting String);
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

    victoryPoints.setText(String.valueOf(StartFragment.player.getVictoryPoints()));

    diePresentation1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
        userDie.setColorFilter(Color.argb(100, 255, 0, 0));
        editor.putString(String.valueOf(dieCount), "#64FF0000"); // Storing string
      }
    });

    diePresentation2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
        userDie.setColorFilter(Color.argb(100, 0, 255, 0));
        editor.putString(String.valueOf(dieCount), "#6400FF00"); // Storing string

      }
    });

    diePresentation3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
        userDie.setColorFilter(Color.argb(100, 0, 0, 255));
        editor.putString(String.valueOf(dieCount), "#640000FF"); // Storing string

      }
    });

    diePresentation4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        basket = 200;
        userDie.setColorFilter(Color.argb(0, 255, 255, 255));
        editor.putString(String.valueOf(dieCount), "#00000000"); // Storing string

      }
    });

    buyButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        System.out.println("POINTS: " + StartFragment.playerPoints);
        if(StartFragment.player.getVictoryPoints() >= basket) {
          StartFragment.player.setVictoryPoints(StartFragment.player.getVictoryPoints() - basket);
          victoryPoints.setText(String.valueOf(StartFragment.player.getVictoryPoints()));
          StartFragment.victoryPoints.setText(String.valueOf(StartFragment.player.getVictoryPoints()));
          editor.commit(); // commit changes
        } else {
          Toast.makeText(getApplicationContext(),"Not Enough Funds",Toast.LENGTH_SHORT).show();          }
      }
    });

      upArrow.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(dieCount != 6) {
            dieCount += 1;
          }
          userDie.setColorFilter(Color.parseColor(pref.getString(String.valueOf(dieCount), "#00FFFFFF"))); // getting String);
          dieId.setText(Html.fromHtml("&#35; " + dieCount));
          if(dieCount < 6) {
            downArrow.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.VISIBLE);
          } else {
            upArrow.setVisibility(View.INVISIBLE);
          }
        }
      });

      downArrow.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(dieCount != 1) {
            dieCount -= 1;
          }
          userDie.setColorFilter(Color.parseColor(pref.getString(String.valueOf(dieCount), "#00FFFFFF"))); // getting String);
          dieId.setText(Html.fromHtml("&#35; " + dieCount));
          if(dieCount > 1) {
            downArrow.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.VISIBLE);
          } else {
            downArrow.setVisibility(View.INVISIBLE);
          }
        }
      });


  }
}

