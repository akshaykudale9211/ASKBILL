package com.asksmt.asksmtv.services.interfaces;

import com.asksmt.asksmtv.model.Product;

import java.util.List;

public interface iProductService {
    public List<Product> getAllProducts();
    public Product updateProduct(Long id,Product productDetails);
    public Product addProduct(Product product);
    public void deleteProduct(Long id);
    public Product findProduct(Long id);


}
