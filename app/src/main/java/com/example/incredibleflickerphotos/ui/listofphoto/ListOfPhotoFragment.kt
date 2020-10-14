package com.example.incredibleflickerphotos.ui.listofphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incredibleflickerphotos.R
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import com.example.incredibleflickerphotos.extension.gone
import com.example.incredibleflickerphotos.extension.visible
import com.example.incredibleflickerphotos.flickerPhotoComponent
import kotlinx.android.synthetic.main.fragment_list_of_photos.*
import kotlinx.android.synthetic.main.progress_bar.*

/**
 * Handle view for List of photo
 *
 * @constructor Create empty List of photo fragment
 */
class ListOfPhotoFragment : Fragment() {

    lateinit var viewModel: ListOfPhotoViewModel

    lateinit var loadingStateObserver: Observer<Boolean>

    lateinit var listOfPhotoAdapter: ListOfPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flickerPhotoComponent?.inject(this)
        viewModel = ViewModelProviders.of(this).get(ListOfPhotoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list_of_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        initObserver()
        viewModel.getListOfPhotos()
    }

    /**
     * Initialize observer
     *
     */
    private fun initObserver() {
        loadingStateObserver()
        listOfPhotoObserver()
        failureEventObserver()
    }

    /**
     * Failure event observer
     *
     */
    private fun failureEventObserver() {
        viewModel.failureEvent.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    /**
     * List of photo observer
     *
     */
    private fun listOfPhotoObserver() {
        viewModel.listOfPhoto.observe(viewLifecycleOwner, Observer {
            rvListOfPhoto.visible()
            listOfPhotoAdapter.updateList(it as ArrayList<PhotoWithDiffSizeResponse>)
        })
    }

    /**
     * Set recycler view property, also create recycler view adapter
     *
     */
    private fun setRecyclerView() {
        rvListOfPhoto.layoutManager =
            GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
        listOfPhotoAdapter = ListOfPhotoAdapter(requireContext())
        rvListOfPhoto.adapter = listOfPhotoAdapter
    }

    /**
     * Loading state observer, handle the visibility of the progress bar and recycler view
     *
     */
    private fun loadingStateObserver() {
        loadingStateObserver = Observer {
            when (it) {
                true -> {
                    rvListOfPhoto.gone()
                    progress_bar_id.visible()
                }
                false -> {
                    rvListOfPhoto.visible()
                    progress_bar_id.gone()
                }
            }
        }
        viewModel.loadingState.observe(this, loadingStateObserver)
    }

    companion object {
        private var instance: ListOfPhotoFragment? = null

        /**
         * Get the instance of the fragment
         *
         */
        fun getInstance() = instance ?: ListOfPhotoFragment().also {
            instance = it
        }
    }
}