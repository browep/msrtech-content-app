package com.github.browep.handiquilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 8/25/11
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShareActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);
    }

    public void doSMS(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", "Check out this Android app! http://q.qreatebuzz.com/?i=14199");
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
    }

    public void doEmail(View view) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out the HandiQuilter App!");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Follow this link to download the HandiQuilter app...");
        Intent chooser = Intent.createChooser(emailIntent, "Send mail...");
        startActivity(chooser);
    }


}
