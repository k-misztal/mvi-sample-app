package pl.misztal.template.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;

import pl.misztal.template.App;
import pl.misztal.template.ExceptionHandler;
import pl.misztal.template.di.component.ActivityComponent;
import pl.misztal.template.di.component.DaggerActivityComponent;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V, ?>> extends MviActivity<V, P> {
    ProgressDialog progressDialog;

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

    public void showProgressDialog(int titleRes, int messageRes) {
        showProgressDialog(getString(titleRes), getString(messageRes));
    }

    public void showProgressDialog(String title, String message) {
        dismissProgressDialog();
        progressDialog = ProgressDialog.show(this, title, message);
    }

    public void dismissProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();

        progressDialog = null;
    }

    public void showError(Throwable e, boolean pullToRefresh) {
        String messageError = exceptionHandler.getMessageError(e);
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();

        // TODO: 22.01.2017
    }
}
