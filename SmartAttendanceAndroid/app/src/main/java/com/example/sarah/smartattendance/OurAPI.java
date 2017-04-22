package com.example.sarah.smartattendance;

import java.util.List;

import Models.Attendance;
import Models.Beacon;
import Models.Room;
import Models.Tutorial;
import Models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface OurAPI {

    //users
    @GET("/users/{user_id}")
    void getUser(@Path("user_id") String id, Callback<User> callback);

    @GET("/users/")
    void getUsers(Callback<List<User>> callback);
    @GET("/users/{username}/tutorial/")
    void getActiveTutorial(@Path("username") String id, Callback<User> callback);

    @GET("/users/{username}/tutorials")
    void getAllTutorials(@Path("username") String id, Callback<List<Tutorial>> callback);

    @GET("/users/{username}/tutorials/{name}/attendances")
    void getTutAttendances(@Path("username") String username, @Path("name") String name, Callback<List<Attendance>> callback);

    //tutorials

    @FormUrlEncoded
    @PUT("/tutorials/{tutorial_id}")
    void updateTutorial(@Path("room_id") String id, @Field("tutorial[name]") String name,
    @Field("tutorial[isActive]") boolean isActive,
            Callback<Tutorial> callback);

    @GET("/tutorials/{name}/room")
    void findRoom(@Path("name") String name, Callback<Room> callback);

    @GET("/tutorials/{name}/users")
    void getAllStudents(@Path("name") String name, Callback<List<User>> callback);

    @GET("/tutorials/{name}/attendances")
    void getAllAttendances(@Path("name") String name, Callback<List<User>> callback);
    //room

    @GET("/rooms/{name}/")
    void getRoom(@Path("name") String name, Callback<Room> callback);

    @GET("/rooms/{name}/beacons")
    void getBeacons(@Path("name") String name, Callback<List<Beacon>> callback);

    @FormUrlEncoded
    @PUT("/attendances/{id}")
    void updateAttendance(@Path("id") String id, @Field("attendance[attended]") String attended, Callback<Attendance> callback);

    @FormUrlEncoded
    @POST ("/attendances")
    void createAttendance(@Field("attendance[user_id]") String id, @Field("attendance[tutorial_id]") String tutorialId, @Field("attendance[attended]") String attended, @Field("attendance[tut_date]") String date, Callback<Attendance> callback);
}
