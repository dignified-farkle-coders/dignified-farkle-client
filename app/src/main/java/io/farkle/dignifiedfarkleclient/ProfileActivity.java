package io.farkle.dignifiedfarkleclient;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

  private int points = 5;
  private double winRatio = 0.363;
  private int highestPoint = 3450;

  private TextView pointsTextView;
  private TextView winRatioTextView;
  private TextView highestPointTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_profile);
    super.onCreate(savedInstanceState);

    pointsTextView=findViewById(R.id.display_points);
    pointsTextView.setText(getString(R.string.points, points));

    winRatioTextView = findViewById(R.id.win_lose_ratio);
    winRatioTextView.setText(getString(R.string.win_ratio, winRatio));

    highestPointTextView = findViewById(R.id.largest_point);
    highestPointTextView.setText(getString(R.string.largest_single_point, highestPoint));
  }
}
