/*
 * MainActivity_Trigger_Snippet.java
 * ------------------------------------------------------------
 * EDUCATIONAL SECURITY SIMULATION – CODE SNIPPET
 *
 * Course: IE3112 – Mobile Security
 * Institution: Sri Lanka Institute of Information Technology (SLIIT)
 *
 * Description:
 * This snippet demonstrates how a modified Android Activity
 * can trigger a background service after application tampering.
 *
 * The purpose is to illustrate how user interaction or simple
 * logic conditions can be abused to start background services
 * without raising suspicion.
 *
 * IMPORTANT:
 * - This is NOT a complete MainActivity implementation
 * - This code is NOT directly buildable
 * - Provided only for academic reference and viva explanation
 */

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Calendar;

// -------------------- MENU CREATION (SIMULATION) --------------------

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_extra, menu);

    // Simulated trigger options added via menu modification
    menu.add(Menu.NONE, 9001, Menu.NONE, "Run Notification Demo");
    menu.add(Menu.NONE, 9002, Menu.NONE, "Run Timed CPU Demo (Even Minute)");

    return true;
}

// -------------------- TRIGGER LOGIC (SIMULATION) --------------------

@Override
public boolean onOptionsItemSelected(MenuItem item) {

    // Trigger 1: User‑initiated foreground service execution
    if (item.getItemId() == 9001) {

        Intent demoIntent = new Intent(
                this,
                com.amaze.filemanager.asynchronous.services.DemoService.class
        );

        // Pass execution mode to service
        demoIntent.putExtra("demo_mode", "notification");

        ContextCompat.startForegroundService(this, demoIntent);

        Toast.makeText(
                this,
                "Notification demo started (simulation)",
                Toast.LENGTH_SHORT
        ).show();

        return true;
    }

    // Trigger 2: Time‑based conditional execution
    if (item.getItemId() == 9002) {

        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);

        // Execute only on even minutes (demonstration of stealth condition)
        if (minute % 2 == 0) {

            Intent demoIntent = new Intent(
                    this,
                    com.amaze.filemanager.asynchronous.services.DemoService.class
            );

            demoIntent.putExtra("demo_mode", "cpu");

            ContextCompat.startForegroundService(this, demoIntent);

            Toast.makeText(
                    this,
                    "Timed condition met. CPU demo started.",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            Toast.makeText(
                    this,
                    "Timed condition not met. Try again on an even minute.",
                    Toast.LENGTH_SHORT
            ).show();
        }

        return true;
    }

    return super.onOptionsItemSelected(item);
}
``
