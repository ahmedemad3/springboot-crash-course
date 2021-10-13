package eg.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eg.com.ecommerce.model.OrderProduct;
import eg.com.ecommerce.model.OrderProductPK;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK>{

}
