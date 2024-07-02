package com.example.langleyeventtracking.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.langleyeventtracking.R;

/**
 * Settings Activity
 * <p>
 * The SettingsActivity class handles the application settings, including:
 * - SMS notifications toggle.
 * - User logout functionality.
 * <p>
 * This class is used to manage user preferences and settings.
 *
 * @author Joseph Langley
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Replace the content with the SettingsFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Settings Fragment
     * <p>
     * This fragment handles the display and interaction with user settings.
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey);

            // Retrieve shared preferences
            SharedPreferences preferences = requireActivity().getSharedPreferences("com.example.langleyeventtracking", Context.MODE_PRIVATE);

            // Initialize the SMS notifications switch
            boolean isSmsEnabled = preferences.getBoolean("sms_notifications", false);
            SwitchPreferenceCompat smsNotificationsPreference = findPreference("sms_notifications");
            if (smsNotificationsPreference != null) {
                smsNotificationsPreference.setChecked(isSmsEnabled);

                // Set the change listener for the SMS notifications switch
                smsNotificationsPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                    boolean isEnabled = (boolean) newValue;
                    preferences.edit().putBoolean("sms_notifications", isEnabled).apply();
                    return true;
                });
            }

            // Initialize the logout preference
            Preference logoutPreference = findPreference("logout");
            if (logoutPreference != null) {
                // Set the click listener for the logout preference
                logoutPreference.setOnPreferenceClickListener(preference -> {
                    // Clear user session and navigate to login activity
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    requireActivity().finish();
                    return true;
                });
            }
        }
    }
}
