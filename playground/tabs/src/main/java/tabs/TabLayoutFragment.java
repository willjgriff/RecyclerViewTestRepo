package tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.Playground.R;

/**
 * Created by Will on 23/02/2016.
 */
public class TabLayoutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_tab_view_pager);
        viewPager.setAdapter(new TabLayoutPageAdapter(getChildFragmentManager(), getActivity()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_tab_layout_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
