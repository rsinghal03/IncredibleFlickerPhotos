package com.example.incredibleflickerphotos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.incredibleflickerphotos.R
import com.example.incredibleflickerphotos.extension.add
import com.example.incredibleflickerphotos.ui.listofphoto.ListOfPhotoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(ListOfPhotoFragment.getInstance(), R.id.container, false)
    }
}
