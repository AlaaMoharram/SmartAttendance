package Models;

/**
 * Created by sarah on 4/6/2017.
 */
public class Tutorial {
    String name;
    boolean isActive;
    Integer room_id;
    Integer tutorial_id;


    public Integer getTutorial_id() {
        return tutorial_id;
    }

    public void setTutorial_id(Integer tutorial_id) {
        this.tutorial_id = tutorial_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
