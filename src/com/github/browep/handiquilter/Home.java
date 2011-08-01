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
  }

  public void storeLocatorOnClick(View view) {
    Intent intent = new Intent(self, StoreLocatorActivity.class);
    self.startActivity(intent);
  }

  public void trainingOnClick(View view) {
    Intent intent = new Intent(self, TrainingVideos.class);
    self.startActivity(intent);
  }

  public void freeDvdOnClick(View view) {
    Intent intent = new Intent(self, FreeDVDActivity.class);
    self.startActivity(intent);
  }

  public void aboutUsOnClick(View view) {
    Intent intent = new Intent(self, HtmlViewer.class);
    intent.putExtra(SCREEN_ID, "aboutus");
    self.startActivity(intent);
  }


}
