package io.farkle.dignifiedfarkleclient.controller;

import android.content.Intent;
import android.widget.Button;

import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.farkle.dignifiedfarkleclient.MarketFrag;
import io.farkle.dignifiedfarkleclient.R;
import io.farkle.dignifiedfarkleclient.StartFrag;
import io.farkle.dignifiedfarkleclient.TournamentFrag;
import io.farkle.dignifiedfarkleclient.model.Points;
import io.farkle.dignifiedfarkleclient.service.GoogleSignInService;
import io.farkle.dignifiedfarkleclient.view.FarkleAdapter.OnClickListener;
import io.farkle.dignifiedfarkleclient.view.FarkleAdapter.OnContextListener;
import io.farkle.dignifiedfarkleclient.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
    implements OnClickListener, OnContextListener, OnCompleteListener {

  private ProgressBar waiting;
  private MainViewModel viewModel;
  private GoogleSignInService signInService;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SectionsPagerAdapter mSectionsPagerAdapter =
        new SectionsPagerAdapter(getSupportFragmentManager());


    setupSignIn();
    ViewPager mViewPager = findViewById(R.id.container);
    mViewPager.setAdapter(mSectionsPagerAdapter);
    mViewPager.setCurrentItem(1);
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

  @Override
  public void onComplete(@NonNull Task task) {

  }

  @Override
  public void onClick(View view, int position, Points passphrase) {

  }

  @Override
  public void onLongPress(Menu menu, int position, Points passphrase) {

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
      playButton = rootView.findViewById(R.id.play_button);
      playButton.setOnClickListener(this);
      return rootView;
    }


    @Override
    public void onClick(View v) {

    }
  }

  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      Fragment fragment = null;
      switch (position) {
        case 0:
          fragment = new TournamentFrag();
          break;
        case 1:
          fragment = new StartFrag();
          break;
        case 2:
          fragment = new MarketFrag();
          break;
      }
      return fragment;
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }
  }

}
