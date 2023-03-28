package com.example.taskhuman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskhuman.adapter.RecyclerSwipeViewAdapter
import com.example.taskhuman.databinding.FragmentDiscoverBinding
import com.example.taskhuman.swipeCreator.SwipeCreatorBase
import com.example.taskhuman.viewModels.TaskHumanViewModel
import com.github.ygngy.multiswipe.MultiSwipeListener
import com.github.ygngy.multiswipe.SwipeDirection
import com.github.ygngy.multiswipe.multiSwiping
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class DiscoverFragment : Fragment() {
    private lateinit var _binding: FragmentDiscoverBinding
    private lateinit var viewModel: TaskHumanViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[TaskHumanViewModel::class.java]

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopicListResponseMutableLivedata().observe(viewLifecycleOwner) {
            binding.loadingLayout.containerShimmer.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            hideLoadingState()
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@DiscoverFragment.requireContext())
                adapter =
                    RecyclerSwipeViewAdapter(this@DiscoverFragment.requireContext(), it.skills)
            }
        }


        binding.recyclerView.multiSwiping(object : MultiSwipeListener {

            override fun swiping(direction: SwipeDirection, swipeListSize: Int) {
                // here i hide FAB when user is swiping actively
//                if (direction == SwipeDirection.END) fab.hide() else fab.show()
            }

            override fun onSwipeDone(swipeId: Int, data: Any?) {
                // holder has reacted to swipeDone event and has returned data
                // to this method. I returned viewHolder itself from onSwipeDone at viewHolder
                val holder = data as RecyclerSwipeViewAdapter.ViewHolder
                when (swipeId) {
                    SwipeCreatorBase.SWIPE_TO_LIKE_ID -> toggleLike(holder)
                }
            }

        })
        binding.loadingLayout.containerShimmer.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        displayLoadingState()
        viewModel.getListResults()
    }

    private fun toggleLike(holder: RecyclerSwipeViewAdapter.ViewHolder) {
        holder.item?.let { item ->
            if (item.isFavorite) {
                item.tileName?.let { viewModel.removeFavResults(it) }
            } else {
                item.dictionaryName?.let {
                    item.tileName?.let { it1 ->
                        viewModel.addFavResults(
                            it1,
                            it
                        )
                    }
                }
            }
            item.toggleLike()
            binding.recyclerView.adapter?.notifyItemChanged(holder.adapterPosition)
        }
    }

    fun displayLoadingState() {
        binding.loadingLayout.containerShimmer.showShimmer(true)
        binding.loadingLayout.containerShimmer.startShimmer()
    }

    fun hideLoadingState() {
        binding.loadingLayout.containerShimmer.hideShimmer()
        binding.loadingLayout.containerShimmer.stopShimmer()
    }
}