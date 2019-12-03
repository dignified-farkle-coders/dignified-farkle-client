package io.farkle.dignifiedfarkleclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.farkle.dignifiedfarkleclient.BuildConfig;
import io.farkle.dignifiedfarkleclient.model.entity.Action;
import io.farkle.dignifiedfarkleclient.model.entity.Player;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Declaration of proxy methods used to connect to Diceware server application, with
 * singleton-pattern-based instantiation of Retrofit-generated implementation.
 */
public interface FarkleService {

  static FarkleService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @GET("actions/")
  Observable<List<Action>> getAllActions(@Header("Authorization") String token);

  @GET("player/")
  Observable<List<Player>> getPlayerInfo(@Header("Authorization") String token);

//  @POST("points/")
//  Single<Points> post(@Header("Authorization") String token, @Body Points points);
//
//  @DELETE("points/{id}")
//  Completable delete(@Header("Authorization") String token, @Path("id") long id);
//
//  @PUT("points/{id}")
//  Single<Points> put(@Header("Authorization") String token, @Path("id") long id,
//      @Body Points points, @Query("regenerate") boolean regenerate,
//      @Query("length") int length);
//
//  @GET("points/")
//  Observable<List<Points>> getAll(@Header("Authorization") String token);

  class InstanceHolder {

    private static final FarkleService INSTANCE;

    static {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .client(client)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(FarkleService.class);
    }

  }

}
