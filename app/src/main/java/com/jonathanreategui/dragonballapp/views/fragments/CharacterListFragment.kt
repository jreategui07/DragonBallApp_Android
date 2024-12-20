package com.jonathanreategui.dragonballapp.views.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private val TAG: String = "Lifecycle-CharacterListFragment"
    private val viewModel: CharacterViewModel by activityViewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemRowAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "#1: onAttach called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "#2: onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "#3: onCreateView called")
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "#4: onViewCreated called")
        setupRecyclerView()
        observeViewModel()
        if (savedInstanceState == null) {
            viewModel.fetchCharacters()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "#5: onViewStateRestored called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "#6: onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "#7: onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "#8: onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "#9: onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "#10: onDestroyView called")
        binding.recyclerViewCharacters.adapter = null
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "#11: onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "#12: onDetach called")
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
}
