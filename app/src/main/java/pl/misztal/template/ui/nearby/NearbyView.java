package pl.misztal.template.ui.nearby;

import io.reactivex.Observable;
import pl.misztal.template.ui.base.BaseView;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public interface NearbyView extends BaseView {

    /**
     * The intent to load the first page
     *
     * @return The emitted item boolean can be ignored because it is always true
     */
    Observable<Boolean> loadFirstPageIntent();

    /**
     * The intent to load next page
     *
     * @return Integer that says how many items there is in the adapter
     */
    Observable<Integer> loadNextPageIntent();

    void render(NearbyViewState viewState);
}
