package gene.com.bigo.sort.model.sort;

import java.util.LinkedList;
import java.util.List;

import gene.com.bigo.sort.model.Data;
import gene.com.bigo.sort.model.SortingStep;

/**
 * Created by Gene on 2/29/2016.
 */
public abstract class SortingAlgorithm {

    private static final String TAG = SortingAlgorithm.class.getSimpleName();

    protected List<Data> listToSort;
    protected List<SortingStep> sortingSteps = new LinkedList<>();

    public SortingAlgorithm(List<Data> listToSort) {
        this.listToSort = listToSort;
        sort();
    }

    public abstract void sort();
    public List<SortingStep> getSortingSteps() {
        return sortingSteps;
    }

}
