package it.unipd.dezen.dezzilauncher.applist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unipd.dezen.dezzilauncher.R;

/**
 * Recycler adapter for the app list, self initializes the needed data from the given
 * {@link android.content.Context}.
 *
 * @author Riccardo De Zen.
 */
public class AppRecyclerAdapter extends RecyclerView.Adapter<AppViewHolder> implements Observer<List<AppInfo>> {

    private static final int ITEM_LAYOUT = R.layout.base_list_item;

    private MutableLiveData<List<AppInfo>> appsList = new MutableLiveData<>();

    /**
     * The default constructor, taking in a {@link Context}.
     *
     * @param context The calling {@link Context}, used to instantiate the app list.
     */
    public AppRecyclerAdapter(@NonNull Context context) {
        appsList.setValue(new ArrayList<AppInfo>());
        appsList.observeForever(this);

        new AppListRetriever(
                context.getPackageManager(),
                appsList
        ).execute();
    }

    /**
     * Method called when creating a new ViewHolder.
     *
     * @param parent   The parent of the Holder, used to get a suitable
     *                 {@link android.view.LayoutInflater}.
     * @param viewType UNUSED
     * @return A new {@link AppViewHolder}.
     */
    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater parentInflater = LayoutInflater.from(parent.getContext());
        View itemView = parentInflater.inflate(ITEM_LAYOUT, parent, false);
        return new AppViewHolder(itemView);
    }

    /**
     * @param holder   The holder to bind the data on.
     * @param position The position in the list of the item to display.
     */
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        if (appsList.getValue() == null) return;
        holder.populate(appsList.getValue().get(position));
    }

    /**
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        if (appsList.getValue() == null) return 0;
        return appsList.getValue().size();
    }

    /**
     * @param appInfos The new value of the list. UNUSED.
     */
    @Override
    public void onChanged(List<AppInfo> appInfos) {
        notifyDataSetChanged();
    }
}
