package com.github.browep.handiquilter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.github.browep.handiquilter.Constants.SCREEN_ID;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 6/7/11
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Home extends Activity {

  Activity self = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);

    findViewById(R.id.about_us_button).setOnClickListener(new View.OnClickListener(){

      public void onClick(View view) {
        Intent intent = new Intent(self, HtmlViewer.class);
    intent.putExtra(SCREEN_ID, "aboutus");
    self.startActivity(intent);
      }
    });
  }

}
