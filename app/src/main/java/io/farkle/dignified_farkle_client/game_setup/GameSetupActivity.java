package io.farkle.dignified_farkle_client.game_setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignified_farkle_client.R;

public class GameSetupActivity extends AppCompatActivity {

  private static int currentRadioId;
  private RadioGroup radioGroup;
  private RadioButton radioButton;
  private TextView textView;
  private Button next;
  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_setup);
    radioGroup = findViewById(R.id.radioGroup);
    next = findViewById(R.id.next_button);
    next.setVisibility(View.INVISIBLE);
    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openPlayerOneId();
      }
    });
  }

  public void checkButton(View v) {
    int radioId = radioGroup.getCheckedRadioButtonId();
    radioButton = findViewById(radioId);
    currentRadioId = radioId - 2131230756;
    next.setVisibility(View.VISIBLE);
  }

  public static int setPlayers() {
    return currentRadioId;
  }

  public void openPlayerOneId() {
    Intent intent = new Intent(this, PlayerOneId.class);
    startActivity(intent);
  }

}
