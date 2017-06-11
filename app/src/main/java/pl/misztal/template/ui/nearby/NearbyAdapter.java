package pl.misztal.template.ui.nearby;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.Observable;
import pl.misztal.template.di.scope.FragmentSingleton;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@FragmentSingleton
public class NearbyAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;

    @Inject
    public NearbyAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    public Observable<Boolean> getLoadNewPageObservable() {
        return null;
    }
}
