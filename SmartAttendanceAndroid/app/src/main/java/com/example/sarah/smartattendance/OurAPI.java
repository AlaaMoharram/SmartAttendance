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

    @GET("/users/{username}")
    void getUser(@Path("username") String id, Callback<User> callback);

    @GET("/users/")
    void getUsers(Callback<List<User>> callback);

    @GET("/users/{username}/activeTutorial/")
    void getActiveTutorial(@Path("username") String id, Callback<Tutorial> callback);

    @GET("/users/{username}/allTutorials")
    void getAllTutorials(@Path("username") String id, Callback<List<Tutorial>> callback);

    @GET("/users/{username}/tutorials/{name}/attendances")
    void getTutAttendances(@Path("username") String username, @Path("name") String name, Callback<List<Attendance>> callback);

    //tutorials

    @FormUrlEncoded
    @PUT("/tutorials/{name}/updateStatus")
    void updateTutorialStatus(@Path("name") String tutorialName, @Field("isActive") boolean isActive,
            Callback<Tutorial> callback);

    @GET("/tutorials/{name}/room")
    void findRoom(@Path("name") String name, Callback<Room> callback);

    @GET("/tutorials/{name}/students")
    void getAllStudents(@Path("name") String name, Callback<List<User>> callback);

    @GET("/tutorials/{name}/attendances")
    void getAllAttendances(@Path("name") String name, Callback<List<Attendance>> callback);

    @FormUrlEncoded
    @PUT("/tutorials/{name}/updateRoom")
    void updateTutorialRoom(@Path("name") String tutorialName, @Field("room_id") String roomName,
                            Callback<Room> callback);


    //room
    @GET("/rooms")
    void getAllRooms(Callback<List<Room>> callback);

    @GET("/rooms/{name}/beacons")
    void getBeacons(@Path("name") String name, Callback<List<Beacon>> callback);

    //attendance

    @FormUrlEncoded
    @PUT("/attendances/{id}")
    void updateAttendance(@Path("id") String id, @Field("attended") String attended, Callback<Attendance> callback);

    @FormUrlEncoded
    @POST ("/attendances")
    void createAttendance(@Field("user_id") String id, @Field("tutorial_id") String tutorialId, @Field("attended") String attended, @Field("tut_date") String date, Callback<Attendance> callback);
}
