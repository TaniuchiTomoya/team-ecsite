package jp.co.internous.quest.model.domain;

import java.sql.Timestamp;

import jp.co.internous.quest.model.form.CartForm;

public class TblCart {

	private int id;
	private int userId;
	private int productId;
	private long productCount;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public TblCart() {}
	
	public TblCart(CartForm f) {
		userId = f.getUserId();
		productId = f.getProductId();
		productCount = f.getProductCount(); 
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public long getProductCount() {
		return productCount;
	}
	
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}