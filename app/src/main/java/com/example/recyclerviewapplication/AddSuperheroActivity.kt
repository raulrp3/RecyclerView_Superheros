package com.example.recyclerviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.recyclerviewapplication.Models.Superhero
import com.google.android.material.textfield.TextInputLayout

class AddSuperheroActivity : AppCompatActivity() {
    private lateinit var etSuperhero: EditText;
    private lateinit var etRealName: EditText;
    private lateinit var etPublisher: EditText;
    private lateinit var etImage: EditText;
    private lateinit var tilSuperhero: TextInputLayout;
    private lateinit var tilRealName: TextInputLayout;
    private lateinit var tilPublisher: TextInputLayout;
    private lateinit var tilImage: TextInputLayout;
    private lateinit var btnSave: Button;
    private val REQUEST = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_superhero)

        this.initUi();

        btnSave.setOnClickListener{
            val validateSuperhero: Boolean = validateSuperhero();
            val validateRealName: Boolean = validateRealName();
            val validatePublisher: Boolean = validatePublisher();
            val validateImage: Boolean = validateImage();

            if (validateSuperhero && validateRealName && validatePublisher && validateImage) {
                val superhero: Superhero = Superhero(etSuperhero.text.toString(), etPublisher.text.toString(), etRealName.text.toString(), etImage.text.toString());
                val json: String = superhero.toJson();
                val intent: Intent = intent.putExtra("superhero", json);
                setResult(REQUEST, intent);
                finish();
            }
        }
    }

    private fun initUi() {
        etSuperhero = findViewById(R.id.etSuperhero);
        etRealName = findViewById(R.id.etRealName);
        etPublisher = findViewById(R.id.etPublisher);
        etImage = findViewById(R.id.etImage);
        tilSuperhero = findViewById(R.id.tilSuperhero);
        tilRealName = findViewById(R.id.tilRealName);
        tilPublisher = findViewById(R.id.tilPublisher);
        tilImage = findViewById(R.id.tilImage);
        btnSave = findViewById(R.id.btnSave);
    }

    private fun validateSuperhero(): Boolean {
        val superhero: String = etSuperhero.text.toString();

        if (superhero.isEmpty() || superhero.isBlank()) {
            tilSuperhero.error = "El campo nombre del superhéroe no es válido.";
            return false;
        }

        return true;
    }

    private fun validateRealName(): Boolean {
        val realName: String = etRealName.text.toString();

        if (realName.isEmpty() || realName.isBlank()) {
            tilRealName.error = "El campo nombre real no es válido.";
            return false;
        }

        return true;
    }

    private fun validatePublisher(): Boolean {
        val publisher: String = etPublisher.text.toString();

        if (publisher.isEmpty() || publisher.isBlank()) {
            tilPublisher.error = "El campos editor no es válido.";
            return false;
        }

        return true;
    }

    private fun validateImage(): Boolean {
        val image: String = etImage.text.toString();

        if (image.isEmpty() || image.isBlank()) {
            tilImage.error = "El campo imagen no es válido.";
            return false;
        }

        return true;
    }
}