package com.FuneralManage.Action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.ReceiptService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String purchaseNumber;// 采购编号
	private String supplierName;// 厂家名称
	private String startTime;// 起始时间
	private String endTime;// 截止时间
	private String pageNum;// 当前页数
	private String pageSize;// 每页记录数
	private String receipts;// 收货信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
	}

	/**
	 * 获取已通过审核且未结案的采购单主信息中的厂家信息
	 * @return
	 */
	public String getSuppliersInPurchases()
	{
		PurchaseMasterService purchaseManageService = new PurchaseMasterService();
		// 获取已通过审核且未结案的采购单主信息中的厂家信息
		returnString = purchaseManageService.getSuppliersInAuditedPurchases();
		return "getSuppliers";
	}
	
	/**
	 * 获取所有已通过审核且未结案的采购单主信息
	 * @return 采购单主信息
	 */
	public String getPurchases()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 根据查询条件获取采购单主信息
		returnString = purchaseMasterService.getPurchasesHaveAudit(purchaseNumber, supplierName, startTime, endTime, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return "getPurchases";
	}
	
	/**
	 * 获取总页数
	 * @return 总页数
	 */
	public String getPageCount()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 查询分页数
		returnString = purchaseMasterService.getPageCountHaveAudit(purchaseNumber, supplierName, startTime, endTime, Integer.parseInt(pageSize));
		return "getPageCount";
	}
	
	/**
	 * 获取采购明细信息
	 * @return 采购明细信息
	 */
	public String getPurchaseDetails()
	{
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		ReceiptService receiptService = new ReceiptService();
		JSONArray purchaseDetailsArray = null;
		JSONArray goodsNameAndAmountArray = null;
		JSONArray newArray = new JSONArray();
		// 根据采购编号获取采购明细信息
		String purchaseDetails = purchaseDetailService.getPurchaseDetails(purchaseNumber);
		// 根据采购编号计算每个商品已到货数量
		String goodsNameAndAmount = receiptService.getHaveGotGoods(purchaseNumber);
		try {
			purchaseDetailsArray = new JSONArray(purchaseDetails);
			goodsNameAndAmountArray = new JSONArray(goodsNameAndAmount);
			// 遍历每条明细记录
			outer:
			for (int i = 0; i < purchaseDetailsArray.length(); i++)
			{
				JSONObject detail = (JSONObject)purchaseDetailsArray.get(i);
				// 获取品名
				String name = detail.getString("goodsName");
				// 获取采购数量
				int amount = Integer.parseInt(detail.getString("amount"));
				// 遍历品名和已收货数量
				for (int j = 0; j < goodsNameAndAmountArray.length(); j++)
				{
					JSONObject goods = (JSONObject)goodsNameAndAmountArray.get(j);
					String goodsName = goods.getString("goodsName");
					int amountIn = Integer.parseInt(goods.getString("haveGotAmount"));
					// 如果品名相同，则比较采购数量和已收货数量
					if (name.equals(goodsName))
					{
						// 采购数量大于已收货数量
						if (amount > amountIn)
						{
							String notArrivedAmount = String.valueOf(amount - amountIn);
							// 将未到货数量放入这条采购明细记录中
							detail.put("notArrivedAmount", notArrivedAmount);
							// 将这条采购记录放入JSONArray数组中
							newArray.put(detail);
						}
						continue outer;
					}
				}
				// 如果没有寻找到相同的品名，则未到货数量为采购数量
				detail.put("notArrivedAmount", String.valueOf(amount));
				newArray.put(detail);
			}
			returnString = newArray.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "getPurchaseDetails";
	}
	
	/**
	 * 添加收货单信息
	 * @return 保存结果
	 */
	public String addReceipts()
	{
		ReceiptService receiptService = new ReceiptService();
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		TransactionManager transactionManager = new TransactionManager();
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(receipts);
		List<Map<String, String>> rs = jsonObject.getJSONArray("data");
		try {
			// 开启事务
			transactionManager.start();
			Connection conn = transactionManager.getConnection();
			// 获取最新收货单号
			String receiptNumber = NumberUtil.createReceiptNumber(rs.get(0).get("receiptDate"));
			// 遍历收货信息
			for (Map<String, String> m : rs)
			{
				// 添加收货信息
				receiptService.addReceipt(conn, m, receiptNumber);
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
		} finally {
			// 关闭事务
			transactionManager.close();
		}
		return "getSaveResult";
	}
}
