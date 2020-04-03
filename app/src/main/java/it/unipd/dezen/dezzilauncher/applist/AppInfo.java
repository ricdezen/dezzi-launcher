package it.unipd.dezen.dezzilauncher.applist;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

/**
 * Class meant to hold user-ready app information for display.
 *
 * @author Riccardo De Zen.
 */
public class AppInfo implements Comparable<AppInfo> {
    @NonNull
    private Drawable icon;
    @NonNull
    private String label;
    @NonNull
    private String packageName;

    /**
     * @param icon        The app icon.
     * @param label       The app name/label.
     * @param packageName The app package name.
     */
    public AppInfo(@NonNull Drawable icon, @NonNull String label, @NonNull String packageName) {
        this.icon = icon;
        this.label = label;
        this.packageName = packageName;
    }

    /**
     * @return The icon for the app.
     */
    @NonNull
    public Drawable getIcon() {
        return icon;
    }

    /**
     * @return The label for the app.
     */
    @NonNull
    public String getLabel() {
        return label;
    }

    /**
     * @return The name of the app's package
     */
    @NonNull
    public String getPackageName() {
        return packageName;
    }

    /**
     * The ordering of {@code AppInfo} items is decided by the natural ordering of the app labels.
     *
     * @param that The other appInfo to compare.
     * @return The result of {@link String#compareTo(String)}.
     */
    @Override
    public int compareTo(@NonNull AppInfo that) {
        return this.getLabel().compareTo(that.getLabel());
    }
}
