package eg.com.ecommerce.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import eg.com.ecommerce.dto.OrderProductDto;
import eg.com.ecommerce.exception.ResourceNotFoundException;
import eg.com.ecommerce.model.Product;
import eg.com.ecommerce.repository.ProductRepository;
import eg.com.ecommerce.service.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);

	@Autowired
	private ProductRepository productRepository;

	public Iterable<Product> getProducts() {
		logger.info("get all available product");
		return productRepository.findAll();
	}

	public Product getProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void validateProductsAvailability(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts.stream()
				.filter(op -> !isProductAvailableAndExist(op.getProduct().getId())).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found or not available");
		}
	}

	private boolean isProductAvailableAndExist(Long productId) {
		Product product = getProduct(productId);
		return Objects.isNull(product) ? false : product.isAvailable() == true ? true : false;
	}
}
