package frent.nobos.tiktoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import frent.nobos.tiktoes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val playerController = PlayerController()

    /**
     * @constructor
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerController.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(playerController.binding.root)
        playerController.initBoard()

    }

}