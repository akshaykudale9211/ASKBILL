package com.asksmt.asksmtv.controller;

import com.asksmt.asksmtv.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
public class WebPageController {
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring Boot!");
        return "index"; // Corresponds to templates/index.html
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "addProduct"; // Corresponding template: templates/add-product.html
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product"; // This corresponds to add-product.html
    }

    @PostMapping(value = "/add-product")
    public String addProduct(@ModelAttribute Product product, Model model) {
        // Simulating saving the product
        model.addAttribute("message", "Product added successfully!");
        return "add-product";
    }

    @GetMapping("/product-list")
    public String listProducts(Model model) {
        String apiUrl = "http://localhost:8080/v1/product/all";
        RestTemplate restTemplate = new RestTemplate();

        Product[] products = restTemplate.getForObject(apiUrl, Product[].class);

        // Convert array to list and sort alphabetically by productName
        List<Product> productList = Arrays.asList(products);
        productList.sort(Comparator.comparing(Product::getProductName));

        // Pass the sorted product list to the Thymeleaf template
        model.addAttribute("products", productList);
        return "product-list";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        String apiUrl = "http://localhost:8080/v1/product/" + id;
        RestTemplate restTemplate = new RestTemplate();

        // Fetch product details
        Product product = restTemplate.getForObject(apiUrl, Product.class);

        // Pass product details to the edit page
        model.addAttribute("product", product);
        return "edit-product"; // Corresponds to edit-product.html
    }

    @PostMapping("/v1/product/update")
    public String updateProduct(Product product) {
        String apiUrl = "http://localhost:8080/v1/product/update";
        RestTemplate restTemplate = new RestTemplate();

        // Send updated product data to the API
        restTemplate.postForObject(apiUrl, product, Void.class);

        // Redirect back to the product list
        return "redirect:/product/list";
    }

}
