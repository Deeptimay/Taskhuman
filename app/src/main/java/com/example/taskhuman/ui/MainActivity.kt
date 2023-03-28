package com.example.taskhuman.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskhuman.R
import com.example.taskhuman.adapter.RecyclerSwipeViewAdapter
import com.example.taskhuman.databinding.ActivityDemoBinding
import com.example.taskhuman.swipeCreator.SwipeCreatorBase
import com.example.taskhuman.viewModels.TaskHumanViewModel
import com.github.ygngy.multiswipe.MultiSwipeListener
import com.github.ygngy.multiswipe.SwipeDirection
import com.github.ygngy.multiswipe.multiSwiping
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDemoBinding
    private lateinit var viewModel: TaskHumanViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TaskHumanViewModel::class.java]

//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.getTopicListResponseMutableLivedata().observe(this) {
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this@MainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = RecyclerSwipeViewAdapter(this@MainActivity, it.skills)
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

        viewModel.getListResults()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }

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
}