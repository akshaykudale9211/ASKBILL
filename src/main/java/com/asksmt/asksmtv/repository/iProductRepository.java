package com.asksmt.asksmtv.repository;

import com.asksmt.asksmtv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iProductRepository  extends JpaRepository<Product,Long> {
}
