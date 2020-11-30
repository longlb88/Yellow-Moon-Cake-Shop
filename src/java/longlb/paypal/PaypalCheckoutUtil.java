/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.paypal;

import com.paypal.http.HttpResponse;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Long Le
 */
public class PaypalCheckoutUtil {
    private static final String PAYMENT_PRERARE_LINK = "https://www.sandbox.paypal.com/checkoutnow?token=";
    
    public static String createOrder(double totalMoney) throws IOException{
	Order order = null;
	// Construct a request object and set desired parameters
	// Here, OrdersCreateRequest() creates a POST request to /v2/checkout/orders
	OrderRequest orderRequest = new OrderRequest();
	orderRequest.checkoutPaymentIntent("CAPTURE");
	
	// Setup for payment page
	ApplicationContext applicationContext = new ApplicationContext()
						    .brandName("Yellow Moon Shop")
						    .cancelUrl("http://localhost:8084/J3LP0011_YellowMoon/view-cart")
						    .returnUrl("http://localhost:8084/J3LP0011_YellowMoon/checkout-cart");
	orderRequest.applicationContext(applicationContext);

	// Setup payment item
	List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
	purchaseUnits.add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalMoney + "")));
	orderRequest.purchaseUnits(purchaseUnits);
	
	//Create order request
	OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);

	// Call API with your client and get a response for your call
	HttpResponse<Order> response = Credentials.getClient().execute(request);
	
	// calling result() on the response
	order = response.result();
	
	// Construct payment link
	String paymentLink = PAYMENT_PRERARE_LINK + order.id();
	return paymentLink;
    }
    
    public static boolean captureOrder(String orderID) throws IOException{
	if (orderID != null){
	    if (!orderID.isEmpty()){
		OrdersCaptureRequest request = new OrdersCaptureRequest(orderID);

		// Call API with your client and get a response for your call
		HttpResponse<Order> response = Credentials.getClient().execute(request);

		String status = response.result().status();

		if (status.equals("COMPLETED")){
		    return true;
		}
	    }
	}
	return false;
    }
}
