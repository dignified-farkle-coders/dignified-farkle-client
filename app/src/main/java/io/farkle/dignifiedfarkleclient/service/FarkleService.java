package io.farkle.dignifiedfarkleclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.farkle.dignifiedfarkleclient.BuildConfig;
import io.farkle.dignifiedfarkleclient.model.Passphrase;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
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

  /**
   * Returns (constructing as necessary) the singleton instance of the Retrofit-generated instance
   * of this interface.
   *
   * @return singleton instance.
   */

  /**
   * Requests all passphrases associated with the currently logged-in user.
   *
   * @param token OAuth2.0 token.
   * @return observable list of passphrases.
   */
  @GET("passphrases/")
  Observable<List<Passphrase>> getAll(@Header("Authorization") String token);

  /**
   * Requests a single passphrase of the currently logged-in user, with the specified ID.
   *
   * @param token OAuth2.0 token.
   * @param id unique numeric identifier of passphrase.
   * @return observable result.
   */
  @GET("passphrases/{id}")
  Single<Passphrase> get(@Header("Authorization") String token,
      @Path("id") long id);

  /**
   * Requests a single passphrase of the currently logged-in user, with the specified key.
   *
   * @param token OAuth2.0 token.
   * @param key unique {@link String} identifier of passphrase.
   * @return observable result.
   */
  @GET("passphrases/{key}")
  Single<Passphrase> get(@Header("Authorization") String token,
      @Path("key") String key);

  /**
   * Requests deletion of the specified passphrase associated with the currently logged-in user.
   *
   * @param token OAuth2.0 token.
   * @param id unique numeric identifier of passphrase.
   * @return observable success/failure result.
   */
  @DELETE("passphrases/{id}")
  Completable delete(@Header("Authorization") String token, @Path("id") long id);

  /**
   * Sends an updated passphrase, associated with the currently logged-in user, to the server.
   *
   * @param token OAuth2.0 token.
   * @param id unique numeric identifier of passphrase.
   * @param passphrase updated {@link Passphrase} instance.
   * @return observable result.
   */
  @PUT("passphrases/{id}")
  Single<Passphrase> put(@Header("Authorization") String token, @Path("id") long id,
      @Body Passphrase passphrase, @Query("regenerate") boolean regenerate,
      @Query("length") int length);

  /**
   * Sends a new {@link Passphrase} to the server, for adding to the collection associated with the
   * currently logged-in user.
   *
   * @param token OAuth2.0 token.
   * @param passphrase new {@link Passphrase} instance.
   * @return observable result.
   */
  @POST("passphrases/")
  Single<Passphrase> post(@Header("Authorization") String token, @Body Passphrase passphrase);

  static FarkleService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final FarkleService INSTANCE;

    static {
      // TODO Investigate logging interceptor issues.
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(FarkleService.class);
    }

  }

}
