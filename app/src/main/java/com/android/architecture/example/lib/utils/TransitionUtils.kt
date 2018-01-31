package com.android.architecture.example.lib.utils

import android.app.Activity
import android.content.Context
import com.android.architecture.example.R


object TransitionUtils {

    /**
     * Explicitly set a transition after starting an activity.
     *
     * @param context The activity that started the new intent.
     * @param transition A pair of animation ids, first is the enter animation, second is the exit animation.
     */
    fun transition(context: Context, transition: Pair<Int, Int>) {
        if (context !is Activity) {
            return
        }
        context.overridePendingTransition(transition.first, transition.second)
    }

    fun enter(): Pair<Int, Int> {
        return Pair(R.anim.slide_in_right, R.anim.zoom_out)
    }

    fun exit(): Pair<Int, Int> {
        return Pair(R.anim.zoom_in, R.anim.slide_out_right)
    }

    fun slideInUp(): Pair<Int, Int> {
        return Pair(R.anim.slide_in_up, R.anim.stay)
    }

    fun slideOutDown(): Pair<Int, Int> {
        return Pair(R.anim.stay, R.anim.slide_out_down)
    }

    fun fadeIn(): Pair<Int, Int> {
        return Pair(R.anim.fade_in_full, R.anim.stay)
    }

    fun fadeOut(): Pair<Int, Int> {
        return Pair(R.anim.stay, R.anim.fade_out_full)
    }

}