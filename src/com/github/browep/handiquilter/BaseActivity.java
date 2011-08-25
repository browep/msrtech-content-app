package com.github.browep.handiquilter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 8/25/11
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseActivity extends Activity {
    protected final Activity self = this;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ImageView headerImageView = (ImageView) findViewById(R.id.header);
        if (headerImageView != null)
            headerImageView.setOnLongClickListener(headerLongClickListener);

    }

    View.OnLongClickListener headerLongClickListener = new View.OnLongClickListener() {
        public boolean onLongClick(View view) {
            AlertDialog alertDialog = new AlertDialog.Builder(BaseActivity.this)
                    .setTitle("How would you like to contact HandiQuilter?")
                    .setItems(new String[]{"Phone", "Email"}, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    String url = "tel:18776978458";
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                                    startActivity(intent);
                                    break;
                                case 1:
                                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"info@handiquilter.com"});
                                    emailIntent.setType("plain/text");
                                    Intent chooser = Intent.createChooser(emailIntent, "Send mail...");
                                    startActivity(chooser);
                                    break;
                            }

                        }
                    })
                    .create();
            alertDialog.show();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Share App");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        startActivity(new Intent(self, ShareActivity.class));
        return true;
    }
}
