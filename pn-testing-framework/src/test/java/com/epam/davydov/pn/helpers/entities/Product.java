package com.epam.davydov.pn.helpers.entities;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private Map<String, String> properties;

    public Product() {
        properties = new HashMap<>();
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getPropertyValue(String key) {
        return properties.get(key);
    }

    public int compareItemByProperty(Product product, String key) {
        return key.equals(Property.PRICE)
                ? compareProductByPrice(product)
                : this.getPropertyValue(key).compareTo(product.getPropertyValue(key));
    }

    private int compareProductByPrice(Product product) {
        int ourPrice = Integer.parseInt(
                this.getPropertyValue(Property.PRICE).replaceAll("[\\D\\s]", ""));
        int hisPrice = Integer.parseInt(
                product.getPropertyValue(Property.PRICE).replaceAll("[\\D\\s]", ""));
        if (ourPrice > hisPrice)
            return 1;
        if (ourPrice < hisPrice)
            return -1;
        return 0;
    }
    
    public boolean containsPropertyValue(String propertyValue){
    	return properties.containsValue(propertyValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product that = (Product) o;

        return properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
