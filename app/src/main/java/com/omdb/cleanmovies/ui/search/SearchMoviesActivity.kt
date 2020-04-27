package com.omdb.cleanmovies.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdb.cleanmovies.R
import com.omdb.cleanmovies.common.BaseActivity
import com.omdb.cleanmovies.databinding.ActivitySearchMoviesBinding
import com.omdb.cleanmovies.ui.details.MovieDetailsActivity
import io.reactivex.functions.Consumer
import kotlin.reflect.KClass

class SearchMoviesActivity :
    BaseActivity<ActivitySearchMoviesBinding, SearchMoviesViewModel>(R.layout.activity_search_movies) {

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearch()
    }

    override fun viewModelClass(): KClass<SearchMoviesViewModel> {
        return SearchMoviesViewModel::class
    }

    private fun setupSearch() {
        binding.searchMoviesResults.layoutManager = LinearLayoutManager(this)
        binding.searchMoviesResults.adapter = adapter

        binding.searchMoviesTitleInput.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                viewModel.searchMovie(binding.searchMoviesTitleInput.text.toString())
            }

            actionId == EditorInfo.IME_ACTION_SEARCH
        }

        adapter.setMovieClickedListener { id, title ->
            val openMovieDetails = Intent(this, MovieDetailsActivity::class.java)
            openMovieDetails.putExtra(MovieDetailsActivity.FLAG_MOVIE_ID, id)
            openMovieDetails.putExtra(MovieDetailsActivity.FLAG_MOVIE_TITLE, title)

            startActivity(openMovieDetails)
        }

        observeSuccess(viewModel.searchMoviesResult, Consumer {
            adapter.data = it
        }, binding.searchMoviesLoading)
    }
}