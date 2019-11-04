package io.farkle.dignifiedfarkleclient.gamesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignifiedfarkleclient.PlayGameActivity;
import io.farkle.dignifiedfarkleclient.R;

public class PlayerFourId extends AppCompatActivity {

  private TextView textView;
  private Button next;
  private View view;
  private int remainingPlayers;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_four_id);
    remainingPlayers = getIntent().getIntExtra("remaining_players", 1) - 1;
    next = findViewById(R.id.next_button);
    if (remainingPlayers > 0) {
      next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          openPlayerFiveId(remainingPlayers);
        }
      });
    } else {
      next.setText("Start");
      next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          openPlayGameActivity();
        }
      });
    }

  }

  public void openPlayerFiveId(int remainingPlayers) {
    Intent intent = new Intent(this, PlayerFiveId.class);
    intent.putExtra("remaining_players", remainingPlayers);
    startActivity(intent);
  }

  public void openPlayGameActivity() {
    Intent intent = new Intent(this, PlayGameActivity.class);
    startActivity(intent);
  }

}
