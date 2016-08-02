package com.scene.baseframe;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.scene.baseframe.config.PermissionsConfig;
import com.scene.baseframe.ui.fragment.MainFragment;
import com.scene.baselib.easypermissions.AfterPermissionGranted;
import com.scene.baselib.easypermissions.EasyPermissions;
import com.scene.baselib.frame.SupportActivity;
import com.scene.baselib.frame.anim.DefaultHorizontalAnimator;
import com.scene.baselib.frame.anim.FragmentAnimator;
import com.scene.baselib.util.LogUtil;

import java.util.List;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
        //申请权限---内存读写权限
        applyExternalPer();
    }


    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }


    @AfterPermissionGranted(PermissionsConfig.RC_EXTERNAL_PERM)
    public void applyExternalPer() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // 已经获取到权限了
        } else {
            //申请一个权限 多个权限的申请最后一个参数可以是字符串数组
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_external),
                    PermissionsConfig.RC_EXTERNAL_PERM, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }


}
