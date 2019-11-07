package io.farkle.dignifiedfarkleclient;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class FarkleApplication extends Application {


  @Override
  public void onCreate() {
    super.onCreate();
//    GoogleSignInService.setApplicationContext(this);
    Stetho.initializeWithDefaults(this);

    NoteDatabase.setApplicationContext(this);
    NoteDatabase database = NoteDatabase.getInstance();
    new Thread(new Runnable() {
      @Override
      public void run() {
        database.getNoteDao().delete();
      }
    }).start();
  }

}