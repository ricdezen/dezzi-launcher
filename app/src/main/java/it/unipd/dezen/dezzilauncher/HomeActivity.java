package it.unipd.dezen.dezzilauncher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.unipd.dezen.dezzilauncher.applist.AppRecyclerAdapter;

/**
 * @author Riccardo De Zen.
 */
public class HomeActivity extends AppCompatActivity {

    private RecyclerView appListRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recycler initialization ---
        appListRecycler = findViewById(R.id.app_list_recycler);
        appListRecycler.setLayoutManager(new LinearLayoutManager(this));
        appListRecycler.setAdapter(new AppRecyclerAdapter(this));
    }
}
