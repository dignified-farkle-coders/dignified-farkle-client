package io.farkle.dignified_farkle_client.game_setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignified_farkle_client.PlayGameActivity;
import io.farkle.dignified_farkle_client.R;

public class PlayerTwoId extends AppCompatActivity {

  private TextView textView;
  private Button next;
  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_two_id);
    next = findViewById(R.id.next_button);
    if (GameSetupActivity.setPlayers() - 2 > 0) {
      System.out.println(GameSetupActivity.setPlayers());
      next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          openPlayerThreeId();
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

  public void openPlayerThreeId() {
    Intent intent = new Intent(this, PlayerThreeId.class);
    startActivity(intent);
  }

  public void openPlayGameActivity() {
    Intent intent = new Intent(this, PlayGameActivity.class);
    startActivity(intent);
  }

}
