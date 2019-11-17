package com.example.incredibleflickerphotos.ui.listofphoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incredibleflickerphotos.IncredibleFlickerPhotosApplication
import com.example.incredibleflickerphotos.R
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse
import com.example.incredibleflickerphotos.extension.gone
import com.example.incredibleflickerphotos.extension.visible
import kotlinx.android.synthetic.main.fragment_list_of_photos.*
import kotlinx.android.synthetic.main.progress_bar.*

class ListOfPhotoFragment : Fragment() {

    lateinit var viewModel: ListOfPhotoViewModel

    lateinit var loadingStateObserver: Observer<Boolean>

    lateinit var listOfPhotoAdapter: ListOfPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IncredibleFlickerPhotosApplication.getInstance().flickerPhotoComponent.inject(this)
        viewModel = ViewModelProviders.of(this).get(ListOfPhotoViewModel::class.java)
        viewModel.getMetaDataOfPhoto()
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
        observeLoadingState()
        viewModel.getLiveDataOfListOfPhoto().observe(requireActivity(), Observer<ArrayList<PhotoWithDiffSizeResponse>> {
            rvListOfPhoto.visible()
            Log.i("Viewmodel", "onChange")

            listOfPhotoAdapter.updateList(it)
        })
    }

    private fun setRecyclerView() {
        rvListOfPhoto.layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
        listOfPhotoAdapter = ListOfPhotoAdapter(requireContext())
        rvListOfPhoto.adapter = listOfPhotoAdapter
    }

    private fun observeLoadingState() {
        loadingStateObserver = Observer {
            when(it) {
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
        viewModel.getLoadingState().observe(this, loadingStateObserver)
    }

    companion object {
        val instance = ListOfPhotoFragment()
    }
}