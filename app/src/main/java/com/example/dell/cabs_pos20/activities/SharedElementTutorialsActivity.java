package com.example.dell.cabs_pos20.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SharedElementTutorialsActivity extends AppCompatActivity {


    @Nullable
    @BindView(R.id.tutorialDescript)
    TextView tutText;
    @BindView(R.id.TutorialVid)
    VideoView tutorialVid;

    private MediaController mediaControls;
    private int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_tutorials);
        ButterKnife.bind(this);


        if (mediaControls == null) {
            mediaControls = new MediaController(this);
        }


        Intent intent = getIntent();
        String v520Intent = intent.getStringExtra(Constants.V520);
        String v675Intent = intent.getStringExtra(Constants.V675);
        String v265Intent = intent.getStringExtra(Constants.VE265);

        if (v520Intent == null && v675Intent == null) {

            String[] e265 = {
                    "http://169.254.28.11/cabs_pos/mobile/assets/CE_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/PRINTER_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/ND_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/CLEAR_BATCH.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/SETTLEMENT.MOV"};
            selector(v265Intent, e265);

        } else if (v520Intent == null && v265Intent == null) {
            String[] vx675 = {
                    "http://169.254.28.11/cabs_pos/mobile/assets/CE_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/PRINTER_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/ND_ERROR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/CLEAR_BATCH.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/SETTLEMENT.MOV"};
            selector(v675Intent, vx675);

        } else if (v675Intent == null && v265Intent == null) {

            String[] vx520 = {"",
                    "http://169.254.28.11/cabs_pos/mobile/assets/PRINTER_ERR.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/ERROR_ND.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/ERROR_BATCH.MOV",
                    "http://169.254.28.11/cabs_pos/mobile/assets/ERROR_SETTLEMENT.MOV"};
            if (vx520 != null)
                selector(v520Intent, vx520);
        }

    }

    //add url parameter
    public void selector(@NonNull String strSwitch, String[] verifone) {
        switch (strSwitch) {
            case Constants.CE:
                assert tutText != null;
                tutText.setText(R.string.error_ce);
                videoStreaming(verifone[0]);
                break;

            case Constants.PRINTER_ERROR:

                assert tutText != null;
                tutText.setText(R.string.print_error);
                videoStreaming(verifone[1]);
                break;

            case Constants.ND:
                assert tutText != null;
                tutText.setText(R.string.error_nd);
                videoStreaming(verifone[2]);
                break;

            case Constants.ERROR_BATCH:
                assert tutText != null;
                tutText.setText(R.string.error_batch);
                videoStreaming(verifone[3]);
                break;

            case Constants.SETTLEMENT:
                assert tutText != null;
                tutText.setText(R.string.settlement);
                videoStreaming(verifone[4]);

                break;

        }

    }

    public void videoStreaming(String verifone) {

        try {
            tutorialVid.setMediaController(mediaControls);
            tutorialVid.setVideoURI(Uri.parse(verifone));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        tutorialVid.requestFocus();
        tutorialVid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                //  progressDialog.dismiss();
                tutorialVid.seekTo(position);
                if (position == 0) {
                    tutorialVid.start();
                } else {
                    tutorialVid.pause();
                }
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", tutorialVid.getCurrentPosition());
        tutorialVid.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        tutorialVid.seekTo(position);
    }


}
