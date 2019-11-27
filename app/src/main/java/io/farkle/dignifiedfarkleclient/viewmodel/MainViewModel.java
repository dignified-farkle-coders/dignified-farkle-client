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
import io.farkle.dignifiedfarkleclient.model.Player;
import io.farkle.dignifiedfarkleclient.model.Points;
import io.farkle.dignifiedfarkleclient.service.FarkleService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;

/**
 * Supplier of {@link LiveData} intended to be consumed by an instance of {@link
 * io.farkle.dignifiedfarkleclient.controller.MainActivity} (and any hosted fragments within).
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final FarkleService farkleService;
  private final MutableLiveData<List<Player>> player;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * Initializes the {@link LiveData} and {@link CompositeDisposable} containers used by this
   * instance.
   *
   * @param application {@link Application} context.
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    farkleService = FarkleService.getInstance();
    player = new MutableLiveData<>();
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   * Returns the observable list of {@link Points} instances from the server-based collection.
   */
  public MutableLiveData<List<Player>> getPlayer() {
    return player;
  }

  /**
   * Returns the most recently thrown exception or error.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Sets the currently logged-in user.
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account.setValue(account);
    refreshPlayer();
  }

  /**
   * Request a refresh from the server of the collection of {@link Points} instances.
   */
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
//    pending.add(
//        farkleService.getAll(token)
//            .subscribeOn(Schedulers.io())
//            .subscribe(this.player::postValue, this.throwable::postValue)
//    );
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

}
