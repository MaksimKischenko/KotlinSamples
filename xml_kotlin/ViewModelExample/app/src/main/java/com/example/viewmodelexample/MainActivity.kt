package com.example.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.example.viewmodelexample.adapters.LifeCycleAdapter
import com.example.viewmodelexample.adapters.UserAdapter
import com.example.viewmodelexample.models.UserViewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by lazy {ViewModelProvider(this)?.get(UserViewModel::class.java)}
    lateinit var myObserver : LifeCycleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myObserver = LifeCycleAdapter()
        lifecycle.addObserver(myObserver)

        //val itemsList : RecyclerView = findViewById(R.id.userList)
        val adapter = UserAdapter()
        userList.layoutManager = LinearLayoutManager(this)
        userList.adapter = adapter

        userViewModel?.getListUsers()?.observe(this, Observer {
            it.let {
                adapter.refreshUsers(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {
                userViewModel?.updateListUsers()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

