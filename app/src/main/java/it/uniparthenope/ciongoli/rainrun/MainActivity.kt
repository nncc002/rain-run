package it.uniparthenope.ciongoli.rainrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import it.uniparthenope.ciongoli.rainrun.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val runner=Character()
        val earthobst= Obstacle()
        binding.wallpaper.setOnClickListener{
            runner.jump()}
        binding.playbutton.setOnClickListener{
            lifecycleScope.launch{
                binding.title.visibility=View.GONE
                binding.playbutton.visibility=View.GONE
                while(true){
                    runner.frame()
                    binding.player.translationY = runner.pos_y.toFloat()
                    earthobst.frame()
                    binding.obstacle.translationX=earthobst.pos_x.toFloat()
                    delay(16)
                }
            }
            Log.d("State","Game started")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        }

    }

