package com.fonehaus.app;

import com.fonehaus.app.data.ProductData;
import com.fonehaus.app.model.Product;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductSearchTest {

    @Test
    public void testSearchByName() {
        ArrayList<Product> results = ProductData.searchProducts("Galaxy");
        assertFalse(results.isEmpty());
        for (Product p : results) {
            assertTrue(p.getProductName().toLowerCase().contains("galaxy")
                    || p.getDescription().toLowerCase().contains("galaxy")
                    || p.getCategory().toLowerCase().contains("galaxy"));
        }
    }

    @Test
    public void testSearchWithCategoryFilter() {
        ArrayList<Product> results = ProductData.searchProducts("S25", "Phones");
        assertEquals(1, results.size());
        assertEquals("SAMSUNG GALAXY S25", results.get(0).getProductName());
    }

    @Test
    public void testSearchEmptyQueryReturnsAll() {
        ArrayList<Product> results = ProductData.searchProducts("");
        assertEquals(ProductData.getAllProducts().size(), results.size());
    }

    @Test
    public void testSearchNonMatchingQueryReturnsEmpty() {
        ArrayList<Product> results = ProductData.searchProducts("NonExistentItem12345");
        assertTrue(results.isEmpty());
    }
}
