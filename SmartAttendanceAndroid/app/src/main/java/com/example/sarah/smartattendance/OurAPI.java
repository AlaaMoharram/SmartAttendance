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

    //session

    //users

    @GET("/users/{username}")
    void getUser(@Path("username") String id, Callback<User> callback);

    @GET("/users/")
    void getUsers(Callback<List<User>> callback);

    @GET("/users/{username}/activeTutorial/")
    void getActiveTutorial(@Path("username") String username, Callback<Tutorial> callback);

    @GET("/users/{username}/allTutorials")
    void getAllTutorials(@Path("username") String id, Callback<List<Tutorial>> callback);

    @GET("/users/{username}/tutorials/{name}/attendances")
    void getTutAttendances(@Path("username") String username, @Path("name") String name, Callback<List<Attendance>> callback);

    @GET("/users/{username}/tutorials/{name}/lastAttendance")
    void getLastAttendanceRecordForTutorial(@Path("username") String username, @Path("name") String tutorialName, Callback<Attendance> callback);
    //tutorials

    @FormUrlEncoded
    @PUT("/tutorials/{name}/update")
    void updateTutorialStatus(@Path("name") String tutorialName, @Field("isActive") boolean isActive,
            Callback<Tutorial> callback);

    @GET("/tutorials/{name}/room")
    void findRoom(@Path("name") String name, Callback<Room> callback);

    @GET("/tutorials/{name}/students")
    void getAllStudents(@Path("name") String name, Callback<List<User>> callback);

    @GET("/tutorials/{name}/attendances")
    void getAllAttendances(@Path("name") String name, Callback<List<Attendance>> callback);

    @FormUrlEncoded
    @PUT("/tutorials/{name}/update")
    void updateTutorialRoom(@Path("name") String tutorialName, @Field("room_id") String roomID,
                            Callback<Room> callback);
    @FormUrlEncoded
    @POST ("/tutorials/{name}/generateAttendances")
    void generateAttendances(@Path("name") String tutorialName, @Field("name") String tutName, Callback<Attendance> callback);

    @GET("/tutorials/{name}")
    void getTutorial(@Path("name") String tutorialName, Callback<Tutorial> callback);

    @FormUrlEncoded
    @PUT("/tutorials/{name}/endTime")
    void AddTutorialEndTime(@Path("name") String tutorialName, @Field("tut_end_time") String tutorialEndTime, Callback<Attendance> callback);

    //room
    @GET("/rooms")
    void getAllRooms(Callback<List<Room>> callback);

    @GET("/rooms/{id}/beacons")
    void getBeacons(@Path("id") String roomID, Callback<List<Beacon>> callback);

    //attendance

    @FormUrlEncoded
    @PUT("/attendances/{id}")
    void updateAttendance(@Path("id") String id, @Field("attended") boolean attended, Callback<Attendance> callback);

    @FormUrlEncoded
    @POST ("/attendances")
    void createAttendance(@Field("user_id") String id, @Field("tutorial_id") String tutorialId, @Field("attended") String attended, @Field("tut_date") String date, Callback<Attendance> callback);


}
