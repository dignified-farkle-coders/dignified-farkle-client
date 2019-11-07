package io.farkle.dignifiedfarkleclient;

import android.app.Application;
import io.farkle.dignifiedfarkleclient.service.GoogleSignInService;

/**
 * Class containing main (non-UI) entry point for this app.
 */
public class FarkleApplication extends Application {

  /**
   * Initializes the app by passing this instance (as the context) to {@link GoogleSignInService}.
   */
  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setApplicationContext(this);
  }

}
