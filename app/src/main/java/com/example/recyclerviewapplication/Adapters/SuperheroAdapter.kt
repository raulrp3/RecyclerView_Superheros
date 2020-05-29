package com.example.recyclerviewapplication.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapplication.DetailAcitivity
import com.example.recyclerviewapplication.MainActivity
import com.example.recyclerviewapplication.Models.Superhero
import com.example.recyclerviewapplication.R
import com.squareup.picasso.Picasso

class SuperheroAdapter(
    private var superheros: MutableList<Superhero>,
    private var context: Context
) : RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {

    fun SuperheroAdapter(superheros: MutableList<Superhero>, context: Context) {
        this.superheros = superheros;
        this.context = context;
    }

    override fun onBindViewHolder(holder: SuperheroAdapter.ViewHolder, position: Int) {
        val item = superheros.get(position);
        holder.bind(item , context);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context);

        return ViewHolder(inflater.inflate(R.layout.item_superhero_list, parent, false));
    }

    override fun getItemCount(): Int {
        return superheros.size;
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvSuperhero: TextView = view.findViewById(R.id.tvSuperhero);
        var tvRealName: TextView = view.findViewById(R.id.tvRealName);
        var tvPublisher: TextView = view.findViewById(R.id.tvPublisher);
        var ivAvatar: ImageView = view.findViewById(R.id.ivAvatar);

        fun bind(superhero: Superhero, context: Context) {
            tvSuperhero.text = superhero.superhero;
            tvRealName.text = superhero.realName;
            tvPublisher.text = superhero.publisher;
            ivAvatar.loadUrl(superhero.image);

            itemView.setOnClickListener {
                val json: String = superhero.toJson();

                val intent: Intent = Intent(context, DetailAcitivity::class.java);
                intent.putExtra("superhero", json);
                context.startActivity(intent);
            }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this);
        }
    }
}