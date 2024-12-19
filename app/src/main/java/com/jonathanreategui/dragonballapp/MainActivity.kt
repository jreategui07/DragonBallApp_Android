package com.jonathanreategui.dragonballapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonathanreategui.dragonballapp.views.fragments.CharacterListFragment
import com.jonathanreategui.dragonballapp.views.fragments.CharacterDetailFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showCharacterListFragment()
        }
    }

    private fun showCharacterListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_list, CharacterListFragment())
        if (resources.getBoolean(R.bool.isTablet)) {
            transaction.replace(R.id.fragment_container_detail, CharacterDetailFragment())
        }
        transaction.commit()
    }
}