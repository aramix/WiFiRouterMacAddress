package com.phonegap.plugin.wifimacaddress;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
//import android.net.wifi.WifiInfo;

/**
 * The Class WiFiRouterMacAddressPlugin.
 */
public class WiFiRouterMacAddressPlugin extends Plugin {

    @Override
    public boolean isSynch(String action) {
        if (action.equals("getBSSID")) {
            return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see org.apache.cordova.api.Plugin#execute(java.lang.String, org.json.JSONArray, java.lang.String)
     */
    @Override
    public PluginResult execute(String action, JSONArray args, String callbackId) {
        PluginResult result = null;


        if (action.equals("getBSSID")) {

            String wifiRouterMacAddress = this.getBSSID();
 
            if (wifiRouterMacAddress != null) {
                JSONObject JSONresult = new JSONObject();
                try {
                    JSONresult.put("wifimac", wifiRouterMacAddress);
                    result = new PluginResult(PluginResult.Status.OK, JSONresult);
                } catch (JSONException jsonEx) {
         
                    result = new PluginResult(PluginResult.Status.JSON_EXCEPTION);
                }

            }
        }
        
        return result;
    }

    /**
     * Gets the mac address.
     * 
     * @return the mac address
     */
    private String getBSSID() {
        String wifiRouterMacAddress = null;
        WifiManager wifi = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
		/* // Get WiFi status
		WifiInfo info = wifi.getConnectionInfo();
        wifiRouterMacAddress = info.getBSSID().toString(); */
		
		List<ScanResult> results = wifi.getScanResults();
		ScanResult bestSignal = null;
		for (ScanResult result : results) {
		  if (bestSignal == null
			  || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
			bestSignal = result;
		}
		
		wifiRouterMacAddress = bestSignal.BSSID.toString();
		
        if (wifiRouterMacAddress == null || wifiRouterMacAddress.length() == 0) {
            wifiRouterMacAddress = "00:00:00:00:00:00";
        }

        return wifiRouterMacAddress;
    }
}