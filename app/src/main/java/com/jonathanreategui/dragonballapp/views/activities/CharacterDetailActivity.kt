package com.jonathanreategui.dragonballapp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonathanreategui.dragonballapp.R
import com.jonathanreategui.dragonballapp.views.fragments.CharacterDetailFragment

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        if (savedInstanceState == null) {
            showCharacterDetailFragment()
        }
    }

    private fun showCharacterDetailFragment() {
        val characterJson = intent.getStringExtra("EXTRA_CHARACTER")
        val bundle = Bundle().apply { putString("EXTRA_CHARACTER", characterJson) }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_detail, CharacterDetailFragment().apply { arguments = bundle })
        transaction.commit()
    }
}