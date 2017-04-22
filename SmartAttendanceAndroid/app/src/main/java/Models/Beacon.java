package Models;

/**
 * Created by sarah on 4/6/2017.
 */
public class Beacon {
    Integer uuid;

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    Integer room_id;
}
