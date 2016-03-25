package gene.com.bigo.sort.model.sort;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;
import gene.com.bigo.utils.Range;

/**
 * Created by Gene on 3/2/2016.
 */
public class MergeSort extends SortingAlgorithm {

    private static final String TAG = MergeSort.class.getSimpleName();

    public MergeSort(List<Data> listToSort) {
        super(listToSort);
    }

    @Override
    public void sort() {
        Log.d(TAG, "sort");
        sortingSteps.add(new SortingStep(listToSort));

        // list of range of lists
        List<Range> rangeList = new ArrayList<>();

        for (int i = 0; i < listToSort.size(); i++) {
            rangeList.add(new Range(i, i + 1));
        }

        int index = 0;
        while (rangeList.size() > 1) {
            Log.d(TAG, "sort index: "+ index);
            if (index+1 < rangeList.size()) {
                Range left  = rangeList.remove(index);
                Range right = rangeList.remove(index);
                rangeList.add(index, merge(left, right));
                index++;
            } else {
                index = 0;
            }
        }
    }

    private Range merge(Range left, Range right) {
        Log.d(TAG, "merge leftEndIndex: "+ left.getEndIndex() +" rightEndIndex: "+ right.getEndIndex());
        int leftIndex  = left.getStartIndex();
        int rightIndex = right.getStartIndex();
        int listIndex = leftIndex;
        while (leftIndex < left.getEndIndex() && rightIndex < right.getEndIndex()) {
            Log.d(TAG, "merge leftIndex: " + leftIndex + " rightIndex: " + rightIndex);
            listToSort.get(leftIndex).setPointed(true);
            listToSort.get(rightIndex).setPointed(true);
            sortingSteps.add(new SortingStep(listToSort));

            if (listToSort.get(leftIndex).getValue() < listToSort.get(rightIndex).getValue()) {
                listToSort.add(listIndex, listToSort.remove(leftIndex));
            } else if (listToSort.get(rightIndex).getValue() < listToSort.get(leftIndex).getValue()) {
                listToSort.add(listIndex, listToSort.remove(rightIndex));
                left.setEndIndex(left.getEndIndex()+1);
                rightIndex++;
            }
            listToSort.get(listIndex).setFinalPos(true);
            listToSort.get(listIndex).setPointed(false);
            leftIndex++;
            listIndex++;
        }

        while (leftIndex < left.getEndIndex()) {
            Data d = listToSort.get(leftIndex);
            d.setPointed(false);
            d.setFinalPos(true);
            leftIndex++;
            listIndex++;
        }

        while (rightIndex < right.getEndIndex()) {
            Data d = listToSort.get(rightIndex);
            d.setPointed(false);
            d.setFinalPos(true);
            rightIndex++;
            listIndex++;
        }

        sortingSteps.add(new SortingStep(listToSort));

        return new Range(left.getStartIndex(), listIndex);
    }

//    private List<Data> mergeSort(List<Data> list) {
//        if (list.size() < 2) {
//            return list;
//        }
//
//        List<Data> left  = new ArrayList<>();
//        List<Data> right = new ArrayList<>();
//
//        boolean odd = true;
//        for (int i = 0; i < list.size(); i++) {
//            if (odd) {
//                left.add(list.get(i));
//                odd = false;
//            } else {
//                right.add(list.get(i));
//                odd = true;
//            }
//        }
//
//        left  = mergeSort(left);
//        right = mergeSort(right);
//
//        return merge(left, right);
//    }

//    private List<Data> merge(List<Data> left, List<Data> right) {
//        List<Data> list = new ArrayList<>();
//
//        while (list.size() < left.size() + right.size()) {
//            if (left.get(0) != null)  left.get(0).setPointed(true);
//            if (right.get(0) != null) right.get(0).setPointed(true);
//
//            sortingSteps.add(new SortingStep(list));
//
//            if (left.get(0) == null) {
//                list.add(right.get(0));
//            } else if (right.get(0) == null) {
//                list.add(left.get(0));
//            } else if (left.get(0).getValue() < right.get(0).getValue()) {
//                list.add(left.remove(0));
//            } else {
//                list.add(right.remove(0));
//            }
//        }
//
//        return list;
//    }
}
