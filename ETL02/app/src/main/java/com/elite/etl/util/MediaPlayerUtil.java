package com.elite.etl.util;

import android.media.MediaPlayer;

import com.elite.etl.EtlApplication;
import com.elite.etl.R;
import com.elite.etl.model.VoiceEnum;

import java.util.Optional;

/**
 * @Author: Wesker
 * @Date: 2019-05-25 10:26
 * @Version: 1.0
 */
public class MediaPlayerUtil {
    private static final String TAG = "MediaPlayerUtil";
    private static MediaPlayer mediaPlayer;
    public static void play(VoiceEnum voiceType) {
        Optional.ofNullable(mediaPlayer)
                .ifPresent(x -> {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                });
        switch (voiceType) {
            case SERVICE_START:
                mediaPlayer = MediaPlayer.create(EtlApplication.getContext(), R.raw.testmp3);
                break;
            case SERVICE_CONFIRM:break;
            case SERVICE_STOP:break;
            case SERVICE_COMMENT:break;
            default:break;
        }
        play();
    }
    private static void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
