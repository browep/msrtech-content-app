package com.github.browep.handiquilter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 7/31/11
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoreLocatorActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.banner_wrapper);
    LinearLayout wrapper = (LinearLayout) findViewById(R.id.wrapper_container);
    LinearLayout inflatedFieldLayout = (LinearLayout) View.inflate(this, R.layout.store_locator, null);
    wrapper.addView(inflatedFieldLayout);

  }
}
