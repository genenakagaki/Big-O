package gene.com.bigo.sort.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gene on 3/12/2016.
 */
public class SortFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Info", "Graph" };
    private Context context;

    private int sortingAlgorithm;

    public SortFragmentPagerAdapter(FragmentManager fragmentManager, Context context, int sortingAlgorithm) {
        super(fragmentManager);
        this.context = context;
        this.sortingAlgorithm = sortingAlgorithm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SortInfoFragment.newInstance(sortingAlgorithm);
        } else {
            return SortGraphFragment.newInstance(sortingAlgorithm);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
