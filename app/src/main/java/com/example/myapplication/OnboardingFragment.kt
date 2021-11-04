package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.databinding.FragmentOnboardingBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

const val VOLUME_UP_KEY = "volume_up"

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private val viewBinding by viewBinding(FragmentOnboardingBinding::bind)

    private var player: ExoPlayer? = null
    private var volumeUp = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("assets:///onboarding.mp4"))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
        }
        savedInstanceState?.getBoolean(VOLUME_UP_KEY)?.let {
            volumeUp = it
        }
        viewBinding.playerView.player = player
        val volumeControlButton = viewBinding.volumeControlButton
        viewBinding.volumeControlButton.setOnClickListener {
            if (volumeUp) {
                volumeUp = false
                player?.volume = 0.0F
                volumeControlButton.setImageResource(R.drawable.ic_volume_off_white_24dp)
            } else {
                volumeUp = true
                player?.volume = 1.0F
                volumeControlButton.setImageResource(R.drawable.ic_volume_up_white_24dp)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(VOLUME_UP_KEY, volumeUp)
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}