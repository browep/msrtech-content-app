package com.github.browep.resume;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.browep.resume.Constants.SCREEN_ID;
import static com.github.browep.resume.Constants.TITLE;

public class Main extends ListActivity {
  private HomeArrayAdapter dataAdapter;
  private Map<String, Map> screens;
  private String[] ids;
  private String screenId;
  private Map screenMap;


  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    screenId = getIntent().getExtras() != null ? getIntent().getExtras().getString(SCREEN_ID) : null;
    if (screenId == null)
      screenId = "home";

    screens = ResumeApplication.getApplication().getScreens();
    List<String> titles = new ArrayList<String>();


    screenMap = screens.get(screenId);
    List<String> items = (List<String>) screenMap.get("items");
    ids = items.toArray(new String[0]);
    for (String itemId : ids) {
      titles.add((String) screens.get(itemId).get(TITLE));
    }

    dataAdapter = new HomeArrayAdapter(this,
        R.layout.default_listitem,
        R.id.label,
        titles.toArray(new String[titles.size()]));

    setListAdapter(dataAdapter);
  }

  public class HomeArrayAdapter extends ArrayAdapter<String> {
    private String[] titles;

    public HomeArrayAdapter(Context context, int listItemResource, int listItemTextView, String[] titles) {
      super(context, listItemResource, listItemTextView, titles);
      this.titles = titles;

    }

    @Override
    public String getItem(int position) {
      return titles == null ? null : titles[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View row = super.getView(position, convertView, parent);
      if (convertView == null) {
        TextView textView = (TextView) row.findViewById(R.id.label);
        textView.setText(getItem(position));
      } else
        row = convertView;
      return (row);
    }
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent intent = new Intent(this, screens.get(ids[position]).containsKey("items") ? Main.class : HtmlViewer.class);
    intent.putExtra(SCREEN_ID, ids[position]);
    this.startActivity(intent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return ResumeApplication.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    return ResumeApplication.onMenuItemSelected(this,featureId,item);
  }
}
