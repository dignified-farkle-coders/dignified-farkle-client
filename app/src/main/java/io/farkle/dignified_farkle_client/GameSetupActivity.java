package io.farkle.dignified_farkle_client;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameSetupActivity extends AppCompatActivity {
  Button button1, button2, button3, button4, button5, startGame;
  String hello;
  View view;
  TextView textView;

  private void openPlayGameActivity () {
    Intent intent = new Intent(this, PlayGameActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_setup);
    textView = findViewById(R.id.playerId);
    startGame = findViewById(R.id.start_game_button);
    button1 = findViewById(R.id.button1);
    button2 = findViewById(R.id.button2);
    button3 = findViewById(R.id.button3);
    button4 = findViewById(R.id.button4);
    button5 = findViewById(R.id.button5);

    startGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openPlayGameActivity();
      }
    });

    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textView.setText("Player Names:");
      }
    });

    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textView.setText("Player Names:");
      }
    });

    button3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textView.setText("Player Names:");
      }
    });

    button4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textView.setText("Player Names:");
        }
    });

    button5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textView.setText("Player Names:");
      }
    });
  }

  public void onRadioButtonClicked(View view) {
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();
    System.out.println("TEST#############################################################");
    // Check which radio button was clicked
    switch(view.getId()) {
      case R.id.button1:
        if (checked)
          button1.setBackgroundColor(getResources().getColor(R.color.buttonPress));
          break;
      case R.id.button2:
        if (checked)
          button2.setBackgroundColor(getResources().getColor(R.color.buttonPress));
          break;
      case R.id.button3:
        if (checked)
          button3.setBackgroundColor(getResources().getColor(R.color.buttonPress));
          break;
      case R.id.button4:
        if (checked)
          button4.setBackgroundColor(getResources().getColor(R.color.buttonPress));
          break;
      case R.id.button5:
        if (checked)
          button5.setBackgroundColor(getResources().getColor(R.color.buttonPress));
          break;

    }
  }

}
