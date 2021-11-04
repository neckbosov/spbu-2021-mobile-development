package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.databinding.FragmentOnboardingBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class OnboardingFragment : BaseFragment(R.layout.fragment_onboarding) {
    companion object {
        const val VOLUME_UP_KEY = "volume_up"
        const val CURRENT_POSITION_KEY = "current_position"
    }

    private val viewBinding by viewBinding(FragmentOnboardingBinding::bind)

    private lateinit var player: ExoPlayer
    private var volumeUp = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("asset:///onboarding.mp4"))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
        }

        viewBinding.playerView.player = player
        savedInstanceState?.run {
            volumeUp = getBoolean(VOLUME_UP_KEY)
            player.seekTo(getLong(CURRENT_POSITION_KEY))
        }
        val volumeControlButton = viewBinding.volumeControlButton
        configureVolume(volumeControlButton)

        viewBinding.volumeControlButton.setOnClickListener {
            volumeUp = !volumeUp
            configureVolume(volumeControlButton)
        }

        viewBinding.viewPager.run {
            setTextPages()
            attachDots(viewBinding.onboardingTextTabLayout)
        }

        viewBinding.signInButton.setOnClickListener {
            // TODO: Go to SignInFragment.
            Toast.makeText(requireContext(), "Нажата кнопка войти", Toast.LENGTH_SHORT).show()
        }
        viewBinding.signUpButton.setOnClickListener {
            // TODO: Go to SignUpFragment.
            Toast.makeText(requireContext(), "Нажата кнопка зарегистрироваться", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun configureVolume(volumeControlButton: ImageButton) {
        if (volumeUp) {
            player.volume = 1.0F
            volumeControlButton.setImageResource(R.drawable.ic_volume_up_white_24dp)
        } else {
            player.volume = 0.0F
            volumeControlButton.setImageResource(R.drawable.ic_volume_off_white_24dp)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(CURRENT_POSITION_KEY, player.currentPosition)
        outState.putBoolean(VOLUME_UP_KEY, volumeUp)
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    private fun ViewPager2.setTextPages() {
        adapter = ListDelegationAdapter(onboardingTextAdapterDelegate()).apply {
            items = listOf(
                getString(R.string.onboarding_view_pager_text_1),
                getString(R.string.onboarding_view_pager_text_2),
                getString(R.string.onboarding_view_pager_text_3)
            )
        }
    }

    private fun ViewPager2.attachDots(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, this) { _, _ -> }.attach()
    }
}