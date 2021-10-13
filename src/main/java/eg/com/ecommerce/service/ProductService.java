package eg.com.ecommerce.service;

import java.util.List;

import eg.com.ecommerce.dto.OrderProductDto;
import eg.com.ecommerce.model.Product;

public interface ProductService {
	
	public Iterable<Product> getProducts();
	public Product getProduct(long id);
	public Product save(Product product);
	public void validateProductsAvailability(List<OrderProductDto> orderProducts);

}
