package pl.misztal.template.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

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

    public static final int REQUEST_LOCATION_CODE = 1;

    private boolean fragmentInited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionsGranted()) {
            initFragment();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            finish();
        }
    }

    private void initFragment() {
        if (!fragmentInited) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
            if (fragment == null) {
                fragment = NearbyFragment.newInstance();
            }

            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
            fragmentInited = true;
        }
    }

    private boolean permissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @NonNull
    @Override
    public DefaultPresenter createPresenter() {
        return new DefaultPresenter();
    }
}
