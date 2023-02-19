package com.example.movies.ui.genresFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.response.Film
import com.example.movies.response.FilmsList
import com.example.movies.rest.MoviesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragmentViewModel : ViewModel() {
    private val TAG = "checkResult"
    private val repository = MoviesRepository()

    private val _genresListLiveData = MutableLiveData<FilmsList>()
    val genresListLiveData: LiveData<FilmsList> = _genresListLiveData

    val action = MutableSharedFlow<Action>()

    init {
        checkIfFilmsListIsEmpty()
    }

    private fun checkIfFilmsListIsEmpty() {
        repository.getFilmsList().enqueue(object : Callback<FilmsList> {
            override fun onResponse(call: Call<FilmsList>, response: Response<FilmsList>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    val filmList = response.body() ?: return
                    filmList.films.forEach { film ->
                        film.onClick = {
                            viewModelScope.launch {
                                action.emit(Action.OpenFilmDetails(film))
                            }
                        }
                    }
                    _genresListLiveData.postValue(filmList)
                } else {
                    Log.d(TAG, "Ошибка выгрузки списка фильмов")
                }
            }

            override fun onFailure(call: Call<FilmsList>, t: Throwable) {
                Log.d(TAG, "Ошибка загрузки", t)
            }
        })
    }

    sealed class Action {
        class OpenFilmDetails(val film: Film) : Action()
        class ShowError(val message: String) : Action()
    }
}

