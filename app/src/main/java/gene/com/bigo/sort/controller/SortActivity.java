package gene.com.bigo.sort.controller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import gene.com.bigo.R;

/**
 * Created by Gene on 3/1/2016.
 */
public class SortActivity extends AppCompatActivity {

    private static final String TAG = SortActivity.class.getSimpleName();

    public static final String EXTRA_KEY = "extra_key";

    public static final int QUICK_SORT     = 0;
    public static final int MERGE_SORT     = 1;
    public static final int INSERTION_SORT = 2;
    public static final int SELECTION_SORT = 3;
    public static final int BUBBLE_SORT    = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_sort);

        String[] algorithmList = getResources().getStringArray(R.array.sorting_algorithms);
        int algorithm = getIntent().getIntExtra(EXTRA_KEY, SELECTION_SORT);
        setTitle(algorithmList[algorithm]);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SortFragmentPagerAdapter(
                getSupportFragmentManager(),
                SortActivity.this,
                algorithm));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


}
