package gene.com.bigo.sort.controller;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import gene.com.bigo.R;
import gene.com.bigo.sort.model.sort.BubbleSort;
import gene.com.bigo.sort.model.sort.InsertionSort;
import gene.com.bigo.sort.model.sort.MergeSort;
import gene.com.bigo.sort.model.sort.QuickSort;
import gene.com.bigo.sort.model.sort.SelectionSort;
import gene.com.bigo.sort.model.SortingStep;
import gene.com.bigo.sort.view.Graph;

/**
 * Created by Gene on 2/27/2016.
 */
public class SortGraphFragment extends Fragment {

    private static final String TAG = SortGraphFragment.class.getSimpleName();
    private static boolean D = Log.isLoggable(TAG, Log.DEBUG);

    private static final String SORTING_KEY = "sorting_algorithm";
    private int mSortingAlgorithm;

    private Graph mGraph;
    private Button mPrevButton;
    private Button mNextButton;
    private Button mPlayButton;

    private List<SortingStep> mSortingSteps;
    private int mCurrentStep = 0;

    private Handler mHandler;
    private Runnable mHandlerAnimator = new Runnable() {

        int interval = 200;

        @Override
        public void run() {
            mCurrentStep++;

            if (mCurrentStep >= mSortingSteps.size()) {
                mHandler.removeCallbacks(mHandlerAnimator);
                mPlayButton.setText(R.string.reset_steps);
            } else {
                mGraph.setList(mSortingSteps.get(mCurrentStep).getDataList());
                mGraph.invalidate();

                mHandler.postDelayed(mHandlerAnimator, interval);
            }
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }
    };

    private void startAnimation() {mHandlerAnimator.run();}
    private void stopAnimation() {mHandler.removeCallbacks(mHandlerAnimator);}

    public static SortGraphFragment newInstance(int sortingAlgorithm) {
        Log.d(TAG, "newInstance");
        Bundle args = new Bundle();
        args.putInt(SORTING_KEY, sortingAlgorithm);
        SortGraphFragment fragment = new SortGraphFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_sort_graph, container, false);
        mGraph = (Graph) view.findViewById(R.id.graph);
        mPrevButton = (Button) view.findViewById(R.id.prev_step);
        mNextButton = (Button) view.findViewById(R.id.next_step);
        mPlayButton = (Button) view.findViewById(R.id.play_steps);

        mSortingAlgorithm = getArguments().getInt(SORTING_KEY);

        switch (mSortingAlgorithm) {
            case SortActivity.SELECTION_SORT:
                Log.d(TAG, "    Selection Sort");
                mSortingSteps = new SelectionSort(mGraph.getList()).getSortingSteps();
                break;
            case SortActivity.INSERTION_SORT:
                Log.d(TAG, "    Insertion Sort");
                mSortingSteps = new InsertionSort(mGraph.getList()).getSortingSteps();
                break;
            case SortActivity.BUBBLE_SORT:
                Log.d(TAG, "    Bubble Sort");
                mSortingSteps = new BubbleSort(mGraph.getList()).getSortingSteps();
                break;
            case SortActivity.MERGE_SORT:
                Log.d(TAG, "    Merge Sort");
                mSortingSteps = new MergeSort(mGraph.getList()).getSortingSteps();
                break;
            case SortActivity.QUICK_SORT:
                Log.d(TAG, "    Quick Sort");
                mSortingSteps = new QuickSort(mGraph.getList()).getSortingSteps();
                break;
        }

        mGraph.setList(mSortingSteps.get(mCurrentStep).getDataList());

        mPrevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on prev button. \n" +
                        "   currentStep: "+ mCurrentStep);
                mCurrentStep --;
                if (mCurrentStep < 0) {
                    mCurrentStep++;
                }

                mGraph.setList(mSortingSteps.get(mCurrentStep).getDataList());
                mGraph.invalidate();
            }
         });

        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on next button. \n" +
                        "   currentStep: "+ mCurrentStep);
                mCurrentStep++;
                if (mCurrentStep >= mSortingSteps.size()) {
                    mCurrentStep --;
                }

                mGraph.setList(mSortingSteps.get(mCurrentStep).getDataList());
                mGraph.invalidate();
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mPlayButton.getText().toString().equals(getString(R.string.play_steps))) {
                    Log.d(TAG, "clicked on Play");
                    startAnimation();
                    mPlayButton.setText(R.string.stop_steps);
                } else if (mPlayButton.getText().toString().equals(getString(R.string.stop_steps))) {
                    Log.d(TAG, "clicked on Stop");
                    stopAnimation();
                    mPlayButton.setText(R.string.play_steps);
                } else if (mPlayButton.getText().toString().equals(getString(R.string.reset_steps))) {
                    Log.d(TAG, "clicked on Reset");
                    mCurrentStep = 0;
                    mGraph.setList(mSortingSteps.get(mCurrentStep).getDataList());
                    mGraph.invalidate();
                    mPlayButton.setText(R.string.play_steps);
                }
            }
        });
        return view;
    }


}