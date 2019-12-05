package io.farkle.dignifiedfarkleclient.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.gms.tasks.Task;
import io.farkle.dignifiedfarkleclient.R;
import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.service.GoogleSignInService;
import io.farkle.dignifiedfarkleclient.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

  private ProgressBar waiting;
  private MainViewModel viewModel;
  private GoogleSignInService signInService;
  private NavController navController;
  private NavHostFragment navHostFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navHostFragment = (NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment);
    navController = navHostFragment.getNavController();

    setupSignIn();
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getGame().observe(this, obj -> {
      Game game = (Game) obj;
      if (game != null) {
        switch (game.getState()) {
          case PENDING:
            navController.navigate(R.id.waitingFragment);
            break;
          case IN_PROGRESS:
            if (game.isYourTurn()) {
              navController.navigate(R.id.playFragment);
            } else {
              navController.navigate(R.id.opponentTurnFragment);
            }
            break;
          case COMPLETED:
            navController.navigate(R.id.startFragment);
            break;
          case ABANDONED:
            navController.navigate(R.id.startFragment);
            break;
        }
      } else {
        navController.navigate(R.id.startFragment);
      }
    });
//    ViewPager mViewPager = findViewById(R.id.container);
//    mViewPager.setAdapter(mSectionsPagerAdapter);
//    mViewPager.setCurrentItem(1);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  private void setupSignIn() {
    signInService = GoogleSignInService.getInstance();
//    signInService.getAccount().observe(this, (account) -> viewModel.setAccount(account));
  }

  private void refreshSignIn(Runnable runnable) {
    waiting.setVisibility(View.VISIBLE);
    signInService.refresh()
        .addOnSuccessListener((account) -> runnable.run())
        .addOnFailureListener((e) -> signOut());
  }

  private void signOut() {
    signInService.signOut()
        .addOnCompleteListener((task) -> {
          Intent intent = new Intent(this, LoginActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_settings:
        break;
      case R.id.sign_out:
        signOut();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  public void onComplete(@NonNull Task task) {

  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private Button button;
    private Button playButton;


    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      final View rootView = inflater.inflate(R.layout.fragment_start, container, false);
      TextView textView = rootView.findViewById(R.id.section_label);
      textView
          .setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
      button = rootView.findViewById(R.id.profile_button);
      button.setOnClickListener(this);
      playButton = rootView.findViewById(R.id.find_game);
      playButton.setOnClickListener(this);
      return rootView;
    }


    @Override
    public void onClick(View v) {

    }
  }


}
