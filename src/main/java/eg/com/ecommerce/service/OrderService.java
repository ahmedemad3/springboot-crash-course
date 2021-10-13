package eg.com.ecommerce.service;

import eg.com.ecommerce.model.Order;

public interface OrderService {

	public Iterable<Order> getAllOrders();
	public Order create(Order order);
	public void update(Order order);
}
