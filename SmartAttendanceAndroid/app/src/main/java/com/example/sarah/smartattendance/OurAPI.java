package com.example.sarah.smartattendance;

import Models.Beacon;
import Models.Room;
import Models.Tutorial;
import Models.User;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OurAPI {

    //users

    @GET("/users/{user_id}/tutorials/")
    void getActiveTutorial(@Path("user_id") String id, Callback<User> callbWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWack);

    @GET("/users/{user_id}/tutorials")
    void getAllTutorials(@Path("user_id") String id, Callback<User> callback);

    //tutorials

    @FormUrlEncoded
    @PUT("/tutorials/{room_id}")
    void updateTutorial(@Path("room_id") String id, @Field("tutorial[name]") String name,
    @Field("tutorial[isActive]") boolean isActive,
            Callback<Tutorial> callback);

    @GET("/tutorials/{room_id}/room/{name}")
    void findRoom(@Path("room_id") String id, @Path("name") String name, Callback<Tutorial> callback);

    @GET("/tutorials/{room_id}/users")
    void getAllStudents(@Path("room_id") String id, Callback<User> callback);

    //room

    @GET("/rooms/{name}/")
    void getRoom(@Path("name") String name, Callback<Room> callback);

    @GET("/rooms/{name}/beacons")
    void getBeacons(@Path("name") String name, Callback<Beacon> callback);

//    @FormUrlEncoded
//    @PUT("/attendances/{}")
//    void updateAttendance()
}
