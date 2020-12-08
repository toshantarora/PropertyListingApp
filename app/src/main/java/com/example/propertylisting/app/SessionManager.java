package com.example.propertylisting.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.utility.Const;

import java.util.HashMap;

public class SessionManager
{
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Context context;

    private static final String SESSION_NAME = "property_listing";

    public SessionManager(Context context)
    {
        this.context = context;
        preferences = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createLoginSession(String username, String password)
    {
        try
        {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();
        }
        catch (Exception e) {}
    }

    public HashMap<String, String> getUserDetail()
    {
        HashMap<String , String> user = new HashMap<>();
        user.put("username", preferences.getString("username", Const.EMPTY_USERNAME));
        user.put("password", preferences.getString("password", Const.EMPTY_PASSWORD));
        return user;
    }

    public void logoutUser()
    {
        editor.clear();
        editor.commit();
    }

    public boolean ifSessionExists()
    {
        String username = getUserDetail().get("username");
        String password = getUserDetail().get("password");

        if(username.equals(Const.EMPTY_USERNAME) && password.equals(Const.EMPTY_PASSWORD)) {
            return false;
        } else {
            DatabaseHandler db = new DatabaseHandler(context);
            boolean check = db.ifUserExist(username, password, "password");
            if(check) {
                return true;
            } else {
                logoutUser();
                return false;
            }
        }
    }
}