package pl.misztal.template.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.security.Permissions;

import pl.misztal.template.R;
import pl.misztal.template.ui.base.BaseActivity;
import pl.misztal.template.ui.base.BaseView;
import pl.misztal.template.ui.base.DefaultPresenter;
import pl.misztal.template.ui.nearby.NearbyFragment;

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

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = NearbyFragment.newInstance();
        }

        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 12.06.2017 do it good
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

        }
    }

    @NonNull
    @Override
    public DefaultPresenter createPresenter() {
        return new DefaultPresenter();
    }
}
