package Models;

/**
 * Created by sarah on 4/6/2017.
 */
public class Attendance {
    Integer user_id;
    Integer tutorial_id;
    boolean attended;
    String tut_date;

    public String getTut_time() {
        return tut_time;
    }

    public void setTut_time(String tut_time) {
        this.tut_time = tut_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTutorial_id() {
        return tutorial_id;
    }

    public void setTutorial_id(Integer tutorial_id) {
        this.tutorial_id = tutorial_id;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public String getTut_date() {
        return tut_date;
    }

    public void setTut_date(String tut_date) {
        this.tut_date = tut_date;
    }

    String tut_time;

}
