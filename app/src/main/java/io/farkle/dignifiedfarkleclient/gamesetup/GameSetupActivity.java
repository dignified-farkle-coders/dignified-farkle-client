package io.farkle.dignifiedfarkleclient.gamesetup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.farkle.dignifiedfarkleclient.R;

public class GameSetupActivity extends AppCompatActivity {

  private static int currentRadioId;
  private RadioGroup radioGroup;
  private RadioButton radioButton;
  private TextView textView;
  private Button next;
  private View view;
  int radioId;

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
        int numPlayers = 0;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          if (((RadioButton) radioGroup.getChildAt(i)).isChecked()) {
            numPlayers = i + 1;
            break;
          }
        }
        openPlayerOneId(numPlayers);
      }
    });
  }

  public void checkButton(View v) {
    radioId = radioGroup.getCheckedRadioButtonId();
    radioButton = findViewById(radioId);
    currentRadioId = radioId - 2131230756;
    next.setVisibility(View.VISIBLE);
    System.out.println(radioId);
    System.out.println(currentRadioId);
  }

  public static int setPlayers() {
    return currentRadioId;
  }

  public void openPlayerOneId(int remainingPlayers) {
    Intent intent = new Intent(this, PlayerOneId.class);
    intent.putExtra("remaining_players", remainingPlayers);
    startActivity(intent);
  }

}
