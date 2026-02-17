package org.lightsys.eventApp.views.SettingsViews;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;

import org.lightsys.eventApp.R;
import org.lightsys.eventApp.tools.ColorContrastHelper;
import org.lightsys.eventApp.tools.LocalDB;

/**
 * Created by Littlesnowman88 on 05/30/2018
 * Allows the user to adjust time zone and notification update frequency
 */
public class SettingsActivity extends AppCompatActivity {

    private int color, black_or_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        /*initialize activity-related settings data*/
        setContentView(R.layout.settings_layout);

        // Find and set up the Toolbar from the layout file
        Toolbar toolbar = findViewById(R.id.settings_toolbar); // Ensure your layout has a Toolbar with this ID
        setSupportActionBar(toolbar);

        /* set up an action bar for returning to Main Activity */
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LocalDB db = new LocalDB(this.getApplicationContext());
        try {
            color = Color.parseColor(db.getThemeColor("themeColor"));
        } catch (Exception e) {
            color = Color.parseColor("#6080C0");
        }
        black_or_white = ColorContrastHelper.determineBlackOrWhite(color);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        if (toolbar != null) {
            toolbar.setBackgroundColor(color);
            toolbar.setTitleTextColor(black_or_white);
            final AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.settings_app_bar);
            if (app_bar != null) {
                app_bar.setBackgroundColor(color);
            }
        }

        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        if (upArrow != null && toolbar != null) {
            upArrow.setColorFilter(black_or_white, PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(upArrow);
        }
    }

    /* used for the back-arrow button that returns to the Main Activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}