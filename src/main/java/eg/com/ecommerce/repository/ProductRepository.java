package eg.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eg.com.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
