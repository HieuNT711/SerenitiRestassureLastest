package example.steps;

import example.config.GlobalVariables;
import example.core.BaseSteps;
import example.dto.request.orderRequest.*;
import example.dto.request.tokenRequest.Oauth2TokenRequest;
import example.dto.response.orderResponse.OrderResponse;
import example.dto.response.tokeResponse.Oauth2TokenResponse;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;

import java.util.ArrayList;
import java.util.List;

public class SandboxPaypalSteps extends BaseSteps {

    @Given("")
    public void generateAccessTokenStep() {

    }

    @Given("I create Order")
    public void orderStep() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIntent("CAPTURE");
        List<Item> items = new ArrayList<>();
        List<PurchaseUnit> purchaseUnits = new ArrayList<>();
        Item item = new Item();
        UnitAmount unitAmount = new UnitAmount();
        unitAmount.setValue("100");
        unitAmount.setCurrency_code("USD");
        items.add(item);

        item.setDescription("Green XL");
        item.setName("T-Shirt2");
        item.setQuantity("1");
        item.setUnit_amount(unitAmount);


        Amount.Breakdown.ItemTotal itemTotal = new Amount.Breakdown.ItemTotal();
        itemTotal.setValue("100");
        itemTotal.setCurrency_code("USD");
        Amount.Breakdown breakdown = new Amount.Breakdown();
        breakdown.setItem_total(itemTotal);

        Amount amount = new Amount();
        amount.setValue("100");
        amount.setCurrency_code("USD");
        amount.setBreakdown(breakdown);

        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setAmount(amount);
        purchaseUnit.setItems(items);
        purchaseUnits.add(purchaseUnit);

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setCancel_url("https://example.com/return");
        applicationContext.setReturn_url("https://example.com/cancel");

        orderRequest.setPurchase_units(purchaseUnits);
        orderRequest.setApplication_context(applicationContext);
        OrderResponse orderResponse = sandboxPaypalService.createOrderResponse(orderRequest);
        Serenity.setSessionVariable("ORDER_ID").to(orderResponse.getId());
    }

    @SneakyThrows
    @Given("I generate access token")
    public void generateToken() {
        Oauth2TokenRequest oauth2TokenRequest = new Oauth2TokenRequest();
        oauth2TokenRequest.setGrant_type("client_credentials");
        Oauth2TokenResponse oauth2TokenResponse = baseService.oauth2TokenResponse(oauth2TokenRequest);
        scenarioContext.setContext(GlobalVariables.TOKEN, oauth2TokenResponse.getAccess_token());
        scenarioContext.setContext(GlobalVariables.TOKEN_TYPE, oauth2TokenResponse.getToken_type());

    }

    @Given("I get Order info")
    public void getOrder() {
        sandboxPaypalService.getOrderResponse();
    }

}
