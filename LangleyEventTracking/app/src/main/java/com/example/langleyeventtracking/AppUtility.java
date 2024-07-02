package com.example.langleyeventtracking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * App Utility
 * <p>
 * The AppUtility class provides utility methods that can be used across the application.
 * It includes:
 * - Clearing text from EditText fields.
 * - Displaying a DatePickerDialog to select dates.
 * <p>
 * This class is used to provide common utility functions to avoid code duplication.
 *
 * @author Joseph Langley
 */
public class AppUtility {

    /**
     * Clears the text from the given EditText fields.
     *
     * @param editTexts The EditText fields to clear.
     */
    public static void clearText(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText != null) {
                editText.getText().clear();
            }
        }
    }

    /**
     * Ensures the text from the given EditText is not empty.
     *
     * @param editTexts The EditText fields to verify.
     */
    public static boolean validateText(Context context, EditText... editTexts) {
        for (EditText editText : editTexts) {
            String inputText = editText.getText().toString().trim();
            if (inputText.isEmpty()) {
                showToast(context,"One or more fields is empty");
                return false;
            }
            int inputType = editText.getInputType();
            if ((inputType & InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(inputText).matches()) {
                    showToast(context, "Not valid email address");
                    clearText(editText);
                    return false;
                }
            }
            if ((inputType & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                if (inputText.length() < 6) {
                    showToast(context, "Password must be greater than 6 characters");
                    clearText(editText);
                    return false;
                }
            }
        }
        return true;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a DatePickerDialog and sets the selected date to the given EditText.
     *
     * @param context The context in which the DatePickerDialog is shown.
     * @param editText The EditText to set the selected date.
     */
    public static void showDatePickerDialog(Context context, final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, month, day) -> {
                    // Set the selected date to the calendar
                    calendar.set(year, month, day);
                    // Format the date and set it to the EditText
                    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
                    String formattedDate = sdf.format(calendar.getTime());
                    editText.setText(formattedDate);
                },
                mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
