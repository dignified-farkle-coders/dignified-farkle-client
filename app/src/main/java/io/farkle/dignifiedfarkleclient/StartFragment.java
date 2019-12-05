package io.farkle.dignifiedfarkleclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.snackbar.Snackbar;
import io.farkle.dignifiedfarkleclient.service.FarkleService;
import io.farkle.dignifiedfarkleclient.service.GoogleSignInService;
import io.farkle.dignifiedfarkleclient.viewmodel.MainViewModel;

public class StartFragment extends Fragment implements View.OnClickListener {

  private Button button;
  private View view;
  private Button playButton;
  private FarkleService farkleService;
  private MainViewModel viewModel;
  private Spinner spinner;
  private GoogleSignInService googleSignInService = GoogleSignInService.getInstance();


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_start, container, false);
    button = view.findViewById(R.id.profile_button);
    button.setOnClickListener(this);
    playButton = view.findViewById(R.id.find_game);
    playButton.setOnClickListener(this);
    spinner = view.findViewById(R.id.spinner);
//    spinner.setItems("Lonely Larry", "One Short of a Mexican Stand Off", "Mexican Stand Off", " Larrys United");
//
//    spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//      @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//        Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//      }
//    });

//    mainViewModel.getAuthorizationHeader(googleSignInService.getAccount());
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    viewModel.getGame().observe(this, (game) -> {
      Log.d(getClass().getSimpleName(), "Game Created");
    });
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.find_game:
        openPlayGameActivity();
        viewModel.joinGame();
        break;
      case R.id.profile_button:
        openProfileActivity();
        break;

    }
  }

  private void openProfileActivity () {
    Intent intent = new Intent(getContext(), ProfileActivity.class);
    startActivity(intent);
  }

  private void openPlayGameActivity () {
//    PlayFragment nextFrag= new PlayFragment();
//    getFragmentManager().beginTransaction()
//        .replace(R.id., nextFrag, "findThisFragment")
//        .addToBackStack(null)
//        .commit();
  }

}
