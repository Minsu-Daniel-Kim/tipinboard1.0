package com.corping.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class Broadcast_SMS extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Toast.makeText(context, "onReceive() 호출됨", 3000).show();

		Intent sendIntent = new Intent(context, MainActivity_GetStarted2.class);

		Log.d("MyReceiver", "onReceiver");

		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessages[] = new SmsMessage[messages.length];

		for (int i = 0; i < messages.length; i++) {

			smsMessages[i] = SmsMessage.createFromPdu((byte[]) messages[i]);

		}

		String message = smsMessages[0].getMessageBody().toString();
		String number = smsMessages[0].getOriginatingAddress();

		sendIntent.putExtra("verification_number", message);
		sendIntent.putExtra("verification_num",
				intent.getStringExtra("verification_num"));
		sendIntent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		sendIntent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);


		if ((intent.getStringExtra("verification_num")) != null) {

			context.startActivity(sendIntent);

		}
	}

}
