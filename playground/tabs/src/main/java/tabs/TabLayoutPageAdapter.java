package tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Will on 07/03/2016.
 */
public class TabLayoutPageAdapter extends FragmentPagerAdapter {
    private String[] mTabs = new String[]{"Tab 1", "Tab 2", "Tab 3"};
    private Context mContext;

    public TabLayoutPageAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
