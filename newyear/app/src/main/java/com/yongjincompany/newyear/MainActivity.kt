package com.yongjincompany.newyear

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.yongjincompany.newyear.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var runnable: Runnable
    private var handler = Handler()

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songs = listOf(
            R.raw.borangbithwangi,
            R.raw.damdadi,
            R.raw.giuknalgnaliwado,
            R.raw.chungchun,
            R.raw.hyehwadong,
            R.raw.iganingirohayo,
            R.raw.mailgdaewa,
            R.raw.nagejulgsuitnungun,
            R.raw.nahangsanggdaerle,
            R.raw.nasarangnegutae,
            R.raw.sawarigamun,
            R.raw.sonu,
            R.raw.together,
            R.raw.toyou
        )
        var shuffledSounds = songs.shuffled()

        var mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.damdadi)
        binding.seekbar.progress = 0
        binding.seekbar.max = mediaplayer.duration


        binding.play.setOnClickListener {
            if (!mediaplayer.isPlaying) {
                mediaplayer.start()

                binding.play.setImageResource(R.drawable.ic_round_pause_24)
            } else {
                mediaplayer.pause()
                binding.play.setImageResource(R.drawable.ic_round_play_arrow_24)
            }
        }
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        runnable = Runnable {
            binding.seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaplayer.setOnCompletionListener {
            binding.play.setImageResource(R.drawable.ic_round_play_arrow_24)
        }

        binding.presong.setOnClickListener {

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}

