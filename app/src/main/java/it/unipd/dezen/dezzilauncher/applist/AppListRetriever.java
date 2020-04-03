package it.unipd.dezen.dezzilauncher.applist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppListRetriever extends AsyncTask<Void, Float, Boolean> {

    private PackageManager packageManager;
    private MutableLiveData<List<AppInfo>> listener;
    private List<AppInfo> appsList;

    /**
     * @param packageManager The {@link PackageManager} used to retrieve the app data.
     * @param listener       The {@link MutableLiveData} that will have to hold the data.
     */
    public AppListRetriever(@NonNull PackageManager packageManager,
                            @NonNull MutableLiveData<List<AppInfo>> listener) {
        this.packageManager = packageManager;
        this.listener = listener;
    }

    /**
     * Loads the list of apps and notifies the listener for every single one.
     *
     * @param voids No args taken.
     * @return {@code true} if no issues arose.
     */
    @Override
    protected Boolean doInBackground(Void... voids) {
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        appsList = new ArrayList<>();
        List<ResolveInfo> tempList = packageManager.queryIntentActivities(i, 0);
        for (ResolveInfo eachInfo : tempList) {
            appsList.add(new AppInfo(
                    eachInfo.loadIcon(packageManager),
                    eachInfo.loadLabel(packageManager).toString(),
                    eachInfo.activityInfo.packageName
            ));
        }

        return true;
    }

    /**
     * After loading the list the {@link androidx.lifecycle.LiveData} is updated.
     *
     * @param aBoolean The result of the loading operation, will notify only if {@code true}.
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Collections.sort(appsList);
        if (aBoolean) listener.postValue(appsList);
    }
}
