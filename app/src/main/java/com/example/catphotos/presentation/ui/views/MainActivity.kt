package com.example.catphotos.presentation.ui.views

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
import com.example.catphotos.utils.Extensions.message
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Variaveis para controle de carregamento de itens do RecyclerView
    private val visibleThreshold = 5 // Número de itens a serem carregados antes do final da lista
    private var isLoading = false
    private var lastVisibleItem = 0
    private var totalItemCount = 0


    private val mainViewModel: MainViewModel by viewModels()

    val adapter by lazy { CatsAdapter() }
    val layoutManager by lazy { GridLayoutManager(this, 2) }

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
        binding.rvImagesCats.layoutManager = layoutManager
        setScrollEventOnRecyclerView()
    }

    private fun setScrollEventOnRecyclerView() {
        binding.rvImagesCats.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 ) {
                        totalItemCount = layoutManager.itemCount
                        lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                            message("Carregando + imagens")
                            getListOfImagesCat()
                        }
                        /*val isPossibleDown = recyclerView.canScrollVertically(1)
                        if (!isPossibleDown) {

                        }*/
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