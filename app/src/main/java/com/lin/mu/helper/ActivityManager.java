package com.lin.mu.helper;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by lin on 2016/8/2.
 * <p/>
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class ActivityManager {

    private static ActivityManager instance;

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    private static Stack<Activity> activityStack;

    private ActivityManager() {
        activityStack = new Stack<>();
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        //Log.d("Jerome","addActivity");
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        //Log.d("Jerome","removeActivity");
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (!activityStack.isEmpty()) {
            Activity activity = activityStack.lastElement();
            return activity;
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (!activityStack.isEmpty()) {
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
        } catch (Exception e) {
        }
    }
}
