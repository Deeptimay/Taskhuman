package com.example.taskhuman.swipeCreator

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.taskhuman.R
import com.github.ygngy.multiswipe.Swipe
import com.github.ygngy.multiswipe.SwipeIcon
import com.github.ygngy.multiswipe.SwipeLabel
import com.github.ygngy.multiswipe.SwipeTheme

/**
 * A sample class used to create customized swipes in details.
 * For simple (or default) swipe creation see [DefaultSwipeCreator] class.
 *
 * @constructor
 * @param context Used to get resources from.
 * @param withLabel if true labels will be drawn otherwise only icons will be drawn
 */
class SwipeCreator(context: Context, withLabel: Boolean) : SwipeCreatorBase(context) {

    override val likedSwipe: Swipe
    override val unlikedSwipe: Swipe


    /**
     * A sample method used to prevent code repeat when creating [SwipeTheme].
     *
     * @receiver [SwipeTheme] To extend and change its icon and text.
     * @param drawableRes Drawable resource id of icon in [SwipeTheme].
     * @param stringRes String resource id of label in [SwipeTheme].
     */
    private fun SwipeTheme.extend(
        @DrawableRes drawableRes: Int,
        @StringRes stringRes: Int
    ): SwipeTheme = copy(
        icon = icon.copy(drawable = getDrawable(drawableRes)),
        label = label?.copy(text = getString(stringRes))
    )

    init {

        val shareLabel = if (withLabel)
            SwipeLabel(
                text = getString(R.string.share_label),
                textColor = getColor(R.color.swipe_text),
                textSize = getDimension(R.dimen.swipe_text_size),
                margin = getDimension(R.dimen.swipe_text_margin),
            )
        else null

        val shareIcon = SwipeIcon(
            drawable = getDrawable(R.drawable.ic_baseline_share_24),
            edgeHorMargin = getDimension(R.dimen.swipe_edge_margin),
            iconHorMargin = getDimension(R.dimen.swipe_icon_margin),
            tailHorMargin = getDimension(R.dimen.swipe_tail_margin)
        )

        // To prevent code repeat,
        // i use this theme as base theme and extend it for other themes
        val shareTheme = SwipeTheme(
            icon = shareIcon,
            label = shareLabel,
            backgroundColor = getColor(R.color.swipe_background),
            viewColor = getColor(R.color.view_background_color)
        )

        val shareAcceptTheme = shareTheme.copy(
            backgroundColor = getColor(R.color.swipe_accept_background),
            label = shareLabel?.copy(textColor = getColor(R.color.swipe_accept_text)),
            viewColor = getColor(R.color.view_background_accept_color)
        )

        val likedTheme = shareTheme.extend(
            R.drawable.ic_baseline_favorite_24,
            R.string.liked_label
        )

        val unlikedTheme = shareTheme.extend(
            R.drawable.ic_baseline_favorite_border_24,
            R.string.unliked_label
        )

        val likedAcceptTheme = shareAcceptTheme.extend(
            R.drawable.ic_baseline_favorite_border_24,
            R.string.unlike_label
        )

        val unlikedAcceptTheme = shareAcceptTheme.extend(
            R.drawable.ic_baseline_favorite_24,
            R.string.like_label
        )

        likedSwipe = Swipe(
            id = SWIPE_TO_LIKE_ID,
            activeTheme = likedTheme,
            acceptTheme = likedAcceptTheme,
            inactiveIcon = getDrawable(R.drawable.ic_disabled_favorite_24)
        )

        unlikedSwipe = Swipe(
            id = SWIPE_TO_LIKE_ID,
            activeTheme = unlikedTheme,
            acceptTheme = unlikedAcceptTheme,
            inactiveIcon = getDrawable(R.drawable.ic_disabled_favorite_border_24)
        )
    }
}