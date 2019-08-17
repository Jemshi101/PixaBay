package com.thinkpalm.pixabay.ui.activity.search

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkpalm.pixabay.R
import com.thinkpalm.pixabay.databinding.ActivitySearchBinding
import com.thinkpalm.pixabay.network.models.photolist.PhotoListResponse
import com.thinkpalm.pixabay.ui.adapters.search.SearchGridAdapter
import com.thinkpalm.pixabay.ui.adapters.search.SearchListAdapter
import com.thinkpalm.pixabay.ui.core.BaseActivity

class SearchActivity : BaseActivity() {

    private var adapterList: SearchListAdapter? = null
    private var adapterGrid: SearchGridAdapter? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        setSupportActionBar(binding.toolbar.toolbar)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.viewModel = viewModel

        binding.handler = SearchHandler(object : SearchHandler.SearchHandlerListener {})

        initViews()

        supportActionBar?.let {
            //            setTitle("", R.color.colorPrimary)
            it.setTitle("")
//            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setHomeButtonEnabled(false)
            it.setDisplayShowTitleEnabled(true)
        }

    }

    override fun onResume() {
        super.onResume()

        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            /*android.R.id.home -> {
                binding.toolbar.toolbar.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                finish();
                return true;
            }*/

            R.id.action_view -> {
                binding.root.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                if (viewModel.isGrid) {
                    item.icon =
                        ContextCompat.getDrawable(applicationContext, R.drawable.ic_action_grid)
                    setListLayout()
                } else {
                    item.icon =
                        ContextCompat.getDrawable(applicationContext, R.drawable.ic_action_list)
                    setGridLayout()
                }
                viewModel.isGrid = !viewModel.isGrid
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        setProgressVisibilityStatus(viewModel.progressStatus)
        setMessageBox(binding.root, viewModel.showMessage)


        binding.lytBase.setPadding(0, (getActionBarHeight() + getStatusBarHeight()).toInt(), 0, 0)

        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.orientation = GridLayoutManager.VERTICAL

        binding.rvSearch.layoutManager = linearLayoutManager


    }

    private fun setListLayout() {
        binding.rvSearch.layoutManager = linearLayoutManager
    }

    private fun setGridLayout() {
        binding.rvSearch.layoutManager = gridLayoutManager
    }

    private fun getData() {

        val liveData = viewModel.fetchPhotoList()
        liveData.observe(this,
            Observer<PhotoListResponse?> {
                it?.let {
                    liveData.removeObservers(this)
                    viewModel.photoListResponse = it
                    if (viewModel.isGrid)
                        populateGrid()
                    else
                        populateList()
                }
            })

    }

    private fun populateList() {
        adapterList?.let {
            it.photoListResponse = viewModel.photoListResponse
            it.notifyDataSetChanged()
        } ?: kotlin.run {
            adapterList = SearchListAdapter(
                this,
                viewModel.photoListResponse,
                object : SearchListAdapter.SearchListAdapterListener {
                    override fun onRequestNextPage(isLoadMore: Boolean, currentPageNumber: Int) {
                    }

                    override fun onRefresh() {
                    }

                    override fun onSwipeRefreshingChange(isSwipeRefreshing: Boolean) {
                    }

                    override fun onSnackBarShow(message: String) {
                    }
                })
            binding.rvSearch.adapter = adapterList
        }
    }

    private fun populateGrid() {
        adapterGrid?.let {
            it.photoListResponse = viewModel.photoListResponse
            it.notifyDataSetChanged()
        } ?: kotlin.run {
            adapterGrid = SearchGridAdapter(
                this,
                viewModel.photoListResponse,
                object : SearchGridAdapter.SearchGridAdapterListener {
                    override fun onRequestNextPage(isLoadMore: Boolean, currentPageNumber: Int) {
                    }

                    override fun onRefresh() {
                    }

                    override fun onSwipeRefreshingChange(isSwipeRefreshing: Boolean) {
                    }

                    override fun onSnackBarShow(message: String) {
                    }
                })
            binding.rvSearch.adapter = adapterGrid
        }
    }
}
