package test.myapplication

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : ComponentActivity(R.layout.main_layout) {
    @androidx.annotation.OptIn(UnstableApi::class)
    override fun onStart() {
        super.onStart()
        val playerView = findViewById<PlayerView>(R.id.player_view)
        val player = ExoPlayer.Builder(this)
            .build()
        playerView.player = player
        player.setVideoEffects(listOf())
        player.setMediaItem(MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        player.addListener(object: Player.Listener{
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if(isPlaying){
                    for(i in 0..<player.rendererCount){
                        Log.v("Main", "${player.getRenderer(i)}")
                    }
                }
            }
        })
        player.prepare()
        player.play()
    }
}