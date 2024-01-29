package com.example.catphotos.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catphotos.data.remote.TheCatApiService
import com.example.catphotos.databinding.ActivityMainBinding
import com.example.catphotos.presentation.ui.adapters.CatsAdapter
import com.example.catphotos.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    val adapter by lazy { CatsAdapter() }
    @Inject lateinit var apiTheCatService : TheCatApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapterCats()
        getListOfImagesCat()

        mainViewModel.listaGatos.observe(this) { listUrlImagesCat ->
            adapter.updateListImagesCats( listUrlImagesCat )
        }
    }

    private fun setAdapterCats() {
        binding.rvImagesCats.adapter = adapter
        binding.rvImagesCats.layoutManager = GridLayoutManager(this@MainActivity, 2)
        setScrollEventOnRecyclerView()
    }

    private fun setScrollEventOnRecyclerView() {
        binding.rvImagesCats.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 ) {
                        val isPossibleDown = recyclerView.canScrollVertically(1)
                        if (!isPossibleDown) {
                            Toast.makeText(applicationContext, "Carregando + imagens", Toast.LENGTH_SHORT).show()
                            getListOfImagesCat()
                        }
                    }
                }
            }
        )
    }

    private fun getListOfImagesCat() {
        lifecycleScope.launch {
            mainViewModel.getImagesTheCatApi()
        }
    }
}