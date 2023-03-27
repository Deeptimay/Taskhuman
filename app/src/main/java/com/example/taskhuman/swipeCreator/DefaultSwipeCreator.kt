package com.example.taskhuman.swipeCreator

import android.content.Context
import androidx.annotation.StringRes
import com.example.taskhuman.R
import com.github.ygngy.multiswipe.Swipe

/**
 * This is a sample class for default and simple swipe creation.
 * For customized (or detailed) swipe creation see [SwipeCreator] class.
 *
 * @constructor
 * @param context Used to get resources from.
 * @param withLabel if true labels will be drawn otherwise only icons will be drawn
 */
class DefaultSwipeCreator(context: Context, private val withLabel: Boolean) :
    SwipeCreatorBase(context) {

    override val likedSwipe: Swipe = Swipe(
        context = context,
        id = SWIPE_TO_LIKE_ID,
        activeIcon = getDrawable(R.drawable.ic_baseline_favorite_24),
        activeLabel = getLabel(R.string.liked_label),
        acceptIcon = getDrawable(R.drawable.ic_baseline_favorite_border_24),
        acceptLabel = getLabel(R.string.unlike_label),
        inactiveIcon = getDrawable(R.drawable.ic_disabled_favorite_24)
    )

    override val unlikedSwipe: Swipe = Swipe(
        context = context,
        id = SWIPE_TO_LIKE_ID,
        activeIcon = getDrawable(R.drawable.ic_baseline_favorite_border_24),
        activeLabel = getLabel(R.string.unliked_label),
        acceptIcon = getDrawable(R.drawable.ic_baseline_favorite_24),
        acceptLabel = getLabel(R.string.like_label),
        inactiveIcon = getDrawable(R.drawable.ic_disabled_favorite_border_24)
    )

    private fun getLabel(@StringRes stringRes: Int) =
        if (withLabel) getString(stringRes) else null

}