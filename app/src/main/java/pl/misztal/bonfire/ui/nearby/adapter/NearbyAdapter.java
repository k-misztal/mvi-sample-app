package pl.misztal.bonfire.ui.nearby.adapter;

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
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.di.scope.FragmentSingleton;
import pl.misztal.bonfire.model.AdditionalItemsLoadable;
import pl.misztal.bonfire.model.FeedItem;
import pl.misztal.bonfire.model.api.model.Venue;

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
    private final ExceptionHandler exceptionHandler;
    private List<FeedItem> items;

    private PublishSubject<Integer> subject = PublishSubject.create();

    @Inject
    public NearbyAdapter(LayoutInflater inflater, ExceptionHandler exceptionHandler) {
        this.inflater = inflater;
        this.exceptionHandler = exceptionHandler;
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VENUE:
                return VenueViewHolder.inflate(inflater, parent);
            case TYPE_MORE_ITEMS:
                return MoreItemsViewHolder.inflate(inflater, parent, ignore -> subject.onNext(getItemCount()));
            default:
                throw new IllegalStateException("Unknown item type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof VenueViewHolder) {
            Venue venue = (Venue) items.get(position);
            ((VenueViewHolder) viewHolder).bind(venue);
        } else if (viewHolder instanceof MoreItemsViewHolder) {
            AdditionalItemsLoadable itemsLoadable = (AdditionalItemsLoadable) items.get(position);
            ((MoreItemsViewHolder) viewHolder).bind(itemsLoadable, exceptionHandler);
        }
    }

    @Override
    public int getItemViewType(int position) {
        FeedItem item = items.get(position);
        if (item instanceof Venue) {
            return TYPE_VENUE;
        } else if (item instanceof AdditionalItemsLoadable) {
            return TYPE_MORE_ITEMS;
        } else {
            throw new IllegalStateException("Unknown item type.");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    public Observable<Integer> getLoadNewPageObservable() {
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

                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Object oldItem = oldItems.get(oldItemPosition);
                    Object newItem = newItems.get(newItemPosition);

                    return oldItem.equals(newItem);
                }
            }, false).dispatchUpdatesTo(this);
        }
    }
}
