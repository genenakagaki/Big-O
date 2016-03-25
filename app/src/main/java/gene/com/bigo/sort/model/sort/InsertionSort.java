package gene.com.bigo.sort.model.sort;

import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;

/**
 * Created by Gene on 3/1/2016.
 */
public class InsertionSort extends SortingAlgorithm {

    private static final String TAG = InsertionSort.class.getSimpleName();

    public InsertionSort(List<Data> listToSort) {
        super(listToSort);
    }

    @Override
    public void sort() {
        sortingSteps.add(new SortingStep(listToSort));

        listToSort.get(0).setPointed(true);
        sortingSteps.add(new SortingStep(listToSort));
        listToSort.get(0).setPointed(false);
        listToSort.get(0).setFinalPos(true);

        for (int i = 1; i < listToSort.size(); i++) {
            listToSort.get(i).setPointed(true);
            sortingSteps.add(new SortingStep(listToSort));
            findPosition(i);
        }

        sortingSteps.add(new SortingStep(listToSort));
    }

    private void findPosition(int index) {
        int value = listToSort.get(index).getValue();
        for (int i = index-1; i >= 0; i--) {
            listToSort.get(i).setPointed(true);
            sortingSteps.add(new SortingStep(listToSort));

            if (value > listToSort.get(i).getValue()) {
                listToSort.get(i).setPointed(false);
                sortingSteps.add(new SortingStep(listToSort));
                break;
            } else {
                listToSort.get(i).setPointed(false);
                swap(index, i);
                index = i;
                sortingSteps.add(new SortingStep(listToSort));
            }
        }
        listToSort.get(index).setPointed(false);
        listToSort.get(index).setFinalPos(true);
    }

    private void swap(int index1, int index2) {
        Data temp = listToSort.get(index1);
        listToSort.set(index1, listToSort.get(index2));
        listToSort.set(index2, temp);
    }
}
