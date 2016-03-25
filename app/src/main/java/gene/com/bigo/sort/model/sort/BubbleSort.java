package gene.com.bigo.sort.model.sort;

import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;

/**
 * Created by Gene on 2/29/2016.
 */
public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(List<Data> listToSort) {
        super(listToSort);
    }

    @Override
    public void sort() {
        sortingSteps.add(new SortingStep(listToSort));

        int size = listToSort.size();

        for (int i = 0; i < size-1; i++) {
            listToSort.get(0).setPointed(true);

            int sortedIndex = size - 1 - i;

            for (int j = 0; j < sortedIndex; j++) {
                listToSort.get(j+1).setPointed(true);
                sortingSteps.add(new SortingStep(listToSort));

                if (listToSort.get(j).getValue() > listToSort.get(j+1).getValue()) {
                    swap(j, j+1);
                    sortingSteps.add(new SortingStep(listToSort));
                }

                listToSort.get(j).setPointed(false);
            }

            listToSort.get(sortedIndex).setPointed(false);
            listToSort.get(sortedIndex).setFinalPos(true);
            sortingSteps.add(new SortingStep(listToSort));
        }

        listToSort.get(0).setFinalPos(true);
    }

    private void swap(int index1, int index2) {
        Data temp = listToSort.get(index1);
        listToSort.set(index1, listToSort.get(index2));
        listToSort.set(index2, temp);
    }
}
