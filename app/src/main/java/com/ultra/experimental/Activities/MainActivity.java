package com.ultra.experimental.Activities;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.ultra.experimental.Utils.O;
import io.reactivex.Observable;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import com.ultra.experimental.R;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
	{
	VideoView player;
	String url= "http://cdndl.zaycev.net/117410/4544980/diskoteka_avariya_-_novogodnyaya_2012_%28zaycev.net%29.mp3";

	private void requestAudioUri()
		{
		Intent content= new Intent(Intent.ACTION_GET_CONTENT);
		content.setType("audio/*");
		startActivityForResult(content,150);
		}
	private void playInVideoView(Uri uri)
		{
		player.setVideoURI(uri);
		player.setMediaController(new MediaController(this) );
		player.start();
		}

	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

//		EasyVideoPlayer player=findViewById(R.id.video);
		player= findViewById(R.id.video);
//		player.setCallback(new Callback() );
//		player.setSource(Uri.parse(url) );

		Button btn= findViewById(R.id.btn);
		btn.setOnClickListener(v ->
				{
				v.setVisibility(View.INVISIBLE);
				playInVideoView(Uri.parse(url) );
//				requestAudioUri();
				} );
		}

	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
		{
		if(requestCode==150 && data!=null)
			playInVideoView(data.getData() );
		}
	}
