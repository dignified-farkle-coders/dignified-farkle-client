package io.farkle.dignifiedfarkleclient;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.model.entity.GamePlayer;
import io.farkle.dignifiedfarkleclient.viewmodel.MainViewModel;
import java.util.Arrays;
import java.util.Random;
import org.w3c.dom.Text;

public class PlayFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String TAG = "playgameactivity";
  private Random rng = new Random();
  private boolean isSelected1;
  private boolean isSelected2;
  private boolean isSelected3;
  private boolean isSelected4;
  private boolean isSelected5;
  private boolean isSelected6;
  private int[] myArray = new int[6];
  private TextView userDisplay;
  private MainViewModel viewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_play, container, false);
    ImageView die1 = view.findViewById(R.id.die_1);
    die1.setVisibility(View.GONE);
    ImageView die2 = view.findViewById(R.id.die_2);
    die2.setVisibility(View.GONE);
    ImageView die3 = view.findViewById(R.id.die_3);
    die3.setVisibility(View.GONE);
    ImageView die4 = view.findViewById(R.id.die_4);
    die4.setVisibility(View.GONE);
    ImageView die5 = view.findViewById(R.id.die_5);
    die5.setVisibility(View.GONE);
    ImageView die6 = view.findViewById(R.id.die_6);
    die6.setVisibility(View.GONE);
    Button reRoll = view.findViewById(R.id.re_roll);
    reRoll.setVisibility(View.GONE);
    Button stay = view.findViewById(R.id.stay);
    stay.setVisibility(View.GONE);
    Button roll = view.findViewById(R.id.roll);
    roll.setOnClickListener(v -> {
      MediaPlayer diceRoll = MediaPlayer.create(getActivity(), R.raw.dice_roll);
      diceRoll.start();
      isSelected1 = false;
      die1.setColorFilter(null);
      isSelected2 = false;
      die2.setColorFilter(null);
      isSelected3 = false;
      die3.setColorFilter(null);
      isSelected4 = false;
      die4.setColorFilter(null);
      isSelected5 = false;
      die5.setColorFilter(null);
      isSelected6 = false;
      die6.setColorFilter(null);

//      for (int i = 0; i < myArray.length; i++) {
//        myArray[i] = rng.nextInt(5) + 1;
//      }


      System.out.println("MyArray: " + Arrays.toString(myArray));
      roll.setVisibility(View.GONE);
      reRoll.setVisibility(View.VISIBLE);
      stay.setVisibility(View.VISIBLE);
      dieImage(myArray[0], die1);
      die1.setVisibility(View.VISIBLE);
      die1.setOnClickListener(one -> {
        if (!isSelected1) {
          die1.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected1 = true;
        } else {
          die1.setColorFilter(null);
          isSelected1 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
      dieImage(myArray[1], die2);
      die2.setVisibility(View.VISIBLE);
      die2.setOnClickListener(one -> {
        if (!isSelected2) {
          die2.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected2 = true;
        } else {
          die2.setColorFilter(null);
          isSelected2 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
      dieImage(myArray[2], die3);
      die3.setVisibility(View.VISIBLE);
      die3.setOnClickListener(one -> {
        if (!isSelected3) {
          die3.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected3 = true;
        } else {
          die3.setColorFilter(null);
          isSelected3 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
      dieImage(myArray[3], die4);
      die4.setVisibility(View.VISIBLE);
      die4.setOnClickListener(one -> {
        if (!isSelected4) {
          die4.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected4 = true;
        } else {
          die4.setColorFilter(null);
          isSelected4 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
      dieImage(myArray[4], die5);
      die5.setVisibility(View.VISIBLE);
      die5.setOnClickListener(one -> {
        if (!isSelected5) {
          die5.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected5 = true;
        } else {
          die5.setColorFilter(null);
          isSelected5 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
      dieImage(myArray[5], die6);
      die6.setVisibility(View.VISIBLE);
      die6.setOnClickListener(one -> {
        if (!isSelected6) {
          die6.setColorFilter(Color.argb(175, 57, 102, 255));
          isSelected6 = true;
        } else {
          die6.setColorFilter(null);
          isSelected6 = false;
        }
        System.out.println(Arrays.toString(dieArray()));
      });
    });
    reRoll.setOnClickListener(v -> {
      clearScreen(die1, die2, die3, die4, die5, die6, reRoll, stay, roll, View.VISIBLE, View.GONE);
    });
    stay.setOnClickListener(v -> {
      clearScreen(die1, die2, die3, die4, die5, die6, stay, roll, reRoll, View.GONE, View.VISIBLE);
      int[] returnArray = dieArray();
      for (int i = 0; i < dieArray().length; i++) {
        returnArray[i] = returnArray[i] * -1;
      }

      System.out.println(Arrays.toString(returnArray));
    });
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    viewModel.getGame().observe(this, obj -> {
      Game game = (Game) obj;
//      GamePlayer gamePlayer = game.getGamePlayers().get();
      myArray = game.getLastAction().getAvailableDice();
      userDisplay = view.findViewById(R.id.display_name);
      userDisplay.setText(String.valueOf(game.getLastAction().getNextPlayer().getDisplayName()));
    });

  }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an
   * interaction in this fragment to be communicated to the activity and potentially other fragments
   * contained in that activity.
   * <p>
   * See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
  private void dieImage(int value, ImageView die) {
    if (value == 1) {
      die.setImageResource(R.drawable.generic_1);
    }
    if (value == 2) {
      die.setImageResource(R.drawable.generic_2);
    }
    if (value == 3) {
      die.setImageResource(R.drawable.generic_3);
    }
    if (value == 4) {
      die.setImageResource(R.drawable.generic_4);
    }
    if (value == 5) {
      die.setImageResource(R.drawable.generic_5);
    }
    if (value == 6) {
      die.setImageResource(R.drawable.generic_6);
    }
  }
  private void clearScreen(ImageView die1, ImageView die2, ImageView die3, ImageView die4,
      ImageView die5, ImageView die6, Button reRoll, Button stay, Button roll, int visible,
      int gone) {
    reRoll.setVisibility(View.GONE);
    roll.setVisibility(visible);
    stay.setVisibility(gone);
    die1.setVisibility(View.GONE);
    die2.setVisibility(View.GONE);
    die3.setVisibility(View.GONE);
    die4.setVisibility(View.GONE);
    die5.setVisibility(View.GONE);
    die6.setVisibility(View.GONE);
  }
  public int[] dieArray() {
    int[] returnArray = new int[6];
    if (isSelected1) {
      returnArray[0] = myArray[0];
    } else {
      returnArray[0] = 0;
    }
    if (isSelected2) {
      returnArray[1] = myArray[1];
    } else {
      returnArray[1] = 0;
    }
    if (isSelected3) {
      returnArray[2] = myArray[2];
    } else {
      returnArray[2] = 0;
    }
    if (isSelected4) {
      returnArray[3] = myArray[3];
    } else {
      returnArray[3] = 0;
    }
    if (isSelected5) {
      returnArray[4] = myArray[4];
    } else {
      returnArray[4] = 0;
    }
    if (isSelected6) {
      returnArray[5] = myArray[5];
    } else {
      returnArray[5] = 0;
    }
    return returnArray;
  }
}