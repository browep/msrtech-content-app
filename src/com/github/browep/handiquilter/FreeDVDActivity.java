package com.github.browep.handiquilter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 7/30/11
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class FreeDVDActivity extends BaseActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.banner_wrapper);
    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    LinearLayout wrapper = (LinearLayout) findViewById(R.id.wrapper_container);
    View inflated = inflater.inflate(R.layout.free_dvd, wrapper, true);
    List fields = ImmutableList.of(
        ImmutableMap.of("id", "name", "label", "Name"),
        ImmutableMap.of("id", "address1", "label", "Address 1"),
        ImmutableMap.of("id", "address2", "label", "Address 2"),
        ImmutableMap.of("id", "city", "label", "City"),
        ImmutableMap.of("id", "state", "label", "State"),
        ImmutableMap.of("id", "phone", "label", "Phone"),
        ImmutableMap.of("id", "email", "label", "Email")
    );

    LinearLayout fieldsContainer = (LinearLayout) inflated.findViewById(R.id.field_container);

    for (Object obj : fields) {
      Map field = (Map) obj;
      LinearLayout inflatedFieldLayout = (LinearLayout) View.inflate(this, R.layout.text_field, null);

      ((TextView) inflatedFieldLayout.findViewById(R.id.label)).setText((CharSequence) field.get("label"));
      fieldsContainer.addView(inflatedFieldLayout);
      inflatedFieldLayout.setTag(R.id.form_id,field.get("id"));

    }

  }

  public void onSubmit(View view){

  }


}
