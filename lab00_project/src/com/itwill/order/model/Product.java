package com.itwill.order.model;

public class Product {
	public static final class Entity {
		public static final String TBL_PRODUCT = "PRODUCT"; // 테이블명
		public static final String COL_PRODUCT_CATEGORY = "PRODUCT_CATEGORY"; // 상품 카테고리
		public static final String COL_PRODUCT_NAME = "PRODUCT_NAME"; // 상품명
		public static final String COL_CURRENT_INVEN = "CURRENT_INVEN"; // 현재재고
		public static final String COL_MIN_STK = "MIN_STK"; // 최소확보재고
		public static final String COL_IMSQOB = "IMSQOB"; // 최소확보재고 이하 일때 발주할 수량
		public static final String COL_QNTTY_BNDL = "QNTTY_BNDL"; // 묶음 수량당 낱개 수량
//		public static final String COL_EXPCT_STCAR = "CURRENT_INVEN+(IMSQOB*QNTTY_BNDL)"; // 묶음 수량당 낱개 수량
	}

	private String productCategory;
	private String productName;
	private int currentInven;
	private int minStk;
	private int imsqob;
	private int qnttyBndl;
//	private int expctStcar;

	public Product() {
	}

//	public Product(String productCategory, String productName, int currentInven, int imsqob) {
//		this.productCategory = productCategory;
//		this.productName = productName;
//		this.currentInven = currentInven;
//		this.imsqob = imsqob;
//	}

	public Product(String productCategory, String productName, int currentInven, int minStk, int imsqob,
			int qnttyBndl) {
		this.productCategory = productCategory;
		this.productName = productName;
		this.currentInven = currentInven;
		this.minStk = minStk;
		this.imsqob = imsqob;
		this.qnttyBndl = qnttyBndl;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCurrentInven() {
		return currentInven;
	}

	public void setCurrentInven(int currentInven) {
		this.currentInven = currentInven;
	}

	public int getMinStk() {
		return minStk;
	}

	public void setMinStk(int minStk) {
		this.minStk = minStk;
	}

	public int getImsqob() {
		return imsqob;
	}

	public void setImsqob(int imsqob) {
		this.imsqob = imsqob;
	}

	public int getQnttyBndl() {
		return qnttyBndl;
	}

	public void setQnttyBndl(int qnttyBndl) {
		this.qnttyBndl = qnttyBndl;
	}

	@Override
	public String toString() {
		return "Product [productCategory=" + productCategory + ", productName=" + productName + ", currentInven="
				+ currentInven + ", minStk=" + minStk + ", imsqob=" + imsqob + ", qnttyBndl=" + qnttyBndl;
	}

}
