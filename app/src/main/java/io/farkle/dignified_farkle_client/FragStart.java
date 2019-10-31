package io.farkle.dignified_farkle_client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragStart extends Fragment implements View.OnClickListener {

  private Button button;
  View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_start, container, false);
    button = (Button) view.findViewById(R.id.profile_button);
    button.setOnClickListener(this);
    return view;
  }

  @Override
  public void onClick(View view) {
    //do what you want to do when button is clicked
//    openProfileActivity();
  }

//  private void openProfileActivity() {
//    Intent intent = new Intent(this, ProfileActivity.class);
//    startActivity(intent);
//  }


}
