package com.example.langleyeventtracking.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.langleyeventtracking.R;
import com.example.langleyeventtracking.viewmodels.EventViewModel;

/**
 * SmsNotifyFragment
 * <p>
 * The SmsNotifyFragment class displays a dialog asking the user if they would like to enable SMS notifications.
 * It includes:
 * - Two buttons: "Yes" and "No" for enabling/disabling SMS notifications.
 * - Interaction with EventViewModel to update the SMS notifications status.
 * <p>
 * This class is used to manage the SMS notifications preference.
 *
 * @author Joseph Langley
 */
public class SmsNotifyFragment extends DialogFragment {

    private EventViewModel eventViewModel; // ViewModel for event operations

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_permission, container, false); // Inflate the layout for this fragment

        // Initialize buttons
        Button buttonYes = view.findViewById(R.id.button_yes);
        Button buttonNo = view.findViewById(R.id.button_no);

        // Initialize the ViewModel
        eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        // Set the click listener for the "Yes" button
        buttonYes.setOnClickListener(v -> {
            // Enable SMS notifications in shared preferences
            SharedPreferences preferences = requireActivity().getSharedPreferences("com.example.langleyeventtracking", Context.MODE_PRIVATE);
            preferences.edit().putBoolean("sms_notifications", true).apply();

            // Update the ViewModel
            eventViewModel.setSmsNotificationsEnabled(true);
            dismiss(); // Close the dialog
        });

        // Set the click listener for the "No" button
        buttonNo.setOnClickListener(v -> {
            // Update the ViewModel
            eventViewModel.setSmsNotificationsEnabled(false);
            dismiss(); // Close the dialog
        });

        return view; // Return the view for this fragment
    }

    @Override
    public void onStart() {
        super.onStart();
        // Ensure the dialog takes up the full screen
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the style for the dialog
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
    }
}
