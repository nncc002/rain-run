package it.uniparthenope.ciongoli.rainrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import it.uniparthenope.ciongoli.rainrun.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: VwModel by viewModels(){
        VwModelFactory(ScoreRepo(AppData.getData(this).scoreDAO()))
    }
    private var stateJob: Job?= null
    val runner=Character()
    val earthobst= Obstacle()
    private fun startLoop(){
        stateJob = lifecycleScope.launch{


            while(true){
                binding.score.text=(viewmodel.score.value/10).toString()
                runner.frame()
                binding.player.translationY = runner.posy.toFloat()
                earthobst.frame()
                binding.obstacle.translationX=earthobst.posx.toFloat()
                viewmodel.increaseScore()
                if(binding.player.x<binding.obstacle.x+binding.obstacle.width && binding.player.x+binding.player.width>binding.obstacle.x && binding.player.y<binding.obstacle.y+binding.obstacle.height && binding.player.y+binding.player.height>binding.obstacle.y){
                    Log.d("State", "Game Over")
                    break
                }
                delay(16)
            }

            if((viewmodel.score.value/10)>viewmodel.highscore.value){
                viewmodel.save(viewmodel.score.value/10)
                Log.d("State", "Highscore saved")
            }
            viewmodel.setState(StateG.GAMEOVER)
            binding.gameovertxt.visibility=View.VISIBLE
            delay(2000)
            binding.gameovertxt.visibility=View.GONE
            binding.title.visibility=View.VISIBLE
            binding.playbutton.visibility=View.VISIBLE
            binding.resetbutton.visibility=View.VISIBLE
            runner.reset()
            earthobst.reset()
            viewmodel.setState(StateG.MENU)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewmodel.highscore.collect {value -> binding.highscore.text= value.toString()}
            }
        }
        binding.wallpaper.setOnClickListener{
            runner.jump()}
        binding.resetbutton.setOnClickListener {
            viewmodel.reset()
        }
        binding.playbutton.setOnClickListener{
            binding.title.visibility=View.GONE
            binding.playbutton.visibility=View.GONE
            binding.resetbutton.visibility=View.GONE

            viewmodel.resetScore()
            viewmodel.setState(StateG.INGAME)
            startLoop()
            Log.d("State","Game started")



        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        }
    override fun onPause(){
        super.onPause()
        if(viewmodel.state.value== StateG.INGAME){
            stateJob?.cancel()
            viewmodel.setState(StateG.PAUSE)
            Log.d("State","Game Paused")
        }
    }

    override fun onResume() {
        super.onResume()
        if(viewmodel.state.value== StateG.PAUSE){
            viewmodel.setState(StateG.INGAME)
            startLoop()
        }
    }

    }

