package gene.com.bigo.sort.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gene.com.bigo.R;

/**
 * Created by Gene on 3/12/2016.
 */
public class SortInfoFragment extends Fragment {

    private static final String TAG = SortInfoFragment.class.getSimpleName();

    public static String SORTING_KEY = "sorting_key";

    private int mSortingAlgorithm;

    public static SortInfoFragment newInstance(int sortingAlgorithm) {
        Log.d(TAG, "newInstance");
        Bundle args = new Bundle();
        args.putInt(SORTING_KEY, sortingAlgorithm);
        SortInfoFragment fragment = new SortInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSortingAlgorithm = getArguments().getInt(SORTING_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort_info, container, false);

        TextView textView;
        switch (mSortingAlgorithm) {
            case (SortActivity.QUICK_SORT):
                textView = (TextView) view.findViewById(R.id.bigo_best);
                textView.setText("O(nlogn)");

                textView = (TextView) view.findViewById(R.id.bigo_average);
                textView.setText("O(nlogn");

                textView = (TextView) view.findViewById(R.id.bigo_worst);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_memory);
                textView.setText("O(logn) | O(n)");

                textView = (TextView)view.findViewById(R.id.bigo_stable);
                textView.setText("both");
                break;

            case (SortActivity.MERGE_SORT):
                textView = (TextView) view.findViewById(R.id.bigo_best);
                textView.setText("O(nlogn)");

                textView = (TextView) view.findViewById(R.id.bigo_average);
                textView.setText("O(nlogn)");

                textView = (TextView) view.findViewById(R.id.bigo_worst);
                textView.setText("O(nlogn)");

                textView = (TextView) view.findViewById(R.id.bigo_memory);
                textView.setText("O(n)");

                textView = (TextView)view.findViewById(R.id.bigo_stable);
                textView.setText("yes");
                break;

            case (SortActivity.INSERTION_SORT):
                textView = (TextView) view.findViewById(R.id.bigo_best);
                textView.setText(Html.fromHtml("O(n)"));

                textView = (TextView) view.findViewById(R.id.bigo_average);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_worst);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_memory);
                textView.setText("O(1)");

                textView = (TextView)view.findViewById(R.id.bigo_stable);
                textView.setText("yes");

                textView = (TextView) view.findViewById(R.id.sorting_info);
                textView.setText(R.string.insertion_sort_info);
                break;

            case (SortActivity.SELECTION_SORT):
                textView = (TextView) view.findViewById(R.id.bigo_best);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_average);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_worst);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_memory);
                textView.setText("O(1)");

                textView = (TextView)view.findViewById(R.id.bigo_stable);
                textView.setText("no");

                textView = (TextView) view.findViewById(R.id.sorting_info);
                textView.setText(R.string.selection_sort_info);
                break;

            case (SortActivity.BUBBLE_SORT):
                textView = (TextView) view.findViewById(R.id.bigo_best);
                textView.setText("O(n)");

                textView = (TextView) view.findViewById(R.id.bigo_average);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_worst);
                textView.setText(Html.fromHtml("O(n<sup><small>2</small></sup>)"));

                textView = (TextView) view.findViewById(R.id.bigo_memory);
                textView.setText("O(1)");

                textView = (TextView)view.findViewById(R.id.bigo_stable);
                textView.setText("yes");
                break;
        }
        return view;
    }
}
