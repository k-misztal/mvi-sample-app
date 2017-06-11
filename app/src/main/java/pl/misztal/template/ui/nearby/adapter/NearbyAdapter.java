package pl.misztal.template.ui.nearby.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.misztal.template.di.scope.FragmentSingleton;
import pl.misztal.template.model.FeedItem;
import pl.misztal.template.model.api.model.Venue;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@FragmentSingleton
public class NearbyAdapter extends RecyclerView.Adapter {

    private static final int TYPE_VENUE = 0;
    private static final int TYPE_MORE_ITEMS = 1;

    private final LayoutInflater inflater;
    private List<FeedItem> items;

    private PublishSubject<Boolean> subject = PublishSubject.create();

    @Inject
    public NearbyAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VENUE:
                return VenueViewHolder.inflate(inflater, parent);
            case TYPE_MORE_ITEMS:
            default:
                throw new IllegalStateException("Unknown item type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof VenueViewHolder) {
            Venue venue = (Venue) items.get(position);
            ((VenueViewHolder) viewHolder).bind(venue);
        }
    }

    @Override
    public int getItemViewType(int position) {
        FeedItem item = items.get(position);
        if (item instanceof Venue) {
            return TYPE_VENUE;
        } else {
            throw new IllegalStateException("Unknown item type.");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    public Observable<Boolean> getLoadNewPageObservable() {
        return subject;
    }

    public void setItems(List<FeedItem> newItems) {
        List<FeedItem> oldItems = this.items;
        this.items = newItems;

        if (oldItems == null) {
            notifyDataSetChanged();
        } else {
            // Use Diff utils
            DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldItems.size();
                }

                @Override
                public int getNewListSize() {
                    return newItems.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Object oldItem = oldItems.get(oldItemPosition);
                    Object newItem = newItems.get(newItemPosition);

                    if (oldItem instanceof Venue && newItem instanceof Venue
                            && ((Venue) oldItem).getId().equals(((Venue) newItem).getId())) {
                        return true;
                    }

                    // TODO: 11.06.2017 other VHs

                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Object oldItem = oldItems.get(oldItemPosition);
                    Object newItem = newItems.get(newItemPosition);

                    return oldItem.equals(newItem);
                }
            }, true).dispatchUpdatesTo(this);
        }
    }
}
