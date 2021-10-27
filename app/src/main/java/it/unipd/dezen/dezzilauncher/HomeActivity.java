package it.unipd.dezen.dezzilauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.unipd.dezen.dezzilauncher.applist.AppRecyclerAdapter;

/**
 * @author Riccardo De Zen.
 */
public class HomeActivity extends AppCompatActivity {

    private ViewGroup appListLayout;
    private ImageView expandArrow;
    private RecyclerView appListRecycler;
    private TextView dateText;
    private BottomSheetBehavior bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing transition ---
        appListLayout = findViewById(R.id.list_layout);
        dateText = findViewById(R.id.date_text);

        // Recycler initialization ---
        appListRecycler = findViewById(R.id.app_list_recycler);
        appListRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        appListRecycler.setAdapter(new AppRecyclerAdapter(this));

        // Bottom sheet init ---
        expandArrow = findViewById(R.id.expand_app_list_arrow);
        bottomSheet = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        bottomSheet.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        expandList();
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        collapseList();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                appListLayout.setAlpha(slideOffset);
            }
        });

    }

    /**
     * Whenever we receive a new intent while already active, we may want to close the app list.
     * We don't want to if we were just put out of focus (we need to preserve the user experience).
     *
     * @param intent The Intent that started this Activity.
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (isListExpanded())
            collapseList();
        Log.e(this.getClass().getName(), intent.getFlags() + "");
    }

    /**
     * When the Activity is paused we detect that we went out of focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * When the app is resumed the date is updated.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updateDate();
    }

    /**
     * When the back button is pressed we collapse the bottom sheet
     */
    @Override
    public void onBackPressed() {
        if (bottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED)
            bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        else
            super.onBackPressed();
    }

    /**
     * @return {@code true} if the List of apps is visible, {@code false} otherwise.
     */
    private boolean isListExpanded() {
        return appListLayout.getVisibility() == View.VISIBLE;
    }

    /**
     * Make button invisible and list visible.
     */
    private void expandList() {
        expandArrow.setVisibility(View.INVISIBLE);
        appListLayout.setAlpha(1);
    }

    /**
     * Make button visible and list invisible.
     */
    private void collapseList() {
        appListLayout.setAlpha(0);
        expandArrow.setVisibility(View.VISIBLE);
    }

    /**
     * Method called when updating the date of the text view.
     */
    private void updateDate() {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        String today = dateFormat.format(Calendar.getInstance().getTime());
        dateText.setText(today);
    }
}
