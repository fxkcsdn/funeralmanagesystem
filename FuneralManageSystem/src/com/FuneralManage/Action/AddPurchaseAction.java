package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.GoodsService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.SupplierService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddPurchaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String goodsType;// 商品种类
	private String goodsName;// 品名
	private String supplierName;// 厂家
	private String operator;// 申请人
	private String createDate;// 创建日期
	private String memo;// 备注
	private String purchaseDetails;// 采购商品信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(String purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	/**
	 * 获取所有厂家信息
	 * @return 厂家名称，json数组
	 */
	public String getSuppliers()
	{
		SupplierService supplierService = new SupplierService(); 
		// 获取所有厂家信息
		returnString = supplierService.getSuppliers();
		return "getSuppliers";
	}
	
	/**
	 * 获取所有商品种类信息
	 * @return 商品种类
	 */
	public String getGoodsTypes()
	{
		GoodsService goodsService = new GoodsService();
		// 获取所有商品种类信息
		returnString = goodsService.getGoodsTypes();
		return "getGoodsTypes";
	}

	/**
	 * 获取品名
	 * @return 品名
	 */
	public String getGoodsNames()
	{
		GoodsService goodsService = new GoodsService();
		// 根据商品种类获取相应的品名
		returnString = goodsService.getGoodsNames(goodsType);
		return "getGoodsNames";
	}
	
	/**
	 * 获取单位
	 * @return 单位
	 */
	public String getUnit()
	{
		GoodsService goodsService = new GoodsService();
		// 根据品名、商品种类获取相应的单位
		returnString = goodsService.getUnit(goodsType, goodsName);
		return "getUnit";
	}
	
	/**
	 * 保存采购信息
	 * @return 返回保存结果
	 */
	public String savePurchaseInfo()
	{
		boolean result = false;
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		TransactionManager transactionManager = new TransactionManager();
		JSONObject jsonObject=JSONObject.fromObject(purchaseDetails);
		// 获取商品信息
		List<Map<String, String>> pds = jsonObject.getJSONArray("data");
		// 获取最新采购单号
		String purchaseNumber = NumberUtil.createPurchaseNumber(createDate);
		try {
			// 事务开始
			transactionManager.start();
			// 获取Connnection对象
			Connection conn = transactionManager.getConnection();
			// 增加一条采购单主信息
			purchaseMasterService.addPurchaseMaster(conn, purchaseNumber, supplierName, operator, createDate, memo);
			// 遍历商品信息
			for (int i = 0; i < pds.size(); i++)
			{
				Map<String, String> map = pds.get(i);
				// 添加一条商品信息
				purchaseDetailService.addPurchaseDetail(conn, map, purchaseNumber);
			}
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 回滚事务
			transactionManager.rollback();
			returnString = "false";
		}
		finally
		{
			// 关闭事务
			transactionManager.close();
		}
		return "getSaveResult";
	}
}
