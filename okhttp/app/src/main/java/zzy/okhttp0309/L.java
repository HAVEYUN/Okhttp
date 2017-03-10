package zzy.okhttp0309;

import android.util.Log;

/**
 * Author: 张智远  PC:MZ
 * Time: 2017/3/9 14:54
 * Email: 826680069@qq.com
 * Instruction: please enter class instruction here
 */
public class L {
    private static final String TAG = "okhttp_info";
    private static boolean debug = true;

    public static void e(String msg) {
        if (debug)
            Log.i(TAG, msg);

    }
}
