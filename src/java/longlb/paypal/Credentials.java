/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

/**
 *
 * @author Long Le
 */
public class Credentials {
    static String clientID = "AaPd0lVFQGZF-3ELR1G_mWVuHiYqwRZK25VP66ybictkVZWGBGdp9GcbGJ8Ny0-OeOSMhRgdViezemEp";
    static String clientSecret = "EL_ckZ_XD9biyrqUrwkmka1eoqZD7AHDnxwVgJS2GuZD5F2NicBhhee9xdD3kt9cdNVCliNOXffHj95L";
    
    //Create a sandbox enviroment
    private static PayPalEnvironment enviroment = new PayPalEnvironment.Sandbox(clientID, clientSecret);
    
    //get client
    public static PayPalHttpClient getClient(){
	return new PayPalHttpClient(enviroment);
    }
}
