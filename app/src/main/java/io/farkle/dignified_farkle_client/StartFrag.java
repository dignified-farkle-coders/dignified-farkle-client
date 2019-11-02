package io.farkle.dignified_farkle_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StartFrag extends Fragment implements View.OnClickListener {

  private Button button;
  View view;
  Button playButton;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_start, container, false);
    button = view.findViewById(R.id.profile_button);
    button.setOnClickListener(this);
    playButton = view.findViewById(R.id.play_button);
    playButton.setOnClickListener(this);
    return view;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.play_button:
        openGameSettupActivity();
        break;
      case R.id.profile_button:
        openProfileActivity();
        break;

    }
  }

  private void openGameSettupActivity () {
    Intent intent = new Intent(getContext(), GameSetupActivity.class);
    startActivity(intent);
  }

  private void openProfileActivity () {
    Intent intent = new Intent(getContext(), ProfileActivity.class);
    startActivity(intent);
  }

  private void openPlayGameActivity () {
    Intent intent = new Intent(getContext(), PlayGameActivity.class);
    startActivity(intent);
  }

}
