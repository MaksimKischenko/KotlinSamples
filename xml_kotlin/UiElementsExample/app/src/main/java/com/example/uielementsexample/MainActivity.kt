package com.example.uielementsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.uielementsexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "UI_APP"

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            navView.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.item1 -> {
                        Toast.makeText(this@MainActivity, "ITEM1", Toast.LENGTH_SHORT).show()
                    }
                    R.id.item2 -> {
                        Toast.makeText(this@MainActivity, "ITEM2", Toast.LENGTH_SHORT).show()
                    }
                }
                drawer.closeDrawer(GravityCompat.START)
                true
            }
            bottomNV.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
                        Toast.makeText(this@MainActivity, "HOME", Toast.LENGTH_SHORT).show()
                    }
                    R.id.museum -> {
                        Toast.makeText(this@MainActivity, "MUSEUM", Toast.LENGTH_SHORT).show()
                    }
                    R.id.restaurant -> {
                        Toast.makeText(this@MainActivity, "RESTAURANT", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> binding.drawer.openDrawer(GravityCompat.START)
            R.id.sync -> Toast.makeText(this, "SYNC", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}