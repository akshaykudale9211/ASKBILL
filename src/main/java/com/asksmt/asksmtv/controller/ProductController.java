package com.asksmt.asksmtv.controller;

import com.asksmt.asksmtv.model.Product;
import com.asksmt.asksmtv.services.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductServiceIMPL  serviceIMPL;

    @PostMapping("/add")
    public ResponseEntity<Product>addProduct(@RequestBody Product product){
        serviceIMPL.addProduct(product);
        return  new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product>products= serviceIMPL.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductByid(@PathVariable Long id){
        Optional<Product> product = Optional.ofNullable(serviceIMPL.findProduct(id));
        return product.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product updateProduct= serviceIMPL.updateProduct(id,product);
        return product != null? new ResponseEntity<>(updateProduct,HttpStatus.ACCEPTED):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        serviceIMPL.deleteProduct(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
