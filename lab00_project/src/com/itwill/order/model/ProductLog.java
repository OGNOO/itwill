package com.itwill.order.model;

import java.time.LocalDateTime;

public class ProductLog {
	public static final class Entity {
		public static final String TBL_PRODUCT_LOG = "PRODUCT_LOG"; // 테이블명
		public static final String COL_LOG_ID = "LOG_ID"; // 로그번호
		public static final String COL_CUD_DATA = "CUD_DATA"; // 데이터 조작 유형
		public static final String COL_TIME = "TIME"; // 데이터 조작 시간
		public static final String COL_PRVS_CATEGORY = "PRVS_CATEGORY"; // 상품 카테고리 변경 전
		public static final String COL_AFTR_CATEGORY = "AFTR_CATEGORY"; // 상품 카테고리 변경 후
		public static final String COL_P_NAME = "P_NAME"; // 상품이름
		public static final String COL_PRVS_CURRENT_INVEN = "PRVS_CURRENT_INVEN"; // 현재 재고 변경 전
		public static final String COL_AFTR_CURRENT_INVEN = "AFTR_CURRENT_INVEN"; // 현재 재고 변경 후
		public static final String COL_PRVS_MIN_STK = "PRVS_MIN_STK"; // 최소확보재고 변경 전
		public static final String COL_AFTR_MIN_STK = "AFTR_MIN_STK"; // 최소확보재고 변경 후
		public static final String COL_PRVS_IMSQOB = "PRVS_IMSQOB"; // 최소확보재고 이하 일때 발주할 수량 변경 전
		public static final String COL_AFTR_IMSQOB = "AFTR_IMSQOB"; // 최소확보재고 이하 일때 발주할 수량 변경 후
		public static final String COL_PRVS_QNTTY_BNDL = "PRVS_QNTTY_BNDL"; // 묶음 수량당 낱개 수량 변경 전
		public static final String COL_AFTR_QNTTY_BNDL = "AFTR_QNTTY_BNDL"; // 묶음 수량당 낱개 수량 변경 후
		public static final String COL_CUD_MANAGER = "CUD_MANAGER"; // 데이터 변경 관리자

	}

	private int logId;
	private String cudData;
	private LocalDateTime time;
	private String prvsCategory;
	private String aftrCategory;
	private String pName;
	private int prvsCurrentInven;
	private int aftrCurrentInven;
	private int prvsMinStk;
	private int aftrMinStk;
	private int prvsImsqob;
	private int aftrImsqob;
	private int prvsQnttyBndl;
	private int aftrQnttyBndl;
	private String cudManager;

	public ProductLog() {
	}

	// 신규 저장하기 버튼 눌렀을때 남길로그
	public ProductLog(String cudData, String aftrCategory, String pName, int aftrCurrentInven, int aftrMinStk,
			int aftrImsqob, int aftrQnttyBndl, String cudManager
	) {
		this.cudData = cudData;
		this.aftrCategory = aftrCategory;
		this.pName = pName;
		this.aftrCurrentInven = aftrCurrentInven;
		this.aftrMinStk = aftrMinStk;
		this.aftrImsqob = aftrImsqob;
		this.aftrQnttyBndl = aftrQnttyBndl;
		this.cudManager = cudManager;
		
	}

	public ProductLog(int logId, String cudData, LocalDateTime time, String prvsCategory, String aftrCategory,
			String pName, int prvsCurrentInven, int aftrCurrentInven, int prvsMinStk, int aftrMinStk, int prvsImsqob,
			int aftrImsqob, int prvsQnttyBndl, int aftrQnttyBndl, String cudManager) {
		this.logId = logId;
		this.cudData = cudData;
		this.time = time;
		this.prvsCategory = prvsCategory;
		this.aftrCategory = aftrCategory;
		this.pName = pName;
		this.prvsCurrentInven = prvsCurrentInven;
		this.aftrCurrentInven = aftrCurrentInven;
		this.prvsMinStk = prvsMinStk;
		this.aftrMinStk = aftrMinStk;
		this.prvsImsqob = prvsImsqob;
		this.aftrImsqob = aftrImsqob;
		this.prvsQnttyBndl = prvsQnttyBndl;
		this.aftrQnttyBndl = aftrQnttyBndl;
		this.cudManager = cudManager;
	}

	public int getLogId() {
		return logId;
	}

	public String getCudData() {
		return cudData;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public String getPrvsCategory() {
		return prvsCategory;
	}

	public String getAftrCategory() {
		return aftrCategory;
	}

	public String getpName() {
		return pName;
	}

	public int getPrvsCurrentInven() {
		return prvsCurrentInven;
	}

	public int getAftrCurrentInven() {
		return aftrCurrentInven;
	}

	public int getPrvsMinStk() {
		return prvsMinStk;
	}

	public int getAftrMinStk() {
		return aftrMinStk;
	}

	public int getPrvsImsqob() {
		return prvsImsqob;
	}

	public int getAftrImsqob() {
		return aftrImsqob;
	}

	public int getPrvsQnttyBndl() {
		return prvsQnttyBndl;
	}

	public int getAftrQnttyBndl() {
		return aftrQnttyBndl;
	}

	public String getCudManager() {
		return cudManager;
	}

	@Override
	public String toString() {
		return "ProductLog [logId=" + logId + ", cudData=" + cudData + ", time=" + time + ", prvsCategory="
				+ prvsCategory + ", aftrCategory=" + aftrCategory + ", pName=" + pName + ", prvsCurrentInven="
				+ prvsCurrentInven + ", aftrCurrentInven=" + aftrCurrentInven + ", prvsMinStk=" + prvsMinStk
				+ ", aftrMinStk=" + aftrMinStk + ", prvsImsqob=" + prvsImsqob + ", aftrImsqob=" + aftrImsqob
				+ ", prvsQnttyBndl=" + prvsQnttyBndl + ", aftrQnttyBndl=" + aftrQnttyBndl + ", cudManager=" + cudManager
				+ "]";
	}
}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */