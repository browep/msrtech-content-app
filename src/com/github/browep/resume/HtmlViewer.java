package com.github.browep.resume;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import static com.github.browep.resume.Constants.APP_PACKAGE_NAME;
import static com.github.browep.resume.Constants.SCREEN_ID;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 6/7/11
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlViewer extends Activity {
  String url;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String screenId = getIntent().getExtras().getString(SCREEN_ID);
    int id = getResources().getIdentifier(screenId, "raw", APP_PACKAGE_NAME);
    InputStream inputStream =
        ResumeApplication.getApplication().getApplicationContext().getResources().openRawResource(id);
    setContentView(R.layout.html_viewer);
    TextView blurbView = (TextView) findViewById(R.id.html);
    try {
      blurbView.setText(Html.fromHtml(slurp(inputStream)));
//      Linkify.addLinks(blurbView, Linkify.ALL);
    } catch (IOException e) {
    }

    url = (String) ResumeApplication.getApplication().getScreens().get(screenId).get("url");
    if (url != null) {
      Button seeItButton = (Button) findViewById(R.id.see_it_button);
      seeItButton.setVisibility(View.VISIBLE);
    }
  }


  public static String slurp(InputStream in) throws IOException {
    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    for (int n; (n = in.read(b)) != -1;) {
      out.append(new String(b, 0, n));
    }
    return out.toString();
  }

  public void seeItOnClick(View view) {
    Toast.makeText(this, "see it on click", 1000);
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(url));
    startActivity(i);
  }
}
