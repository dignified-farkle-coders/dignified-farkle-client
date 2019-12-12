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
import io.farkle.dignifiedfarkleclient.model.entity.Action;
import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.model.entity.GamePlayer;
import io.farkle.dignifiedfarkleclient.viewmodel.MainViewModel;
import java.util.Arrays;
import java.util.List;
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
  private ImageView die1;
  private ImageView die2;
  private ImageView die3;
  private ImageView die4;
  private ImageView die5;
  private ImageView die6;
//  MediaPlayer diceAudio;
  private TextView pointTally;
  private GamePlayer gamePlayer;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_play, container, false);

    die1 = view.findViewById(R.id.die_1);
    die1.setVisibility(View.GONE);
    die2 = view.findViewById(R.id.die_2);
    die2.setVisibility(View.GONE);
    die3 = view.findViewById(R.id.die_3);
    die3.setVisibility(View.GONE);
    die4 = view.findViewById(R.id.die_4);
    die4.setVisibility(View.GONE);
    die5 = view.findViewById(R.id.die_5);
    die5.setVisibility(View.GONE);
    die6 = view.findViewById(R.id.die_6);
    die6.setVisibility(View.GONE);

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

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
//    diceAudio = MediaPlayer.create(getContext(), R.raw.dice_roll);
    Button reRoll = view.findViewById(R.id.re_roll);
    Button stay = view.findViewById(R.id.stay);
    Button next = view.findViewById(R.id.next);
    pointTally = view.findViewById(R.id.point_tally);
    next.setVisibility(View.GONE);

    viewModel.getGame().observe(this, obj -> {
      Game game = (Game) obj;
      List<GamePlayer> gamePlayers = game.getGamePlayers();
      for (GamePlayer gamePlayer : gamePlayers) {
        int userPoints = gamePlayer.getPoints();
        System.out.println("DONE: " + userPoints);
        pointTally.setText(String.valueOf(userPoints));
      }
      myArray = game.getLastAction().getAvailableDice();
      if(game.getLastAction().getFarkleOut()) {
        pointTally.setText("Farkle!!!");
        reRoll.setVisibility(View.GONE);
        stay.setVisibility(View.GONE);
        next.setVisibility(View.VISIBLE);
      }
      System.out.println("MyArray: " + Arrays.toString(myArray));

      if (myArray.length > 0) {
        dieImage(myArray[0], die1);
        die1.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die1.setOnClickListener(one -> {
            if (!isSelected1) {
              die1.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected1 = true;
            } else {
              die1.setColorFilter(null);
              isSelected1 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      if (myArray.length > 1) {
        dieImage(myArray[1], die2);
        die2.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die2.setOnClickListener(one -> {
            if (!isSelected2) {
              die2.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected2 = true;
            } else {
              die2.setColorFilter(null);
              isSelected2 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      if (myArray.length > 2) {
        dieImage(myArray[2], die3);
        die3.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die3.setOnClickListener(one -> {
            if (!isSelected3) {
              die3.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected3 = true;
            } else {
              die3.setColorFilter(null);
              isSelected3 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      if (myArray.length > 3) {
        dieImage(myArray[3], die4);
        die4.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die4.setOnClickListener(one -> {
            if (!isSelected4) {
              die4.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected4 = true;
            } else {
              die4.setColorFilter(null);
              isSelected4 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      if (myArray.length > 4) {
        dieImage(myArray[4], die5);
        die5.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die5.setOnClickListener(one -> {
            if (!isSelected5) {
              die5.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected5 = true;
            } else {
              die5.setColorFilter(null);
              isSelected5 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      if (myArray.length > 5) {
        dieImage(myArray[5], die6);
        die6.setVisibility(View.VISIBLE);
        if(!game.getLastAction().getFarkleOut()) {
          die6.setOnClickListener(one -> {
            if (!isSelected6) {
              die6.setColorFilter(Color.argb(175, 57, 102, 255));
              isSelected6 = true;
            } else {
              die6.setColorFilter(null);
              isSelected6 = false;
            }
            System.out.println(Arrays.toString(dieArray(myArray.length)));
          });
        }
      }

      userDisplay = view.findViewById(R.id.display_name);

      try {
        userDisplay.setText(String.valueOf(game.getLastAction().getNextPlayer().getDisplayName()));
      } catch (Exception ignore) {
        userDisplay.setText("James L");
        System.out.println("user display has null value");
      }

      reRoll.setOnClickListener(v -> {
        System.out.println("myArray " + Arrays.toString(myArray));
        System.out.println("dieArray " + Arrays.toString(dieArray(myArray.length)));
        viewModel.sendFrozen(dieArray(myArray.length), false);
        myArray = game.getLastAction().getAvailableDice();
//        diceAudio.start();
      });

      stay.setOnClickListener(v -> {
        int[] returnArray = dieArray(myArray.length);
        for (int i = 0; i < returnArray.length; i++) {
          returnArray[i] = returnArray[i] * -1;
        }
        viewModel.sendFrozen(returnArray, true);
        myArray = game.getLastAction().getAvailableDice();
//        diceAudio.start();
      });

      next.setOnClickListener(v -> {
        int[] returnArray = dieArray(myArray.length);
        System.out.println("Stay Dice: " + Arrays.toString(returnArray));
        viewModel.sendFrozen(new int[]{-700}, true);
        myArray = game.getLastAction().getAvailableDice();
//        diceAudio.start();
      });
    });
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


  public int[] dieArray(int length) {
    Game game = new Game();
    int[] returnArray;
    returnArray = new int[length];

    try {
      returnArray = new int[game.getLastAction().getAvailableDice().length];
    } catch (Exception ignore) {
    }

    if (returnArray.length > 0) {
      if (isSelected1) {
        returnArray[0] = myArray[0];
      } else {
        returnArray[0] = 0;
      }
    }

    if (returnArray.length > 1) {
      if (isSelected2) {
        returnArray[1] = myArray[1];
      } else {
        returnArray[1] = 0;
      }
    }

    if (returnArray.length > 2) {
      if (isSelected3) {
        returnArray[2] = myArray[2];
      } else {
        returnArray[2] = 0;
      }
    }

    if (returnArray.length > 3) {
      if (isSelected4) {
        returnArray[3] = myArray[3];
      } else {
        returnArray[3] = 0;
      }
    }

    if (returnArray.length > 4) {
      if (isSelected5) {
        returnArray[4] = myArray[4];
      } else {
        returnArray[4] = 0;
      }
    }

    if (returnArray.length > 5) {
      if (isSelected6) {
        returnArray[5] = myArray[5];
      } else {
        returnArray[5] = 0;
      }
    }
    return returnArray;
  }
}