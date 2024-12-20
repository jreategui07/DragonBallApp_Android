package com.jonathanreategui.dragonballapp.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.jonathanreategui.dragonballapp.R
import com.jonathanreategui.dragonballapp.data.models.DragonBallCharacter
import com.jonathanreategui.dragonballapp.databinding.FragmentCharacterDetailBinding
import com.jonathanreategui.dragonballapp.viewmodel.CharacterViewModel
import kotlinx.serialization.json.Json

class CharacterDetailFragment : Fragment() {
    private val TAG: String = "Lifecycle-CharacterDetailFragment"
    private val viewModel: CharacterViewModel by activityViewModels()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "#4: onViewCreated called")
        if (resources.getBoolean(R.bool.isTablet)) {
            viewModel.selectedCharacter.observe(viewLifecycleOwner) { character ->
                updateCharacterDetails(character)
            }
        } else {
            arguments?.getString("EXTRA_CHARACTER")?.let { characterJson ->
                try {
                    val character = Json.decodeFromString<DragonBallCharacter>(characterJson)
                    updateCharacterDetails(character)
                } catch (e: Exception) {
                    println("Error decoding character: ${e.message}")
                    showErrorState()
                }
            }
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

    private fun updateCharacterDetails(character: DragonBallCharacter?) {
        if (character != null) {
            Glide.with(this)
                .load(character.image)
                .into(binding.imageCharacter)
            binding.textCharacterName.text = character.name
            binding.textCharacterRace.text = "Species: ${character.race}"
            binding.textCharacterAffiliation.text = "Affiliation: ${character.affiliation}"
            binding.textCharacterGender.text = "Gender: ${character.gender}"
            binding.textCharacterKi.text = "Ki: ${character.ki} / Max Ki: ${character.maxKi}"
            binding.textCharacterDescription.text = character.description
        } else {
            showErrorState()
        }
    }

    private fun showErrorState() {
        binding.textCharacterName.text = "No character selected"
        binding.textCharacterRace.text = ""
        binding.textCharacterAffiliation.text = ""
        binding.textCharacterGender.text = ""
        binding.textCharacterKi.text = ""
        binding.textCharacterDescription.text = ""
        binding.imageCharacter.setImageResource(android.R.drawable.ic_menu_report_image)
    }
}