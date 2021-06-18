package cl.maleb.testbetterfly.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.maleb.testbetterfly.R
import cl.maleb.testbetterfly.api.list.ResultData
import cl.maleb.testbetterfly.databinding.FragmentCharacterListBinding
import cl.maleb.testbetterfly.utils.Resource
import cl.maleb.testbetterfly.utils.gone
import cl.maleb.testbetterfly.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragment_character_list),
    CharacterListAdapter.OnItemClickListener {

    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var characterListAdapter: CharacterListAdapter

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView()
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObservers() {
        viewModel.characterListLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Error -> showErrorView(result.error)
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> showSuccessView(result.data)
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.characterListEvent.collect { event ->
                when (event) {
                    is CharacterListEvent.NavigateToDetailScreen -> {
                        val action =
                            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                                characterIdentifier = event.characterIdentifier
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }

    }

    private fun setUpView() {
        characterListAdapter = CharacterListAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = characterListAdapter
                layoutManager = LinearLayoutManager(context)
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getCharacterList()
            }

        }

        viewModel.getCharacterList()

    }

    private fun showSuccessView(data: List<ResultData>?) {
        binding.apply {
            progressBar.gone()
            swipeRefreshLayout.isRefreshing = false
            // set data to controls
            characterListAdapter.submitList(data)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(result: ResultData) {
        viewModel.onCharacterSelected(result.id.toString())
    }
}