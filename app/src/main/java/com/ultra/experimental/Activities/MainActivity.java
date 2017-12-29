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
import com.ultra.experimental.R;
import com.ultra.experimental.Utils.O;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
	{
	private static int i=0;
	private Receiver receiver= new Receiver();

	private class Receiver extends BroadcastReceiver
		{
		@Override
		public void onReceive(Context context,Intent intent)
			{
			String msg= "Получено "+ intent.getIntExtra(O.mapKeys.extra.BROADCAST_STATIC_INT,-1);
			Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
			}
		}

	public void sendSMS(String number,String msg)
		{
		SmsManager smsManager= SmsManager.getDefault();
		Intent intentDelivery= new Intent(O.ACTION);
		intentDelivery.putExtra(O.mapKeys.extra.BROADCAST_STATIC_INT,i++);
		Intent intentSent= new Intent(O.ACTION);
		intentSent.putExtra(O.mapKeys.extra.BROADCAST_STATIC_INT,i++);
		PendingIntent pendingIntentDelivery= PendingIntent.getBroadcast(this,0,intentDelivery,0);
		PendingIntent pendingIntentSent= PendingIntent.getBroadcast(this,0,intentSent,0);
//		smsManager.sendTextMessage(number,null,msg,pendingIntentSent,pendingIntentDelivery);

		ArrayList<String> parts = smsManager.divideMessage(msg);
		ArrayList<PendingIntent> delivery= new ArrayList<>();
		delivery.add(pendingIntentDelivery);
		ArrayList<PendingIntent> sent= new ArrayList<>();
		sent.add(pendingIntentSent);
		smsManager.sendMultipartTextMessage(number, null, parts, sent, delivery);
		}

	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		String number="+79180946116";
		String msg="Длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное длинное" +
				" длинное длинное длинное длинное длинное длинное длинное сообщение";

		Button btn= findViewById(R.id.btn);
		btn.setOnClickListener(view -> sendSMS(number,msg) );

		IntentFilter filter= new IntentFilter(O.ACTION);
		registerReceiver(receiver,filter);
		}

	@Override
	protected void onDestroy()
		{
		super.onDestroy();
		unregisterReceiver(receiver);
		}
	}
