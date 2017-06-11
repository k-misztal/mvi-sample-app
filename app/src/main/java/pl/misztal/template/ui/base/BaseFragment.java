package pl.misztal.template.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvi.MviFragment;

import butterknife.Unbinder;
import pl.misztal.template.di.component.DaggerFragmentComponent;
import pl.misztal.template.di.component.FragmentComponent;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V, ?>> extends MviFragment<V, P> {

    private FragmentComponent component;
    protected Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
        inject(component);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroyView();
    }

    protected void inject(@NonNull FragmentComponent component) {
        //override to inject
    }

    protected FragmentComponent getComponent() {
        return component;
    }

    private void initInjector() {
        component = DaggerFragmentComponent.builder()
                .activityComponent(getBaseActivity().component)
                .build();
    }

    protected BaseActivity<?, ?> getBaseActivity() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity());
        } else {
            throw new IllegalStateException("All activities in this app has to extend BaseActivity");
        }
    }

    public void showError(Throwable e, boolean pullToRefresh) {
        ((BaseActivity) getActivity()).showError(e, pullToRefresh);
    }

    public void showLoadingDialog(int titleResId, int messageResId) {
        //TODO;
    }

    public void showLoadingDialog(String title, String message) {
        //TODO
    }

    public void hideLoadingDialog() {
        //TODO
    }
}
