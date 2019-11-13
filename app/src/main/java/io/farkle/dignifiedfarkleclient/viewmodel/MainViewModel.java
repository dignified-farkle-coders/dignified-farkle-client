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
  private final MutableLiveData<List<Points>> passphrases;
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
    passphrases = new MutableLiveData<>();
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   * Returns the observable list of {@link Points} instances from the server-based collection.
   */
  public LiveData<List<Points>> getPassphrases() {
    return passphrases;
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
    refreshPassphrases();
  }

  /**
   * Deletes the specified {@link Points} from the server-based collection.
   */
  public void deletePassphrase(Points passphrase) {
    GoogleSignInAccount account = this.account.getValue();
    if (passphrase != null && passphrase.getId() > 0 && account != null) {
      String token = getAuthorizationHeader(account);
      pending.add(
          farkleService.delete(token, passphrase.getId())
              .subscribeOn(Schedulers.io())
              .subscribe(() -> refreshPassphrases(account), this.throwable::postValue)
      );
    }
  }

  /**
   * Request a refresh from the server of the collection of {@link Points} instances.
   */
  public void refreshPassphrases() {
    GoogleSignInAccount account = this.account.getValue();
    if (account != null) {
      refreshPassphrases(account);
    } else {
      passphrases.setValue(Collections.EMPTY_LIST);
    }
  }

  /**
   * Adds the specified {@link Points} instance to the server-based collection.
   */
  public void addPassphrase(Points passphrase) {
    GoogleSignInAccount account = this.account.getValue();
    if (account != null) {
      String token = getAuthorizationHeader(account);
      pending.add(
          farkleService.post(token, passphrase)
              .subscribeOn(Schedulers.io())
              .subscribe((p) -> refreshPassphrases(account), this.throwable::postValue)
      );
    }
  }

  /**
   * Updates the specified {@link Points} instance in the server-based collection.
   */
  public void updatePassphrase(Points passphrase, boolean regenerate, int length) {
    GoogleSignInAccount account = this.account.getValue();
    if (account != null) {
      String token = getAuthorizationHeader(account);
      pending.add(
          farkleService.put(token, passphrase.getId(), passphrase, regenerate, length)
              .subscribeOn(Schedulers.io())
              .subscribe((p) -> refreshPassphrases(account), this.throwable::postValue)
      );
    }
  }

  private void refreshPassphrases(GoogleSignInAccount account) {
    String token = getAuthorizationHeader(account);
    pending.add(
        farkleService.getAll(token)
            .subscribeOn(Schedulers.io())
            .subscribe(this.passphrases::postValue, this.throwable::postValue)
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

}
