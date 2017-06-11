package pl.misztal.template.ui.nearby;

import javax.inject.Inject;

import pl.misztal.template.di.scope.FragmentSingleton;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.ui.base.BasePresenter;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@FragmentSingleton
public class NearbyPresenter extends BasePresenter<NearbyView, NearbyViewState> {

    private final DataManager dataManager;

    @Inject
    public NearbyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void bindIntents() {

    }

}
