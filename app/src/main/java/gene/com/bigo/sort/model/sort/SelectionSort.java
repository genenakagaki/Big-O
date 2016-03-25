package gene.com.bigo.sort.model.sort;

import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;

/**
 * Created by Gene on 2/26/2016.
 */
public class SelectionSort extends SortingAlgorithm {

    private static final String TAG = SelectionSort.class.getSimpleName();

    public SelectionSort(List<Data> listToSort) {
        super(listToSort);
    }

    public void sort() {
        sortingSteps.add(new SortingStep(listToSort));

        for (int i = 0; i < listToSort.size()-1; i++) {
            int minIndex = findMinIndex(listToSort, i);
            swap(listToSort, i, minIndex);

            listToSort.get(i).setFinalPos(true);
            listToSort.get(i).setPointed(false);
            sortingSteps.add(new SortingStep(listToSort));
        }

        listToSort.get(listToSort.size()-1).setFinalPos(true);
        sortingSteps.add(new SortingStep(listToSort));
    }

    // find index with min number from param: fromIndex
    private int findMinIndex(List<Data> list, int fromIndex) {
        int minIndex = fromIndex;
        int min = list.get(minIndex).getValue();
        list.get(minIndex).setPointed(true);
        sortingSteps.add(new SortingStep(list));

        for (int  i = fromIndex+1; i < list.size(); i++) {
            list.get(i).setPointed(true);
            sortingSteps.add(new SortingStep(list));

            if (list.get(i).getValue() < min) {
                list.get(minIndex).setPointed(false);

                min = list.get(i).getValue();
                minIndex = i;
            } else {
                list.get(i).setPointed(false);
            }
        }

        return minIndex;
    }

    private void swap(List<Data> list, int index1, int index2) {
        Data temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
