package eg.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eg.com.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
