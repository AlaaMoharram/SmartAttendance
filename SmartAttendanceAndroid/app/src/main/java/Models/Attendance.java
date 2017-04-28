package Models;

/**
 * Created by sarah on 4/6/2017.
 */
public class Attendance {
    Integer user_id;
    Integer tutorial_id;
    boolean attended;
    String tut_date;
    Integer id;
    String tut_end_time;
    String tut_start_time;

    public String getTut_start_time() {
        return tut_start_time;
    }

    public void setTut_start_time(String tut_start_time) {
        this.tut_start_time = tut_start_time;
    }


    public String getTut_end_time() {
        return tut_end_time;
    }

    public void setTut_end_time(String tut_end_time) {
        this.tut_end_time = tut_end_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


}
