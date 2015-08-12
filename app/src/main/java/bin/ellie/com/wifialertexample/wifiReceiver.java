package bin.ellie.com.wifialertexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class wifiReceiver extends BroadcastReceiver {

    /*
    * This class extends BroadcastReceiver and gets broadcast when network connectivity has changed.
    * When the change is that the user run out router range or for any reason the Wifi has disconnected,
    * The user will be notice.
    * Created by Avi Rozenblum
     */

    public wifiReceiver() {
    	
    }

    // Return true if wifi connected after the connectivity changed (which means that the user turn on wifi).
    // Parameters: Context - to get connectivity system service
    public boolean IsWiFiConnected(Context context) {
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo ni = cm.getActiveNetworkInfo();
    	if( ni != null && ni.getType() == ConnectivityManager.TYPE_WIFI) {    	
    		return true;
    	} else {
    		return false;
    	}
    }

    // Return true if network(3G) connected after the connectivity changed (which means that the user turn off wifi).
    // Parameters: Context - to get connectivity system service
    public boolean isNetConnected(Context context) {
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo ni = cm.getActiveNetworkInfo();
    	if( ni != null && ni.getType() == ConnectivityManager.TYPE_MOBILE) {    	
    		return true;
    	} else {
    		return false;
    	}     
    }

    // Check if wifi got disconnected and print toast to user.
    public void onReceive(Context context, Intent intent) {
        String s = intent.getAction();
        if (s.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (!IsWiFiConnected(context)) {
                Toast.makeText(context, "wifi not connected", Toast.LENGTH_LONG).show();
            }
            return;
        }
    }
}
