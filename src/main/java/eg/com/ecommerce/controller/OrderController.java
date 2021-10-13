package eg.com.ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eg.com.ecommerce.dto.OrderProductDto;
import eg.com.ecommerce.model.Order;
import eg.com.ecommerce.model.OrderProduct;
import eg.com.ecommerce.model.OrderStatus;
import eg.com.ecommerce.service.OrderProductService;
import eg.com.ecommerce.service.OrderService;
import eg.com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderProductService orderProductService;

	@PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Order createOrder(@RequestBody List<OrderProductDto> orderProducts) {
		
		logger.info("orderProducts {}" , orderProducts);

		// validate products availability and exists
		productService.validateProductsAvailability(orderProducts);

		// create order
		Order order = new Order();
		order.setOrderStatus(OrderStatus.CREATED);
		order.setCreatedOn(LocalDate.now());
        order.setCreatedBy("customerX");
        order = orderService.create(order);
        
        // adding product order and update product
		List<OrderProduct> orderProductList = new ArrayList<>();
		for (OrderProductDto dto : orderProducts) {
			orderProductList.add(orderProductService.create(
					new OrderProduct(order, 
							productService.getProduct(dto.getProduct().getId()), 
							dto.getQuantity())));
		}
		order.setOrderProducts(orderProductList);
		orderService.update(order);
		return order;
	}

}
