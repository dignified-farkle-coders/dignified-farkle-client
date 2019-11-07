package io.farkle.dignifiedfarkleclient.controller;

import android.content.Intent;
import android.widget.Button;

import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.farkle.dignifiedfarkleclient.MarketFrag;
import io.farkle.dignifiedfarkleclient.R;
import io.farkle.dignifiedfarkleclient.StartFrag;
import io.farkle.dignifiedfarkleclient.TournamentFrag;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  public static final int ADD_NOTE_REQUEST = 1;

  private SectionsPagerAdapter mSectionsPagerAdapter;
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    mViewPager = findViewById(R.id.container);
    mViewPager.setAdapter(mSectionsPagerAdapter);
    mViewPager.setCurrentItem(1);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
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
    public void onClick(View view) {

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
