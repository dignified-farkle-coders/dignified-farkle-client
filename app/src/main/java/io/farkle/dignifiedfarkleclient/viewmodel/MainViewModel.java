package io.farkle.dignifiedfarkleclient.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import io.farkle.dignifiedfarkleclient.BuildConfig;
import io.farkle.dignifiedfarkleclient.model.GamePreferences;
import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.model.entity.Player;
import io.farkle.dignifiedfarkleclient.service.FarkleService;
import io.farkle.dignifiedfarkleclient.service.GoogleSignInService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel<TAG> extends AndroidViewModel implements LifecycleObserver {

  private static final Object TAG = "MainView";
  private final FarkleService farkleService;
  private final MutableLiveData<List<Player>> player;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Game> game;

  private final CompositeDisposable pending;

  public MainViewModel(@NonNull Application application) {
    super(application);
    farkleService = FarkleService.getInstance();
    player = new MutableLiveData<>();
    game = new MutableLiveData<>(null);
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    Timer timer = new Timer();
//    timer.schedule(new Poll(), 15000, 15000);
    refreshAccount();
  }

  public LiveData<List<Player>> getPlayer() {
    return player;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void startRound() {
      refreshPlayer();
  }


  public void refreshPlayer() {
    GoogleSignInAccount account = this.account.getValue();
    if (account != null) {
      refreshPlayer(account);
    } else {
      player.setValue(Collections.EMPTY_LIST);
    }
  }

  private void refreshPlayer(GoogleSignInAccount account) {
    String token = getAuthorizationHeader(account);
    pending.add(
        farkleService.getPlayerInfo(token)
            .subscribeOn(Schedulers.io())
            .subscribe(this.player::postValue, this.throwable::postValue)
    );
    Log.d((String) TAG, "PlayerInfo: " + pending.size());
  }

  public void joinGame() {
    String token = getAuthorizationHeader(account.getValue());
    FarkleService.getInstance().post(token, new GamePreferences(1))
        .subscribeOn(Schedulers.io())
        .subscribe(this.game::postValue);//, this.throwable::postValue);
  }

  public String getAuthorizationHeader(GoogleSignInAccount account) {
    String token = String.format(BuildConfig.AUTHORIZATION_FORMAT, account.getIdToken());
    return token;
  }

  private void refreshAccount() {
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          Log.d("Authorization",
              String.format(BuildConfig.AUTHORIZATION_FORMAT, account.getIdToken()));
          this.account.postValue(account);
        })
        .addOnFailureListener(this.throwable::postValue);
  }

  @OnLifecycleEvent(Event.ON_STOP)
  public void clearPending() {
    pending.clear();
  }

  public LiveData<Game> getGame() {
    return game;
  }

  private class Poll extends TimerTask {

    @Override
    public void run() {

    }

  }

}
