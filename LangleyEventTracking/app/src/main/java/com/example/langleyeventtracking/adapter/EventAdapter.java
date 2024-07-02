package com.example.langleyeventtracking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langleyeventtracking.R;
import com.example.langleyeventtracking.data.models.Event;

/**
 * Event Adapter
 * <p>
 * The EventAdapter class provides a binding from the event data set to the views that are displayed within a RecyclerView.
 * It includes:
 * - Setting up the view holder pattern for efficient view management.
 * - Handling click events on individual items.
 * <p>
 * This class is used for displaying a list of events in a RecyclerView.
 *
 * @author Joseph Langley
 */
public class EventAdapter extends ListAdapter<Event, EventAdapter.EventViewHolder> {

    private final Context context; // Context for inflating the layout
    private OnItemClickListener listener; // Listener for handling item clicks

    /**
     * Constructor for EventAdapter.
     *
     * @param context The context in which the adapter is used.
     */
    public EventAdapter(@NonNull Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the event layout and create a new ViewHolder
        View itemView = LayoutInflater.from(context).inflate(R.layout.event_layout, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        // Bind the data to the ViewHolder
        Event currentEvent = getItem(position);
        holder.bind(currentEvent);
    }

    /**
     * Sets the listener for item clicks.
     *
     * @param listener The listener to set.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Interface for handling item click events.
     */
    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    /**
     * ViewHolder class for event items.
     */
    public class EventViewHolder extends RecyclerView.ViewHolder {

        private final TextView title, date, description; // TextViews for event details

        /**
         * Constructor for EventViewHolder.
         *
         * @param itemView The view of the event item.
         */
        public EventViewHolder(View itemView) {
            super(itemView);
            CardView eventContainer = itemView.findViewById(R.id.event_container);
            title = itemView.findViewById(R.id.eventTitle);
            date = itemView.findViewById(R.id.eventDate);
            description = itemView.findViewById(R.id.eventDescription);

            // Set click listener for the event container
            eventContainer.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        /**
         * Binds the event data to the views.
         *
         * @param event The event to bind.
         */
        public void bind(Event event) {
            title.setText(event.getTitle());
            date.setText(event.getDate());
            description.setText(event.getDescription());
        }
    }

    // DiffUtil callback for calculating the difference between old and new lists
    private static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.equals(newItem);
        }
    };
}
