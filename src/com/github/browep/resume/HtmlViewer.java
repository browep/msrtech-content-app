package com.github.browep.resume;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static com.github.browep.resume.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 6/7/11
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlViewer extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int id = getResources().getIdentifier(getIntent().getExtras().getString(SCREEN_ID), "raw", APP_PACKAGE_NAME);
    InputStream inputStream =
        ResumeApplication.getApplication().getApplicationContext().getResources().openRawResource(id);
    setContentView(R.layout.html_viewer);
    TextView blurbView = (TextView) findViewById(R.id.html);
    try {
      blurbView.setText(Html.fromHtml(slurp(inputStream)));
    } catch (IOException e) {
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
}
