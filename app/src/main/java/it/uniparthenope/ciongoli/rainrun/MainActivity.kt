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
            Log.d("State","Game started")
            lifecycleScope.launch{
                binding.title.visibility=View.GONE
                binding.playbutton.visibility=View.GONE
                while(true){
                    runner.frame()
                    binding.player.translationY = runner.pos_y.toFloat()
                    earthobst.frame()
                    binding.obstacle.translationX=earthobst.pos_x.toFloat()
                    Log.d("POSITION", "px${binding.player.x} , py${binding.player.y} , ph${binding.player.height}, pw${binding.player.width}, ox${binding.obstacle.x} , oy${binding.obstacle.y} , oh${binding.obstacle.height}, ow${binding.obstacle.width} ")
                    if(binding.player.x<binding.obstacle.x+binding.obstacle.width && binding.player.x+binding.player.width>binding.obstacle.x && binding.player.y<binding.obstacle.y+binding.obstacle.height && binding.player.y+binding.player.height>binding.obstacle.y){
                        Log.d("COLLISION", "Game Over")
                    }
                    delay(16)
                }
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        }

    }

