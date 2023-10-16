package com.katja.bubblesnotepad;
import android.content.Context;
import android.content.Intent;

public class Navigator {

        private Context context;

        public Navigator(Context context) {
            this.context = context;
        }

        public void navigateToActivity(Class<?> targetActivityClass) {
            Intent intent = new Intent(context, targetActivityClass);
            context.startActivity(intent);
        }
}
