package it.unipd.dezen.dezzilauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.unipd.dezen.dezzilauncher.applist.AppRecyclerAdapter;

/**
 * @author Riccardo De Zen.
 */
public class HomeActivity extends AppCompatActivity {

    private ViewGroup appListLayout;
    private ImageButton expandButton;
    private RecyclerView appListRecycler;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initializing transition ---
        appListLayout = findViewById(R.id.list_layout);
        dateText = findViewById(R.id.date_text);

        // Recycler initialization ---
        appListRecycler = findViewById(R.id.app_list_recycler);
        appListRecycler.setLayoutManager(new LinearLayoutManager(this));
        appListRecycler.setAdapter(new AppRecyclerAdapter(this));

        // Button for list display ---
        expandButton = findViewById(R.id.expand_app_list_button);
        expandButton.setOnClickListener(view -> {
            expandList();
        });
    }

    /**
     * When the app is resumed the date is updated.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updateDate();
    }

    @Override
    public void onBackPressed() {
        if (isListExpanded())
            collapseList();
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
        expandButton.setVisibility(View.GONE);
        appListLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Make button visible and list invisible.
     */
    private void collapseList() {
        appListLayout.setVisibility(View.GONE);
        expandButton.setVisibility(View.VISIBLE);
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
