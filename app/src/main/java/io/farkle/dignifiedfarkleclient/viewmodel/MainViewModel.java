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
import io.farkle.dignifiedfarkleclient.R;
import io.farkle.dignifiedfarkleclient.model.entity.Game;
import io.farkle.dignifiedfarkleclient.model.entity.Player;
import io.farkle.dignifiedfarkleclient.service.FarkleService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final FarkleService farkleService;
  private final MutableLiveData<List<Player>> player;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Game> game;

  private final CompositeDisposable pending;

  public MainViewModel(@NonNull Application application,
      MutableLiveData<Game> game) {
    super(application);
    this.game = game;
    farkleService = FarkleService.getInstance();
    player = new MutableLiveData<>();
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    Timer timer = new Timer();
    timer.schedule(new Poll(), 15000, 15000);
  }

  public MutableLiveData<List<Player>> getPlayer() {
    return player;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setAccount(GoogleSignInAccount account) {
    this.account.setValue(account);
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
  }

  private String getAuthorizationHeader(GoogleSignInAccount account) {
    String token = getApplication().getString(R.string.oauth_header, account.getIdToken());
    Log.d("OAuth2.0 token", token); // FIXME Remove before shipping.
    return token;
  }


  @OnLifecycleEvent(Event.ON_STOP)
  public void clearPending() {
    pending.clear();
  }

  private class Poll extends TimerTask {

    @Override
    public void run() {

    }

  }

}
