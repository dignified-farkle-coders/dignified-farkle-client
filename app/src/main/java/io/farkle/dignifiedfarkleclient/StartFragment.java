package io.farkle.dignifiedfarkleclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import io.farkle.dignifiedfarkleclient.model.entity.Player;
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
  private TextView claim;
  private TextView reward;
  private TextView bonus;
  public static TextView victoryPoints;
  private ImageView goldenDice;
  private Button marketButton;
  private GoogleSignInService googleSignInService = GoogleSignInService.getInstance();
  public static int playerPoints = 0;
  public static Player player = new Player();



  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_start, container, false);
    button = view.findViewById(R.id.profile_button);
    button.setOnClickListener(this);
    playButton = view.findViewById(R.id.find_game);
    playButton.setOnClickListener(this);
    marketButton = view.findViewById(R.id.market);
    marketButton.setOnClickListener(this);
    spinner = view.findViewById(R.id.spinner);
    claim = view.findViewById(R.id.claim);
    reward = view.findViewById(R.id.reward);
    goldenDice = view.findViewById(R.id.market_dice);
    bonus = view.findViewById(R.id.bonus);
    bonus.setVisibility(View.GONE);
    victoryPoints = view.findViewById(R.id.victory_points);


    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

    try {
      victoryPoints.setText(player.getVictoryPoints());
    } catch (Exception e){
      victoryPoints.setText(String.valueOf(0));
    }

    goldenDice.setOnClickListener(v -> {
      claim.setVisibility(View.INVISIBLE);
      reward.setVisibility(View.INVISIBLE);
      bonus.setVisibility(View.VISIBLE);

      AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
      AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
      bonus.startAnimation(fadeOut);
      fadeOut.setDuration(1200);
      fadeOut.setFillAfter(true);
      fadeOut.setStartOffset(800 + fadeIn.getStartOffset());
      System.out.println("Victory Points: " + player.getVictoryPoints());
      if (goldenDice.getVisibility() == View.VISIBLE) {
        player.setVictoryPoints(1000);
      }
      goldenDice.setVisibility(View.INVISIBLE);

      playerPoints = player.getVictoryPoints();

      victoryPoints.setText(String.valueOf(playerPoints));


    });
  }


  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.find_game:
        viewModel.joinGame();
        break;
      case R.id.market:
        openMarketActivity();
        break;
      case R.id.profile_button:
        openProfileActivity();
        break;

    }
  }

  private void openProfileActivity() {
    Intent intent = new Intent(getContext(), ProfileActivity.class);
    startActivity(intent);
  }

  private void openMarketActivity() {
    Intent intent = new Intent(getContext(), MarketActivity.class);
    startActivity(intent);
  }


}
