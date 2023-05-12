package com.krishworld.jetpack_compose_demo.components

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.media3.ui.R.drawable.*
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.ui.theme.Purple80
import java.util.*
import kotlin.math.absoluteValue

class ExoPlayerState(context: Context) {
    val exoPlayer = ExoPlayer.Builder(context).build()
}

@UnstableApi
@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current
    val exoPlayerState by remember(context) { mutableStateOf(ExoPlayerState(context)) }
    var isPlayerReady by remember { mutableStateOf(false) }

    exoPlayerState.exoPlayer
        .apply {
            val defaultDataSourceFactory = DefaultDataSource.Factory(context)
            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                context,
                defaultDataSourceFactory
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))

            setMediaSource(source)
            prepare()
        }

    exoPlayerState.exoPlayer.addListener(object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_READY) {
                isPlayerReady = true
            }
        }
    })

    exoPlayerState.exoPlayer.playWhenReady = true
    exoPlayerState.exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayerState.exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    Box(
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                NetworkImageThumbnail(url = uri.toString())
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = colorResource(id = R.color.purple_200),
                    strokeWidth = Dp(value = 4F)
                )
            }

            if (isPlayerReady) {
                DisposableEffect(
                    AndroidView(
                        factory = {
                            PlayerView(context).apply {
                                hideController()
                                useController = false
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                                player = exoPlayerState.exoPlayer
                                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            }
                        },

                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5))
                            .border(4.dp, Color.Gray, RoundedCornerShape(5))
                            .background(Purple80)
                            .fillMaxWidth(),
                    )
                ) {
                    onDispose { exoPlayerState.exoPlayer.release() }
                }
            }
            if (isPlayerReady) {
                PlayerOverlay(
                    exoPlayerState = exoPlayerState,
                )
            }
        }
    }
}

@Composable
fun PlayerOverlay(
    exoPlayerState: ExoPlayerState,
) {
    var isAlreadyPlayed by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableStateOf(0f) }
    var isBuffering by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(exoPlayerState.exoPlayer.isPlaying) }
    var maxValue by remember { mutableStateOf(0f) }
    val timer = Timer()


    exoPlayerState.exoPlayer.addListener(object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            isBuffering = playbackState == Player.STATE_BUFFERING
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            if (!isAlreadyPlayed && isPlaying) {
                maxValue = exoPlayerState.exoPlayer.duration.absoluteValue.toFloat()
                Log.d("maxValue", "$maxValue")
                isAlreadyPlayed = true

                timer.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        Handler(Looper.getMainLooper()).post {
                            sliderPosition = exoPlayerState.exoPlayer.currentPosition.toFloat()
                        }
                    }
                }, 0, 1000)
            }
        }
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 2.dp)
    ) {
        if (isBuffering) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = colorResource(id = R.color.purple_200),
                strokeWidth = Dp(value = 4F)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(
                onClick = {
                    var currentPosition = exoPlayerState.exoPlayer.currentPosition
                    if (currentPosition - 10000 > 0) {
                        currentPosition -= 10000
                    } else {
                        currentPosition = 0
                    }
                    sliderPosition = currentPosition.toFloat()
                    exoPlayerState.exoPlayer.seekTo(currentPosition)
                }
            ) {
                Image(
                    painter = painterResource(
                        id =
                        exo_ic_rewind
                    ),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                    isPlaying = !isPlaying
                    if (isPlaying) {
                        exoPlayerState.exoPlayer.pause()
                    } else {
                        exoPlayerState.exoPlayer.play()
                    }
                }
            ) {
                Image(
                    painter = painterResource(
                        id = if (isPlaying) {
                            exo_icon_play
                        } else {
                            exo_icon_pause
                        }
                    ),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                    var currentPosition = exoPlayerState.exoPlayer.currentPosition
                    if (currentPosition + 10000 > maxValue) {
                        currentPosition = maxValue.toLong()
                    } else {
                        currentPosition += 10000
                    }
                    sliderPosition = currentPosition.toFloat()
                    exoPlayerState.exoPlayer.seekTo(currentPosition)
                }
            ) {
                Image(
                    painter = painterResource(
                        id =
                        exo_ic_forward
                    ),
                    contentDescription = null
                )
            }
        }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..maxValue,
            onValueChangeFinished = {
                exoPlayerState.exoPlayer.seekTo(sliderPosition.toLong())
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

