package it.uniparthenope.ciongoli.rainrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import it.uniparthenope.ciongoli.rainrun.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewmodel: VwModel by viewModels()
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
                var sc:Int=0

                while(true){
                    binding.score.text=(sc/10).toString()
                    runner.frame()
                    binding.player.translationY = runner.pos_y.toFloat()
                    earthobst.frame()
                    binding.obstacle.translationX=earthobst.pos_x.toFloat()
                    sc=sc+1
                    if(binding.player.x<binding.obstacle.x+binding.obstacle.width && binding.player.x+binding.player.width>binding.obstacle.x && binding.player.y<binding.obstacle.y+binding.obstacle.height && binding.player.y+binding.player.height>binding.obstacle.y){
                        Log.d("State", "Game Over")
                        break
                    }
                    delay(16)
                }
                if((sc/10)>viewmodel.highscore){
                    viewmodel.highscore=(sc/10)
            }
                binding.highscore.text=viewmodel.highscore.toString()
                binding.gameovertxt.visibility=View.VISIBLE
                delay(2000)
                binding.gameovertxt.visibility=View.GONE
                binding.title.visibility=View.VISIBLE
                binding.playbutton.visibility=View.VISIBLE
                runner.reset()
                earthobst.reset()
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        }

    }

