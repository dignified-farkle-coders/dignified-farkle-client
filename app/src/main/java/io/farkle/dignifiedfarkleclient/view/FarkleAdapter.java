package io.farkle.dignifiedfarkleclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.farkle.dignifiedfarkleclient.R;
import io.farkle.dignifiedfarkleclient.model.Passphrase;
import io.farkle.dignifiedfarkleclient.view.FarkleAdapter.Holder;

import java.util.List;

  public class FarkleAdapter extends RecyclerView.Adapter<Holder> {

    private final Context context;
    private final List<Passphrase> passphrases;
    private final OnClickListener clickListener;
    private final OnContextListener contextListener;

    public FarkleAdapter(Context context, List<Passphrase> passphrases,
        OnClickListener clickListener, OnContextListener contextListener) {
      this.context = context;
      this.passphrases = passphrases;
      this.clickListener = clickListener;
      this.contextListener = contextListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.farkle_item, parent, false);
      return new Holder(view);
    }

    /**
     * Binds the specified {@link Holder} to the {@link Passphrase} at the specified position in this
     * adapter instance.
     *
     * @param holder {@link Holder} referencing a bindable {@link View}.
     * @param position index of item in the adapter's list to bind to {@code holder}.
     */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
      Passphrase passphrase = passphrases.get(position);
      holder.bind(position, passphrase);
    }

    /**
     * Returns the number of items in the this instance's list of passphrases.
     *
     * @return count.
     */
    @Override
    public int getItemCount() {
      return passphrases.size();
    }

    /**
     * Listener for {@link FarkleAdapter} item clicks.
     */
    @FunctionalInterface
    public interface OnClickListener {

      /**
       * Handles a click on a specified {@link View} in the {@link RecyclerView}, corresponding to
       * the {@link Passphrase} at index {@code position} in the {@link FarkleAdapter}.
       *
       * @param view clicked {@link View}.
       * @param position selected item index of the {@link FarkleAdapter}.
       * @param passphrase {@link Passphrase} instance bound to the {@link Holder} at {code position}.
       */
      void onClick(View view, int position, Passphrase passphrase);

    }

    /**
     * Listener for {@link FarkleAdapter} context (long) presses.
     */
    @FunctionalInterface
    public interface OnContextListener {

      /**
       * Handles a long press on a specified {@code position} in the {@link FarkleAdapter}.
       *
       * @param menu {@link Menu} instance to which context items may be attached.
       * @param position index of pressed item in {@link FarkleAdapter}.
       * @param passphrase pressed instance of {@link Passphrase}.
       */
      void onLongPress(Menu menu, int position, Passphrase passphrase);

    }

    /**
     * Binder for {@link View} items in a {@link RecyclerView} and {@link Passphrase} items in a
     * {@link FarkleAdapter}.
     */
    public class Holder extends RecyclerView.ViewHolder {

      private final View view;

      private Holder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
      }

      private void bind(int position, Passphrase passphrase) {
        ((TextView) view).setText(passphrase.getKey());
        if (clickListener != null) {
          view.setOnClickListener((v) -> clickListener.onClick(v, position, passphrase));
        }
        if (contextListener != null) {
          view.setOnCreateContextMenuListener((menu, v, menuInfo) ->
              contextListener.onLongPress(menu, position, passphrase));
        }
      }

    }

  }

