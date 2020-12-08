package com.example.propertylisting.utility;

import android.content.Context;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class Utils
{
    private Context context;

    public Utils(Context context)
    {
        this.context = context;
    }

    public static void applyOnClickEffect(View ...views)
    {
        for(View view : views)
        {
            PushDownAnim.setPushDownAnimTo(view)
                    .setScale(PushDownAnim.MODE_STATIC_DP, 8)
                    .setInterpolatorPush(PushDownAnim.DEFAULT_INTERPOLATOR)
                    .setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR);
        }
    }
}