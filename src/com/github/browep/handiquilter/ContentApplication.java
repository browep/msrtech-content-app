package com.github.browep.handiquilter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.github.browep.handiquilter.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: 6/3/11
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContentApplication extends Application{
  private static ContentApplication application;
  Map<String,Map> screens = new HashMap<String,Map>();

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;

    InputStream is = getApplicationContext().getResources().openRawResource(R.raw.data);
    try {
      JSONObject jsonObject = new JSONObject(new JSONTokener(slurp(is)));
      Iterator<String> keyIterator = jsonObject.keys();
      while (keyIterator.hasNext()){
        String key = keyIterator.next();
        JSONObject screenObj = jsonObject.getJSONObject(key);
        Map<String,Object> screen = new HashMap<String,Object>();
        screen.put(TITLE, screenObj.getString(TITLE));
        if(screenObj.has(ITEMS)){
          JSONArray itemsArray = screenObj.getJSONArray(ITEMS);
          List<String> items = new LinkedList<String>();
          for(int i=0; i<itemsArray.length();i++){
            items.add(itemsArray.getString(i));
          }
          screen.put(ITEMS,items);
        }
        if(screenObj.has(URL)){
          screen.put(URL,screenObj.get(URL));
        }
        screens.put(key, screen);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  public static ContentApplication getApplication(){
    return  application;
  }

  public Map<String,Map> getScreens() {
    return screens;
  }

  public static boolean onCreateOptionsMenu(Menu menu) {
    menu.add("Contact Us");
    return true;
  }

  public static boolean onMenuItemSelected(Context context ,int featureId, MenuItem item){
    Intent intent = new Intent(context, HtmlViewer.class);
    intent.putExtra(SCREEN_ID, "contact");
    context.startActivity(intent);
    return true;
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
