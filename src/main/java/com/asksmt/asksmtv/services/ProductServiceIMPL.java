package com.asksmt.asksmtv.services;

import com.asksmt.asksmtv.model.Product;
import com.asksmt.asksmtv.repository.iProductRepository;
import com.asksmt.asksmtv.services.interfaces.iProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceIMPL implements iProductService {

    @Autowired
    private iProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.sort(Product.class));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setProductName(productDetails.getProductName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow();

    }
}
