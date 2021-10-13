package eg.com.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eg.com.ecommerce.model.OrderProduct;
import eg.com.ecommerce.repository.OrderProductRepository;
import eg.com.ecommerce.service.OrderProductService;

@Service
@Transactional
public class OrderProductServiceImp implements OrderProductService {
	
	@Autowired
	private OrderProductRepository orderProductRepository;

	@Override
    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

}
