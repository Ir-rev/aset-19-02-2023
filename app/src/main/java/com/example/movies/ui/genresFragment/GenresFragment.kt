package com.example.movies.ui.genresFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movies.databinding.GenresFragmentBinding
import com.example.movies.ui.ClassForTest

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
//        binding.movieInfoBtn.setOnClickListener {
//            findNavController().navigate(GenresFragmentDirections.actionGenresFragmentToMovieInfoFragment(
//                ClassForTest(binding.moviesHeader.text.toString())
//            ))
//        }
//    }
        binding.movieInfoBtn.setOnClickListener {
            findNavController().navigate(GenresFragmentDirections.actionGenresFragmentToMovieInfoFragment(
                ClassForTest(
                    binding.moviesHeader.text.toString()
                )
            ))
        }
    }

}