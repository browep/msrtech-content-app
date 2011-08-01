package com.github.browep.handiquilter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.github.browep.handiquilter.Constants.APP_PACKAGE_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 7/31/11
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainingVideos extends ListActivity {
  HomeArrayAdapter dataAdapter;
  private static Map[] videos;

  static {
    videos = new Map[]{
        ImmutableMap.of("label", "Super Easy Maintenance ~ Clean & Oil", "image", "easy",
            "url", "http://www.youtube.com/watch?v=BhJxJFedf3Y"),
        ImmutableMap.of("label", "Open Toe Hopping Foot Installation", "image", "toe",
            "url", "http://www.youtube.com/watch?v=oKgXChLZCVs"),
        ImmutableMap.of("label", "Using Your Handi Quilter", "image", "using",
            "url", "http://www.youtube.com/watch?v=OZgvHuaAdhE"),
        ImmutableMap.of("label", "Loading Your Quilter", "image", "loading", "url",
            "http://www.youtube.com/watch?v=OZgvHuaAdhE")
    };

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.training_videos);


    dataAdapter = new HomeArrayAdapter(this,
        R.layout.video_listitem,
        R.id.label,
        videos
    );

    setListAdapter(dataAdapter);
  }


  public class HomeArrayAdapter extends ArrayAdapter<Map> {
    private Map[] objects;



    public HomeArrayAdapter(Context context, int listItemResource, int listItemTextView, Map[] objects) {
      super(context, listItemResource, listItemTextView, objects);
      this.objects = objects;

    }

    @Override
    public Map getItem(int position) {
      return objects == null ? null : objects[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View row = super.getView(position, convertView, parent);
      if (convertView == null) {
        TextView textView = (TextView) row.findViewById(R.id.name);
        textView.setText((CharSequence) getItem(position).get("label"));
        ImageView imageView = (ImageView) row.findViewById(R.id.image);
        imageView.setImageResource(getResources().getIdentifier((String) getItem(position).get("image"), "drawable", APP_PACKAGE_NAME)
        );
      } else
        row = convertView;
      return (row);
    }
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse((String) videos[position].get("url")));
    startActivity(i);
  }

}
