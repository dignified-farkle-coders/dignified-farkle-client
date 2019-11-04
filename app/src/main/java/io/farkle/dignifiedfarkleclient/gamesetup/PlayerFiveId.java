package io.farkle.dignifiedfarkleclient.gamesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignifiedfarkleclient.PlayGameActivity;
import io.farkle.dignifiedfarkleclient.R;

public class PlayerFiveId extends AppCompatActivity {

  private TextView textView;
  private Button next;
  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_five_id);
    next = findViewById(R.id.next_button);
    next.setText("Start");
    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openPlayGameActivity();
      }
    });
  }

  public void openPlayGameActivity() {
    Intent intent = new Intent(this, PlayGameActivity.class);
    startActivity(intent);
  }

}
