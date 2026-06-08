package SampleApi;
	import static io.restassured.RestAssured.given;

	import org.testng.Assert;
	import org.testng.annotations.Test;

	import io.restassured.http.ContentType;
	import io.restassured.response.Response;

	public class PostRequestTest {

	    @Test
	    public void validateOrderDetails() {

	        String requestBody = """
	        {
	          "order_id": "12345",
	          "customer": {
	            "name": "Jane Smith",
	            "email": "janesmith@example.com",
	            "phone": "1-987-654-3210",
	            "address": {
	              "street": "456 Oak Street",
	              "city": "Metropolis",
	              "state": "NY",
	              "zipcode": "10001",
	              "country": "USA"
	            }
	          },
	          "items": [
	            {
	              "product_id": "A101",
	              "name": "Wireless Headphones",
	              "quantity": 1,
	              "price": 79.99
	            },
	            {
	              "product_id": "B202",
	              "name": "Smartphone Case",
	              "quantity": 2,
	              "price": 15.99
	            }
	          ],
	          "payment": {
	            "method": "credit_card",
	            "transaction_id": "txn_67890",
	            "amount": 111.97,
	            "currency": "USD"
	          },
	          "shipping": {
	            "method": "standard",
	            "cost": 5.99,
	            "estimated_delivery": "2024-11-15"
	          },
	          "order_status": "processing",
	          "created_at": "2024-11-07T12:00:00Z"
	        }
	        """;

	        Response response =
	                given()
	                    .contentType(ContentType.JSON)
	                    .body(requestBody)
	                .when()
	                    .post("http://echo.free.beeceptor.com/sample-request?author=beeceptor")
	                .then()
	                    .statusCode(200)
	                    .extract()
	                    .response();

	        response.prettyPrint();

	        // Customer Validation
	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.customer.name"),
	                "Jane Smith");

	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.customer.email"),
	                "janesmith@example.com");

	        // Payment Validation
	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.payment.method"),
	                "credit_card");

	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.payment.transaction_id"),
	                "txn_67890");

	        Assert.assertEquals(
	                response.jsonPath().getDouble("parsedBody.payment.amount"),
	                111.97);

	        // Product Validation
	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.items[0].product_id"),
	                "A101");

	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.items[0].name"),
	                "Wireless Headphones");

	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.items[1].product_id"),
	                "B202");

	        Assert.assertEquals(
	                response.jsonPath().getString("parsedBody.items[1].name"),
	                "Smartphone Case");
	    }
	}


