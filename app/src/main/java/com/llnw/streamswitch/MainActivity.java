package com.llnw.streamswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.view.R5VideoView;

public class MainActivity extends AppCompatActivity {

    EditText streamName;
    Button switchButton;

    R5VideoView video;
    R5Configuration config;
    R5Connection connection;
    R5Stream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        streamName = findViewById(R.id.editText);
        streamName.setText("llama1");

        switchButton = findViewById(R.id.button);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchStreams();
            }
        });

        config = new R5Configuration(
                R5StreamProtocol.RTSP,
                "labs-webrtc-server.phx7.llnw.net",
                8554,
                "live",
                0.1f
        );
        config.setLicenseKey("IEPA-WSXQ-UAD6-GAEA");

        video = findViewById(R.id.subscribeView);
        connection = new R5Connection(config);
        stream = new R5Stream(connection);
        video.attachStream(stream);

        stream.play("llama1");
    }

    protected void switchStreams() {
        connection.switchStream("live", streamName.getText().toString());
    }
}
