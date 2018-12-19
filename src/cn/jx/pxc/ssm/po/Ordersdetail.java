/**
 * 
 */
package cn.jx.pxc.ssm.po;

import java.io.Serializable;

/**
 *<p>Title: Ordersdetail.java</p>
 *<p>Description: 订单明细</p>
 * @package  cn.jx.pxc.pojo
 * @author   黄信胜
 * @date     2018年12月1日下午8:24:58
 * @version  1.0
 */
@SuppressWarnings("all")
public class Ordersdetail implements Serializable{
	private Integer id;//订单明细id
	private Integer itemsNum;//商品数量
	private Integer ordersId;//订单id
	private Integer itemsId;//商品id
	private Items items;//关联的商品
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemsNum() {
		return itemsNum;
	}
	public void setItemsNum(Integer itemsNum) {
		this.itemsNum = itemsNum;
	}
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public Integer getItemsId() {
		return itemsId;
	}
	public void setItemsId(Integer itemsId) {
		this.itemsId = itemsId;
	}
}
