package com.FuneralManage.Action;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.ReceiptService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Service.WarehouseBalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class AuditReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String receiptNumber;// 收货单号
	private String audit;// 审核人
	private String purchaseNumber;// 采购编号
	private String warehouseName;// 收货仓库
	private String details;// 收货明细信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getReceiptNumber() {
		return receiptNumber;
	}
	
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * 获取收货单主信息
	 * @return 收货单主信息
	 */
	public String getReceipt()
	{
		ReceiptService receiptService = new ReceiptService();
		// 获取收货单主信息
		List<Map<String, String>> receipt = receiptService.getReceipt(receiptNumber);
		returnString = JSONArray.fromObject(receipt).toString();
		return "getReceipt";
	}
	
	/**
	 * 获取收货单明细信息
	 * @return 收货单明细信息
	 */
	public String getReceiptDetails()
	{
		ReceiptService receiptService = new ReceiptService();
		// 获取收货单明细信息
		List<Map<String, String>> details = receiptService.getReceiptDetails(receiptNumber);
		returnString = JSONArray.fromObject(details).toString();
		return "getReceiptDetails";
	}
	
	/**
	 * 保存审核信息
	 * @return 保存审核信息的结果
	 */
	public String saveAuditInfo()
	{
		int i;
		ReceiptService receiptService = new ReceiptService();
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		TransactionManager transactionManager = new TransactionManager();
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 保存该采购单已到货的品名和数量
		List<Map<String, String>> newReceiptGoods = new ArrayList<Map<String, String>>();
		JSONObject object = JSONObject.fromObject(details);
		// 收货明细信息
		List<Map<String, String>> list = object.getJSONArray("data");
		// 获取采购明细信息
		List<Map<String, String>> purchaseDetails = purchaseDetailService.getPurchaseDetailsForList(purchaseNumber);
		// 获取该采购单每个商品的已到货数量
		List<Map<String, String>> receiptGoods = receiptService.getHaveGotGoodsForList(purchaseNumber);
		try {
			// 开启事务
			transactionManager.start();
			// 获取Connnection对象
			Connection conn = transactionManager.getConnection();
			// 保存审核信息
			receiptService.auditReceipt(conn, receiptNumber, audit);
			// 遍历明细信息
			for (Map<String, String> map : list)
			{
				// 商品种类
				String goodsType = map.get("goodsType");
				// 品名
				String goodsName = map.get("goodsName");
				// 单位
				String unit = map.get("unit");
				// 入库价
				String buyPrice = map.get("buyPrice");
				// 本次收货数量
				String amountIn = map.get("amountIn");
				// 更新入库价
				purchaseDetailService.updateBuyPrice(conn, purchaseNumber, goodsName, new BigDecimal(buyPrice));
				// 增加库存量
				warehouseBalanceService.increaseBalanceNumber(conn, warehouseName, goodsType, goodsName, unit, Integer.parseInt(amountIn));
				// 更新销售价
				warehouseBalanceService.updateSellPrice(conn, goodsName);
			}
			// 遍历已到货商品信息
			for (int j = 0; j < receiptGoods.size(); j++)
			{
				Map<String, String> m = receiptGoods.get(j);
				String goodsName = m.get("goodsName");
				int gotAmount = Integer.parseInt(m.get("haveGotAmount"));
				// 遍历收货单明细
				for (Map<String, String> map : list)
				{
					String goodsName1 = map.get("goodsName");
					int amountIn = Integer.parseInt(map.get("amountIn"));
					// 如果品名相同
					if (goodsName1.equals(goodsName))
					{
						receiptGoods.get(j).put("haveGotAmount", String.valueOf(gotAmount + amountIn));
					}
				}
			}
			// 遍历收货单明细
			outer:
			for (Map<String, String> map : list)
			{
				String goodsName1 = map.get("goodsName");
				int amountIn = Integer.parseInt(map.get("amountIn"));
				// 遍历已到货商品信息
				for (int j = 0; j < receiptGoods.size(); j++)
				{
					Map<String, String> m = receiptGoods.get(j);
					String goodsName = m.get("goodsName");
					int gotAmount = Integer.parseInt(m.get("haveGotAmount"));
					// 品名相同
					if (goodsName.equals(goodsName1)) continue outer;
				}
				// 如果该收货单上的商品还没有收到
				Map<String, String> m2 = new HashMap<String, String>();
				m2.put("goodsName", goodsName1);
				m2.put("haveGotAmount", String.valueOf(amountIn));
				receiptGoods.add(m2);
			}
			// 遍历采购明细信息
			outer1:
			for (i = 0; i < purchaseDetails.size(); i++)
			{
				Map<String, String> m = purchaseDetails.get(i);
				String goodsName = m.get("goodsName");
				int amount = Integer.parseInt(m.get("amount"));
				// 遍历已到货商品信息
				for (int k = 0; k < receiptGoods.size(); k++)
				{
					Map<String, String> m1 = receiptGoods.get(k);
					String goodsNameInReceipt = m1.get("goodsName");
					int gotAmount = Integer.parseInt(m1.get("haveGotAmount"));
					// 如果品名相同
					if (goodsNameInReceipt.equals(goodsName))
					{
						// 数量相同
						if (amount == gotAmount) continue outer1;
						// 数量不相同
						else break outer1;
					}
				}
				// 品名不相同
				break;
			}
			// 商品已全收齐，则结案采购单
			if (i == purchaseDetails.size()) purchaseMasterService.closePurchaseTran(conn, purchaseNumber);
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
