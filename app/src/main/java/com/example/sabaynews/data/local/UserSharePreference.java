package com.example.sabaynews.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharePreference {
    private static final String accessToken= "AccessToken";
    private static final String refreshToken= "refreshToken";
    private  static final String accesKey ="AccessKey";
    public static  void setToken(String token, String refresh, Context context)
    {
        SharedPreferences preferences =context.getSharedPreferences(accesKey,0);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString(accessToken,token);
        editor.putString(refreshToken,refresh);
        editor.apply();
    }
    public static String getAccessToken(Context context){
        SharedPreferences preferences=context.getSharedPreferences(accesKey,0);
        return preferences.getString(accessToken,"");
    }
    public static String getRefreshToken(Context context){
        SharedPreferences preferences=context.getSharedPreferences(accesKey,0);
        return preferences.getString(refreshToken,"");
    }
    public static  void  removeToken( Context context)
    {
        SharedPreferences preferences=context.getSharedPreferences(accesKey,0);
        SharedPreferences.Editor editor= preferences.edit();
        editor.remove(accessToken);
        editor.remove(refreshToken);
        editor.apply();
    }

}
