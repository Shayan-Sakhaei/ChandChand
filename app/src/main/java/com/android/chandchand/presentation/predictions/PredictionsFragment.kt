package com.android.chandchand.presentation.predictions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.chandchand.databinding.FragmentPredictionBinding
import com.android.chandchand.presentation.common.IView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PredictionsFragment : Fragment(), IView<PredictionsState> {

    private val viewModel: PredictionsViewModel by viewModels {
        defaultViewModelProviderFactory
    }

    val args: PredictionsFragmentArgs by navArgs()

    private var _binding: FragmentPredictionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPredictionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
        viewModel.send(PredictionsIntent.GetPredictions(args.fixtureId))
        setUp()
    }

    override fun render(state: PredictionsState) {
        with(state) {
            predictions?.let {}
        }
    }

    private fun setUp() {
        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}