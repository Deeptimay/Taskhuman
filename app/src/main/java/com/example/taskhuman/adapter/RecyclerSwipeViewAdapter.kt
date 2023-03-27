package com.example.taskhuman.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskhuman.R
import com.example.taskhuman.dataModels.Skill
import com.example.taskhuman.swipeCreator.DefaultSwipeCreator
import com.github.ygngy.multiswipe.LeftSwipeList
import com.github.ygngy.multiswipe.MultiSwipe
import com.github.ygngy.multiswipe.RightSwipeList

/**
 * A sample [RecyclerView] adapter used to show demo lists.
 */
class RecyclerSwipeViewAdapter(
    context: Context,
    private val dataSet: List<Skill>,
    private val onClick: (String, String) -> Unit
) :
    RecyclerView.Adapter<RecyclerSwipeViewAdapter.ViewHolder>() {

    //val sc = SwipeCreator(context, true) // Custom Swipe Creation
    val sc = DefaultSwipeCreator(context, true) // Simple and Default Swipe Creation

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view), MultiSwipe {
        private val textView: TextView = view.findViewById(R.id.title)
        private val imageView: ImageView = view.findViewById(R.id.like)

        // Hold left and right swipe lists and reuse them
        // Prevent recreating them when there is no need to create it
        // Only recreate swipe lists when you need to change a swipe
        private var mLeftSwipeList: LeftSwipeList? = null
        private var mRightSwipeList: RightSwipeList? = null

        var item: Skill? = null
            private set

        fun bind(listItem: Skill) {
            this.item = listItem
            imageView.visibility = if (listItem.isFavorite) View.VISIBLE else View.GONE
            textView.text = listItem.displayTileName
            view.setOnClickListener {
                onClick(listItem.tileName, listItem.dictionaryName)
            }

            mRightSwipeList = RightSwipeList(
                sc.getLikeSwipe(listItem.isFavorite)
            )
        }

        // This will be called a lot so do NOT create any object here.
        override val leftSwipeList: LeftSwipeList?
            get() = mLeftSwipeList

        // This will be called a lot so do NOT create any object here.
        override val rightSwipeList: RightSwipeList?
            get() = mRightSwipeList

        // Here i can handle swipe and return some
        // data to main MultiSwipeListener (if there is one)
        // for this demo i simply return this viewHolder
        // to MultiSwipeListener
        override fun onSwipeDone(swipeId: Int): Any? = this
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
