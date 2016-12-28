package spring.model;

/**
 * Created by Саша on 22.12.2016.
 */
public class LastId {
    public int getlastId() {
        return lastId;
    }

    public void setlastId(int lastId) {
        this.lastId = lastId;
    }

    public LastId(int lastid) {
        this.lastId = lastid;
    }

    private int lastId;
}
