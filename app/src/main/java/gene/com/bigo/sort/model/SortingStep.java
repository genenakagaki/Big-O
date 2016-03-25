package gene.com.bigo.sort.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gene on 2/27/2016.
 */
public class SortingStep {

    private HashMap<Integer, Integer> valueMap    = new HashMap<>();
    private HashMap<Integer, Boolean> finalPosMap = new HashMap<>();
    private HashMap<Integer, Boolean> pointedMap  = new HashMap<>();

    public SortingStep(List<Data> list) {
        for (int i = 0; i < list.size(); i++) {
            Data data = list.get(i);
            if (data.isFinalPos()) {
                finalPosMap.put(i, true);
            } else {
                finalPosMap.put(i, false);
            }
            if (data.isPointed()) {
                pointedMap.put(i, true);
            } else {
                pointedMap.put(i, false);
            }
            valueMap.put(i, data.getValue());
        }
    }

    public List<Data> getDataList() {
        List<Data> result = new ArrayList<>();

        for (int i = 0; i < valueMap.size(); i++) {
            Data data = new Data(valueMap.get(i));
            if (finalPosMap.get(i)) {
                data.setFinalPos(true);
            }
            if (pointedMap.get(i)) {
                data.setPointed(true);
            }
            result.add(data);
        }

        return result;
    }
}
