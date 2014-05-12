/**
 * 
 */

package com.andrew.apolloMod.ui.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andrew.apolloMod.helpers.RefreshableFragment;

/**
 * @author Andrew Neal
 *ViewPager适配器
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //添加TabHost并通知适配器发生改变
    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    //返回ViewPager 页数
    @Override
    public int getCount() {
        return mFragments.size();
    }

    //返回当前页
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * This method update the fragments that extends the {@link RefreshableFragment} class
     * 刷新ViewPager方法
     */
    public void refresh() {
        for (int i = 0; i < mFragments.size(); i++) {
            if( mFragments.get(i) instanceof RefreshableFragment ) {
                ((RefreshableFragment)mFragments.get(i)).refresh();
            }
        }
    }

}
