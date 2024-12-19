package com.jonathanreategui.dragonballapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathanreategui.dragonballapp.R
import com.jonathanreategui.dragonballapp.data.models.DragonBallCharacter
import com.jonathanreategui.dragonballapp.databinding.FragmentCharacterListBinding
import com.jonathanreategui.dragonballapp.viewmodel.CharacterViewModel
import com.jonathanreategui.dragonballapp.views.activities.CharacterDetailActivity
import com.jonathanreategui.dragonballapp.views.adapters.ItemRowAdapter
import kotlinx.serialization.json.Json

class CharacterListFragment : Fragment() {
    private val viewModel: CharacterViewModel by activityViewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemRowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        if (savedInstanceState == null) {
            viewModel.fetchCharacters()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemRowAdapter { selectedCharacter ->
            handleCharacterClick(selectedCharacter)
        }
        binding.recyclerViewCharacters.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            val characterList = characters?.items ?: emptyList()
            adapter.submitList(characterList)
            if (resources.getBoolean(R.bool.isTablet) && characterList.isNotEmpty() && viewModel.selectedCharacter.value == null) {
                viewModel.selectCharacter(characterList[0])
            }
        }
    }

    private fun handleCharacterClick(selectedCharacter: DragonBallCharacter) {
        if (resources.getBoolean(R.bool.isTablet)) {
            viewModel.selectCharacter(selectedCharacter)
        } else {
            val characterJson = Json.encodeToString(DragonBallCharacter.serializer(), selectedCharacter)
            val intent = Intent(requireContext(), CharacterDetailActivity::class.java).apply {
                putExtra("EXTRA_CHARACTER", characterJson)
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewCharacters.adapter = null
        _binding = null
    }
}
