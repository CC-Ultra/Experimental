package com.ultra.experimental.Activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ultra.experimental.R;
import com.ultra.experimental.Utils.O;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
	{
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		Button btn= findViewById(R.id.btn);
		btn.setOnClickListener(view -> new IntentIntegrator(this).initiateScan() );
		}
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
		{
		super.onActivityResult(requestCode,resultCode,data);
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result!=null)
			{
			if(result.getContents() == null)
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
			}
		else
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
