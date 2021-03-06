package pl.misztal.bonfire.ui.nearby.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.misztal.bonfire.R;
import pl.misztal.bonfire.model.api.model.Image;
import pl.misztal.bonfire.model.api.model.Venue;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

class VenueViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.checkins)
    TextView checkins;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.image)
    ImageView imageView;

    static VenueViewHolder inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.card_item_venue, parent, false);
        return new VenueViewHolder(view);
    }

    private VenueViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Venue venue) {
        checkins.setText(String.valueOf(venue.getStats().getCheckinsCount()));
        name.setText(venue.getName());
        Image image = venue.getDefaultPhoto();

        if (image != null) {
            Picasso.with(itemView.getContext()).load(image.getUrl()).into(imageView);
        } else {
            Picasso.with(itemView.getContext()).load(R.drawable.ic_blured).into(imageView);
        }
    }

}
