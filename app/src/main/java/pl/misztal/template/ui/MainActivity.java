package pl.misztal.template.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import pl.misztal.template.R;
import pl.misztal.template.ui.base.BaseActivity;
import pl.misztal.template.ui.base.BaseView;
import pl.misztal.template.ui.base.DefaultPresenter;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class MainActivity extends BaseActivity<BaseView, DefaultPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NonNull
    @Override
    public DefaultPresenter createPresenter() {
        return new DefaultPresenter();
    }
}
