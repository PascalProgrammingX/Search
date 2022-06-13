package com.thesearch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thesearch.adapters.SearchAdapter
import com.thesearch.api.Endpoint
import com.thesearch.api.RetrofitClientInstance
import com.thesearch.databinding.ActivityMainBinding
import com.thesearch.request.SearchRequest
import com.thesearch.response.Drink
import com.thesearch.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener, (Drink) -> Unit {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchAdapter: SearchAdapter
    var searchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        searchAdapter = SearchAdapter(this, this)
        binding.recyclerView.adapter = searchAdapter

        binding.refresh.setOnRefreshListener {
            search(searchQuery)
        }

    }


    /**
     * @param alphabet
     * Query to be made
     */
    private fun search(alphabet:String){
        binding.refresh.isRefreshing = true
        val retrofit = RetrofitClientInstance.retrofitInstance!!.create(Endpoint::class.java)
        val request = SearchRequest(alphabet) // Endpoint accepts params in encoded url so this is useless.
        val call: Call<SearchResponse> = retrofit.search(alphabet)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                binding.refresh.isRefreshing = false
                response.body()?.let {
                    if (response.body()!!.drinks.isEmpty()){
                        binding.switcher.showNext()
                    }else{
                        binding.switcher.showPrevious()
                        searchAdapter.setSearchResult(it.drinks)
                    }
                }

            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                binding.refresh.isRefreshing = false
            }
        })
    }



    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        searchQuery = query
        if (searchQuery.isNotEmpty() && searchQuery.length < 2){
            search(searchQuery)
        }
        return true
    }


    override fun invoke(searchItem: Drink) {
        //Item click data from interface event
        //View ingredient
    }


}