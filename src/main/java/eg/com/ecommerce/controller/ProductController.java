package eg.com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eg.com.ecommerce.model.Product;
import eg.com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = { "", "/" })
    public Iterable<Product> getProducts() {
        return productService.getProducts();
    }
	
	
	@PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)    
	public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
