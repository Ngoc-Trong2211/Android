    package com.example.lab10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

    public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle bundle = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";
        if (bundle != null){
            Object[] smsExtra = (Object[]) bundle.get("pdus");
            for (int i=0; i<smsExtra.length; i++){
                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
                body = sms.getMessageBody();
                address = sms.getOriginatingAddress();
                message += "Co mot tin nhan tu " + address + "\n" + body + "Vua gui den";
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

//    public void processReceive(){
//        Bundle bundle = intent.getExtras();
//        String message = "";
//        String body = "";
//        String address = "";
//        if (bundle != null){
//            Object[] smsExtra = (Object[]) bundle.get("pdus");
//            for (int i=0; i<smsExtra.length; i++){
//                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
//                body = sms.getMessageBody();
//                address = sms.getOriginatingAddress();
//                message += "Co mot tin nhan tu " + address + "\n" + body + "Vua gui den";
//            }
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//        }
//    }
}