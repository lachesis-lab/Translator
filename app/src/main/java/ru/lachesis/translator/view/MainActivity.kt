package ru.lachesis.translator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.lachesis.translator.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.main_container,MainFragment.newInstance(savedInstanceState) ,null)
            .commit()
    }
}