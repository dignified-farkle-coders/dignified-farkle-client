package io.farkle.dignifiedfarkleclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MarketFrag extends Fragment implements View.OnClickListener {
  View view;
  private int points = 69000;
  private TextView pointsTextView;
  private Button button;
  private TextView textView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_market, container, false);
    textView = (TextView) view.findViewById(R.id.points);
    textView.setText(getString(R.string.points, points));
    button = view.findViewById(R.id.market_item_one);
    button.setOnClickListener(this);

    return view;
  }
  @Override
  public void onClick(View view) {
    points -= 200;
    textView.setText(getString(R.string.points, points));

  }
}

