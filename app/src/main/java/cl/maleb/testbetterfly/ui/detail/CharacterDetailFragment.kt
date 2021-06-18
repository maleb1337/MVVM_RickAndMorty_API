package cl.maleb.testbetterfly.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cl.maleb.testbetterfly.R
import cl.maleb.testbetterfly.api.detail.CharacterDetailData
import cl.maleb.testbetterfly.databinding.FragmentCharacterDetailBinding
import cl.maleb.testbetterfly.utils.Resource
import cl.maleb.testbetterfly.utils.gone
import cl.maleb.testbetterfly.utils.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModels()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObservers() {
        viewModel.characterDetailLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Error -> showErrorView(result.error)
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> showSuccessView(result.data)
            }
        })
    }

    private fun setUpView() {
        args.characterIdentifier?.let { viewModel.getCharacterDetail(it) }
    }


    private fun showLoadingView() {
        binding.progressBar.visible()
    }

    private fun showErrorView(error: Throwable?) {
        binding.apply {
            progressBar.gone()
            textViewError.apply {
                visible()
                text = error?.localizedMessage
            }
        }
    }

    private fun showSuccessView(data: CharacterDetailData?) {
        binding.apply {
            progressBar.gone()
            data?.apply {
                Glide.with(requireContext())
                    .load(image)
                    .into(imageView)

                textViewTitle.text = name
                textViewDescription.text = created
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}