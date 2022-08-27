package com.app.resaless.api;

import androidx.annotation.NonNull;

import com.app.resaless.item.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FoodApi {
    private final OkHttpClient client = new OkHttpClient();
    private final static String baseUrl = "https://food.madskill.ru/";
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void register(String email, String password, String login, RegisterCallback callback){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("login", login);
        } catch (JSONException e) {
            callback.onRegister(false, false);
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "auth/register")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onRegister(false, false);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                boolean isRegisterSuccess = response.code() != 400;
                callback.onRegister(true, isRegisterSuccess);
            }
        });
    }

    public interface RegisterCallback{
        void onRegister(boolean isConnectionSuccess, boolean isRegisterSuccess);
    }

    public void login(String email, String password, LoginCallback callback){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            callback.onLoginFailure();
        }
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "auth/login")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onConnectionFailure();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.code() == 200){
                    try {
                        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                        int token = jsonObject.getInt("token");
                        callback.onSuccessLogin(token);
                    } catch (JSONException e) {
                        callback.onLoginFailure();
                    }
                } else {
                    callback.onLoginFailure();
                }

            }
        });
    }

    public interface LoginCallback{
        void onConnectionFailure();
        void onLoginFailure();
        void onSuccessLogin(int token);
    }

    public void getDishes(DishesCallback dishesCallback){
       Request request = new Request.Builder()
               .url(baseUrl + "dishes?version=1.01")
               .get()
               .build();

       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(@NonNull Call call, @NonNull IOException e) {
               dishesCallback.onFailure();
           }

           @Override
           public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
               if (response.code() != 200){
                   dishesCallback.onFailure();
                   return;
               }
               try {
                   JSONArray jsonArray = new JSONArray(Objects.requireNonNull(response.body()).string());
                   int length = jsonArray.length();
                   ArrayList<FoodItem> foodItems = new ArrayList<>();
                   for (int i = 0; i < length; i++){
                       JSONObject object = jsonArray.getJSONObject(i);
                       String dishId = object.getString("dishId");
                       String category = object.getString("category");
                       String nameDish = object.getString("nameDish");
                       String price = object.getString("price");
                       String icon = "http://food.madskill.ru/up/images/" + object.getString("icon");
                       String version = object.getString("version");
                       FoodItem foodItem = new FoodItem(category, icon, nameDish, price);
                       foodItems.add(foodItem);
                   }
                   dishesCallback.onSuccess(foodItems);
               } catch (JSONException e) {
                   dishesCallback.onFailure();
               }


           }
       });
    }

    public interface DishesCallback{
        void onSuccess(ArrayList<FoodItem> dishes);
        void onFailure();
    }
}
