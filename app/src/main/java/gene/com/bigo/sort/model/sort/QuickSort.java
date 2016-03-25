package gene.com.bigo.sort.model.sort;

import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;

/**
 * Created by Gene on 3/5/2016.
 */
public class QuickSort extends SortingAlgorithm {

    private static final String TAG = QuickSort.class.getSimpleName();

    public QuickSort(List<Data> listToSort) {
        super(listToSort);
    }

    @Override
    public void sort() {
        sortingSteps.add(new SortingStep(listToSort));
        quicksort(0, listToSort.size());
    }

    private void quicksort(int left, int right) {
        if (left < right){
            int pivot = partition(left, right);
            quicksort(left, pivot);
            quicksort(pivot+1, right);
        }
    }

    private int partition(int leftIndex, int rightIndex) {
        int pivotIndex = rightIndex-1;
        int pivotValue = listToSort.get(pivotIndex).getValue();
        int swapIndex = leftIndex;

        Data pivot = listToSort.get(pivotIndex);
        pivot.setPointed(true);

        for (int i = leftIndex; i < rightIndex; i++) {
            Data d = listToSort.get(i);
            d.setPointed(true);
            sortingSteps.add(new SortingStep(listToSort));

            if (listToSort.get(i).getValue() < pivotValue) {
                swap(swapIndex, i);
                swapIndex ++;
            }

            d.setPointed(false);
        }

        swap(swapIndex, pivotIndex);
        pivot.setPointed(false);
        pivot.setFinalPos(true);
        sortingSteps.add(new SortingStep(listToSort));
        return swapIndex;
    }

    private void swap(int index1, int index2) {
        Data temp = listToSort.get(index1);
        listToSort.set(index1, listToSort.get(index2));
        listToSort.set(index2, temp);
    }
}
