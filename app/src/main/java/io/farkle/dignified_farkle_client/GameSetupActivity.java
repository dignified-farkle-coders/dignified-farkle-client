package io.farkle.dignified_farkle_client;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameSetupActivity extends AppCompatActivity {
  Button button1, button2, button3, button4, button5;
  String hello;
  View view;
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_setup);

    button1 = findViewById(R.id.button1);
    textView = findViewById(R.id.player1Id);

    button2 = findViewById(R.id.button2);
    textView = findViewById(R.id.player2Id);

    button3 = findViewById(R.id.button3);
    textView = findViewById(R.id.player3Id);

    button4 = findViewById(R.id.button4);
    textView = findViewById(R.id.player4Id);

    button5 = findViewById(R.id.button5);
    textView = findViewById(R.id.player5Id);

    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = getIntent();
        startActivity(intent);
        textView.setText("Player Names:");
        button1.setBackgroundColor(getResources().getColor(R.color.buttonPress));
        System.out.println("Button1");
      }
    });

    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = getIntent();
        startActivity(intent);
        textView.setText("Player Names:");
        button2.setBackgroundColor(getResources().getColor(R.color.buttonPress));
        System.out.println("Button2");

      }
    });

    button3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = getIntent();
        startActivity(intent);
        textView.setText("Player Names:");
        button3.setBackgroundColor(getResources().getColor(R.color.buttonPress));
        System.out.println("Button3");

      }
    });

    button4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = getIntent();
        startActivity(intent);
        textView.setText("Player Names:");
        button4.setBackgroundColor(getResources().getColor(R.color.buttonPress));
        System.out.println("Button4");
      }
    });

    button5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = getIntent();
        startActivity(intent);
        textView.setText("Player Names:");
        button5.setBackgroundColor(getResources().getColor(R.color.buttonPress));
        System.out.println("Button5");

      }
    });
  }

  public void resetButtons() {
    setContentView(R.layout.activity_game_setup);

    button1 = findViewById(R.id.button1);
    textView = findViewById(R.id.player1Id);

    button2 = findViewById(R.id.button2);
    textView = findViewById(R.id.player2Id);

    button3 = findViewById(R.id.button3);
    textView = findViewById(R.id.player3Id);

    button4 = findViewById(R.id.button4);
    textView = findViewById(R.id.player4Id);

    button5 = findViewById(R.id.button5);
    textView = findViewById(R.id.player5Id);
//    button1.setBackgroundColor(getResources().getColor(R.color.buttonPress));
//    button2.setBackgroundColor(getResources().getColor(R.color.buttonPress));
//    button3.setBackgroundColor(getResources().getColor(R.color.buttonPress));
//    button4.setBackgroundColor(getResources().getColor(R.color.buttonPress));
//    button5.setBackgroundColor(getResources().getColor(R.color.buttonPress));

  }

}
