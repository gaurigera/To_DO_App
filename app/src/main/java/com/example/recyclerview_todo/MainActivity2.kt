package com.example.recyclerview_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_todo.databinding.ActivityMain2Binding
import com.example.recyclerview_todo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout = binding.drawerLayout

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var taskList = mutableListOf<Task>()

        val adapter = RecvAdapter(taskList)
        binding.recv.adapter = adapter
        binding.recv.layoutManager = LinearLayoutManager(this)

        binding.btn.setOnClickListener {
            val new_task = binding.newTask.text.toString()
            taskList.add(Task("$new_task", false))
            adapter.notifyItemInserted(taskList.size - 1)
            adapter.deleteDoneTodos()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_item_1 -> Toast.makeText(applicationContext,  "Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item)
    }
}