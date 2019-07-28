package com.aman.vendingMachine;

import com.aman.vendingMachine.entity.Item;
import com.aman.vendingMachine.entity.VendingMachineRequest;
import com.aman.vendingMachine.entity.VendingMachineResponse;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class VendingApplicationIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        VendingMachineResponse candyResponse = this.testRestTemplate.postForObject("/api/deposit", new VendingMachineRequest("Candy"), VendingMachineResponse.class);
        Assert.assertEquals("OK", candyResponse.getStatus());

        VendingMachineResponse testResponse = this.testRestTemplate.postForObject("/api/deposit", new VendingMachineRequest("Soap"), VendingMachineResponse.class);
        Assert.assertEquals("Item not supported", testResponse.getStatus());

        VendingMachineResponse sodaResponse = this.testRestTemplate.postForObject("/api/deposit", new VendingMachineRequest("Soda"), VendingMachineResponse.class);
        Assert.assertEquals("OK", sodaResponse.getStatus());

        VendingMachineResponse candyMaxedOutResponse = this.testRestTemplate.postForObject("/api/deposit", new VendingMachineRequest("Candy"), VendingMachineResponse.class);
        Assert.assertEquals("Candy can't be added. Queue is at max, items: 1", candyMaxedOutResponse.getStatus());

        VendingMachineResponse candywithdrawResponse = this.testRestTemplate.postForObject("/api/withdraw", new VendingMachineRequest("Candy"), VendingMachineResponse.class);
        Assert.assertEquals("OK", candywithdrawResponse.getStatus());

        List<Item> getSupportedItems = this.testRestTemplate.getForObject("/api/getlist", List.class);
        Assert.assertEquals(3, getSupportedItems.size());
    }

}
