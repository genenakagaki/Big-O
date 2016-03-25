package gene.com.bigo.sort.model;

/**
 * Created by Gene on 2/27/2016.
 */
public class Data {

    private int value;
    private boolean finalPos = false;
    private boolean pointed  = false;

    public Data() {}

    public Data(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isFinalPos() {
        return finalPos;
    }

    public void setFinalPos(boolean finalPos) {
        this.finalPos = finalPos;
    }

    public boolean isPointed() {
        return pointed;
    }

    public void setPointed(boolean pointed) {
        this.pointed = pointed;
    }

}
