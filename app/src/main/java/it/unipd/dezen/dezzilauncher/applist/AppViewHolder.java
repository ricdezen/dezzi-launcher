package it.unipd.dezen.dezzilauncher.applist;

import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.unipd.dezen.dezzilauncher.R;

/**
 * {@link androidx.recyclerview.widget.RecyclerView.ViewHolder} class for the
 * {@link AppRecyclerAdapter} class.
 *
 * @author Riccardo De Zen.
 */
class AppViewHolder extends RecyclerView.ViewHolder {

    private ImageView iconView;
    private TextView titleView;
    private String currentPackage;

    /**
     * Constructor taking a View as its parameter, the View must be an inflation of a View
     * containing the items defined in TODO
     *
     * @param itemView The View for this holder, must be an inflation of TODO.
     */
    public AppViewHolder(@NonNull View itemView) {
        super(itemView);
        //On click the current package starts an Activity.
        itemView.setOnClickListener(view -> view.getContext().startActivity(
                view.getContext().getPackageManager().getLaunchIntentForPackage(currentPackage)
        ));
        this.iconView = itemView.findViewById(R.id.base_list_item_icon);
        this.titleView = itemView.findViewById(R.id.base_list_item_title);
    }

    /**
     * Populate this ViewHolder with the content of the given parameter.
     *
     * @param appInfo The {@link ResolveInfo} Object containing the data of an app.
     */
    public void populate(AppInfo appInfo) {
        currentPackage = appInfo.getPackageName();
        iconView.setImageDrawable(appInfo.getIcon());
        titleView.setText(appInfo.getLabel());
    }
}
