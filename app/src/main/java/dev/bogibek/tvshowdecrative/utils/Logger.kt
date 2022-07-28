package dev.bogibek.tvshowdecrative.utils

import android.util.Log
import dev.bogibek.tvshowdecrative.data.network.Server

class Logger {
    companion object {
        fun d(TAG: String, msg: String) {
            if (Server.IS_TESTER) Log.d(TAG, msg)
        }

        fun i(TAG: String, msg: String) {
            if (Server.IS_TESTER) Log.i(TAG, msg)
        }

        fun v(TAG: String, msg: String) {
            if (Server.IS_TESTER) Log.v(TAG, msg)
        }

        fun e(TAG: String, msg: String) {
            if (Server.IS_TESTER) Log.e(TAG, msg)
        }
    }
}