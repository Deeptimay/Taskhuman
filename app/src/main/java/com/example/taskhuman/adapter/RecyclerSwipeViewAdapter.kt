package com.example.taskhuman.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskhuman.dataModels.OverlapImageModel
import com.example.taskhuman.dataModels.Skill
import com.example.taskhuman.databinding.SingleRowItemBinding
import com.example.taskhuman.swipeCreator.DefaultSwipeCreator
import com.example.taskhuman.utils.Utils
import com.github.ygngy.multiswipe.LeftSwipeList
import com.github.ygngy.multiswipe.MultiSwipe
import com.github.ygngy.multiswipe.RightSwipeList
import com.mindinventory.overlaprecylcerview.decoration.OverlapRecyclerViewDecoration

/**
 * A sample [RecyclerView] adapter used to show demo lists.
 */
class RecyclerSwipeViewAdapter(context: Context, private val dataSet: List<Skill>) :
    RecyclerView.Adapter<RecyclerSwipeViewAdapter.ViewHolder>() {

    //val sc = SwipeCreator(context, true) // Custom Swipe Creation
    val sc = DefaultSwipeCreator(context, true) // Simple and Default Swipe Creation

    //------limit number of items to be overlapped
    private val overlapLimit = 5

    //------set value of item overlapping in percentage between 0 to 100
    private val overlapWidthInPercentage = -50

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
//    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(view), MultiSwipe {
    inner class ViewHolder(private val binding: SingleRowItemBinding) :
        RecyclerView.ViewHolder(binding.root), MultiSwipe {

        // Hold left and right swipe lists and reuse them
        // Prevent recreating them when there is no need to create it
        // Only recreate swipe lists when you need to change a swipe
        private var mLeftSwipeList: LeftSwipeList? = null
        private var mRightSwipeList: RightSwipeList? = null

        var item: Skill? = null
            private set

        fun bind(listItem: Skill) {
            this.item = listItem
//            imageView.visibility = if (listItem.isFavorite) View.VISIBLE else View.GONE
            binding.tvSkillTitle.text = listItem.displayTileName
            binding.tvDateTime.text = listItem.availability?.startTime?.let {
                Utils.convertTimeStampToDateString(
                    it
                )
            }
            try {
                binding.ivColorDot.setColorFilter(Color.parseColor(listItem.availability?.color))
            } catch (exp: Exception) {
            }

            try {
                //------set item decoration for item overlapping
                binding.rvImageList.addItemDecoration(
                    OverlapRecyclerViewDecoration(
                        overlapLimit,
                        overlapWidthInPercentage
                    )
                )
                val recyclerImageOverLapAdapter =
                    RecyclerImageOverLapAdapter(overlapLimit, overlapWidthInPercentage)
                binding.rvImageList.adapter = recyclerImageOverLapAdapter
                val overlapImageModel = ArrayList<OverlapImageModel>()
                for (i in listItem.providerInfo!!)
                    i.profileImage?.let { OverlapImageModel(it) }?.let { overlapImageModel.add(it) }

                recyclerImageOverLapAdapter.addAll(overlapImageModel)
                recyclerImageOverLapAdapter.notifyDataSetChanged()
            } catch (exp: Exception) {
            }

//            binding.rvImageList.text = listItem.displayTileName
//            view.setOnClickListener {
////                onClick(listItem.tileName, listItem.dictionaryName)
//            }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
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
