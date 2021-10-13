package eg.com.ecommerce.dto;

import eg.com.ecommerce.model.Product;

public class OrderProductDto {

	private Product product;
	private Integer quantity;

	public OrderProductDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderProductDto(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderProductDto [product=" + product + ", quantity=" + quantity + "]";
	}
	
	

}
