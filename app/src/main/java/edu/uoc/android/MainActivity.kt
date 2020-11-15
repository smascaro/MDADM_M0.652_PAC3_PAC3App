package edu.uoc.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dashboard_item_museums.setOnClickListener {
            val museumsActivityIntent = Intent(this, MuseumsActivity::class.java)
            startActivity(museumsActivityIntent)
        }

        dashboard_item_maps.setOnClickListener {
            val mapsActivityIntent = Intent(this, MapsActivity::class.java)
            startActivity(mapsActivityIntent)
        }
        dashboard_item_quizzes.setOnClickListener {
            val quizzesActivityIntent = Intent(this, QuizzesActivity::class.java)
            startActivity(quizzesActivityIntent)
        }
    }
}