package ru.lachesis.translator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.lachesis.translator.R
import ru.lachesis.translator.view.history.HistoryFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,MainFragment.newInstance(savedInstanceState) ,null)
            .commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.history_menu_item)   {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HistoryFragment.newInstance(),"").addToBackStack(null).commit()
            true}
        else super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

}