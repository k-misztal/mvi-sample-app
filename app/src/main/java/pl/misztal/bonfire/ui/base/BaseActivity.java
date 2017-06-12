package pl.misztal.bonfire.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;

import pl.misztal.bonfire.App;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.di.component.ActivityComponent;
import pl.misztal.bonfire.di.component.DaggerActivityComponent;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V, ?>> extends MviActivity<V, P> {

    protected ExceptionHandler exceptionHandler;
    ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        inject(component);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }

    protected void inject(ActivityComponent component) {
        //override to inject
    }

    private void initializeInjector() {
        component = DaggerActivityComponent.builder()
                .appComponent(App.component())
                .build();
    }
}
