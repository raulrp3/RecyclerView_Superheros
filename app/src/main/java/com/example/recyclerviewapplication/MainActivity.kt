package com.example.recyclerviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapplication.Adapters.SuperheroAdapter
import com.example.recyclerviewapplication.Helpers.SwipeToDeleteCallback
import com.example.recyclerviewapplication.Models.Superhero
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvSuperherosList: RecyclerView;
    private lateinit var fabAdd: FloatingActionButton;
    private lateinit var superheros: MutableList<Superhero>;
    private lateinit var superhero: Superhero;
    private val REQUEST = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initUi();
        this.initSuperheros();

        fabAdd.setOnClickListener{
            val intent: Intent = Intent(this, AddSuperheroActivity::class.java);
            startActivityForResult(intent, REQUEST);
        }
    }

    private fun initUi() {
        fabAdd = findViewById(R.id.fabAdd);
        superheros = ArrayList();
        rvSuperherosList = findViewById(R.id.rvSuperheroList);
        rvSuperherosList.setHasFixedSize(true);
        rvSuperherosList.layoutManager = LinearLayoutManager(this);

        rvSuperherosList.adapter = SuperheroAdapter(superheros, this);

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rvSuperherosList.adapter as SuperheroAdapter;
                adapter.removeAt(viewHolder.adapterPosition);
            }
        }

        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(swipeHandler);
        itemTouchHelper.attachToRecyclerView(rvSuperherosList);

        superhero = Superhero("", "", "", "");
    }

    private fun initSuperheros() {
        superheros.add(Superhero("Spiderman", "Marvel", "Peter Parker", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        superheros.add(Superhero("Daredevil", "Marvel", "Matthew Michael Murdock", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"))
        superheros.add(Superhero("Wolverine", "Marvel", "James Howlett", "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"))
        superheros.add(Superhero("Batman", "DC", "Bruce Wayne", "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"))
        superheros.add(Superhero("Thor", "Marvel", "Thor Odinson", "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"))
        superheros.add(Superhero("Flash", "DC", "Jay Garrick", "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"))
        superheros.add(Superhero("Green Lantern", "DC", "Alan Scott", "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"))
        superheros.add(Superhero("Wonder Woman", "DC", "Princess Diana", "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == this.REQUEST) {
            val json: String = data!!.getStringExtra("superhero");
            superhero = superhero.fromJson(json);

            superheros.add(superhero);
            rvSuperherosList.adapter!!.notifyDataSetChanged();
        }
    }
}
