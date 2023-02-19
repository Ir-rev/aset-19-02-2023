package com.example.movies.ui.genresFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.GenresFragmentBinding
import com.example.movies.ui.ClassForTest
import com.example.movies.ui.MoviesAdapter
import kotlinx.coroutines.launch

class GenresFragment : Fragment() {
    private lateinit var binding: GenresFragmentBinding
    private lateinit var viewModel: GenresFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GenresFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GenresFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genresRV = binding.moviesRv
        genresRV.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.genresListLiveData.observe(viewLifecycleOwner){ filmsList ->
            val moviesAdapter = MoviesAdapter(filmsList.films)
            genresRV.adapter = moviesAdapter
        }

        lifecycleScope.launch {
            viewModel.action.collect { action ->
                when(action){
                    is GenresFragmentViewModel.Action.OpenFilmDetails -> {
                        val navController = findNavController()
                        if ((navController.currentDestination as? FragmentNavigator.Destination)?.className == this@GenresFragment.javaClass.name){
                            navController.navigate(
                                GenresFragmentDirections.actionGenresFragmentToMovieInfoFragment(
                                    action.film
                                )
                            )
                        }
                    }
                    is GenresFragmentViewModel.Action.ShowError -> {
                        Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }

}