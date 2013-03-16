/*
 * WifiRouterMacAddress
 * Implements the javascript access to the cordova plugin for retrieving the wireless router mac address. Returns 0 if not running on Android
 * @author Aram Bayadyan
 */

/**
 * @return the wifi router mac address class instance
 */
var WifiRouterMacAddress = function() {
};

/**
 * Returns the wifi router mac address of the device. Return a 00:00:00:00:00:00 for
 * emulator based runtime or just PC web
 * 
 * @param successCallback
 *            The callback which will be called when directory listing is
 *            successful
 * @param failureCallback
 *            The callback which will be called when directory listing encouters
 *            an error
 */
WifiRouterMacAddress.prototype.getBSSID = function(successCallback, failureCallback) {
	return cordova.exec(successCallback, failureCallback, 'WifiRouterMacAddress',
			'getBSSID', [], false);
};

window.wifimacaddress = new WifiRouterMacAddress();