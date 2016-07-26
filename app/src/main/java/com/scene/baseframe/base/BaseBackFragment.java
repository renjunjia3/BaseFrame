package com.scene.baseframe.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.scene.baseframe.R;
import com.scene.baselib.frame.swipeback.SwipeBackFragment;
import com.scene.baselib.util.LogUtil;


/**
 * Created by scene on 16/2/7.
 */
public class BaseBackFragment extends SwipeBackFragment {
    private static final String TAG = "Fragmentation";
    private TextView title;

    /**
     * @param toolbar  页面内的toolbar
     * @param strTitle 标题
     */
    protected void initToolbarNav(Toolbar toolbar, String strTitle) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        try {
            title = (TextView) toolbar.findViewById(R.id.title);
            title.setText(strTitle);
        } catch (Exception e) {
            LogUtil.e("toolbar没有自定义的title");
            e.printStackTrace();
        }


//        initToolbarMenu(toolbar);
    }

//    protected void initToolbarMenu(Toolbar toolbar) {
//        toolbar.inflateMenu(R.menu.hierarchy);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_hierarchy:
//                        _mActivity.showFragmentStackHierarchyView();
//                        _mActivity.logFragmentStackHierarchy(TAG);
//                        break;
//                }
//                return true;
//            }
//        });
//    }
}
