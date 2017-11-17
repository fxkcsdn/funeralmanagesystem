package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.json.JSONArray;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.ReceiptService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String receiptNumber;// 收货单号
	private String purchaseNumber;// 采购编号
	private String receipts;// 收货信息
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

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

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
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
	 * 获取采购明细信息
	 * @return 采购明细信息
	 */
	public String getPurchaseDetails()
	{
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		ReceiptService receiptService = new ReceiptService();
		org.json.JSONArray purchaseDetailsArray = null;
		org.json.JSONArray goodsNameAndAmountArray = null;
		org.json.JSONArray newArray = new org.json.JSONArray();
		// 根据采购编号获取采购明细信息
		String purchaseDetails = purchaseDetailService.getPurchaseDetailsInReceipt(receiptNumber);
		// 根据采购编号计算每个商品已到货数量
		String goodsNameAndAmount = receiptService.getHaveGotGoods(purchaseNumber);
		try {
			purchaseDetailsArray = new org.json.JSONArray(purchaseDetails);
			goodsNameAndAmountArray = new org.json.JSONArray(goodsNameAndAmount);
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
	 * 修改收货单信息
	 * @return 保存结果
	 */
	public String updateReceipts()
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
			// 删除收货单信息
			receiptService.deleteReceiptTran(conn, receiptNumber);
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
