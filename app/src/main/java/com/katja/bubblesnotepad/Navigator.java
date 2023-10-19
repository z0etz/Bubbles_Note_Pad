package com.katja.bubblesnotepad;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

public class Navigator {

        private Context context;

        public Navigator(Context context) {

            this.context = context;
        }

        public void navigateToActivity(Class<?> targetActivityClass) {
            Intent intent = new Intent(context, targetActivityClass);
            context.startActivity(intent);
        }
        public void navigateToActivityWithExtra(Class<?> targetActivityClass, String extraKey, Serializable extraValue) {
            Intent intent = new Intent(context, targetActivityClass);
            intent.putExtra(extraKey, extraValue);
            context.startActivity(intent);
    }
}
