/**
 * 
 */
package cn.jx.pxc.ssm.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *<p>Title: Orders</p>
 *<p>Description: 订单实体类</p>
 * @package  cn.jx.pxc.pojo
 * @author   黄信胜
 * @date     2018年12月1日下午8:23:48
 * @version  版本号
 */
@SuppressWarnings("all")
public class Orders implements Serializable{
	private Integer id;//订单id
	private Integer user_id;//用户id，外键
	private Integer number;//订单中的商品数量
	private Date createtime;//订单的时间
	private String note;//订单的记录描述
	private User user;//关联的用户对象
	private List<Ordersdetail> ordersdetail;//关联的订单明细
	public List<Ordersdetail> getOrdersdetail() {
		return ordersdetail;
	}
	public void setOrderdetail(List<Ordersdetail> ordersdetail) {
		this.ordersdetail = ordersdetail;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", user_id=" + user_id + ", number=" + number + ", createtime=" + createtime
				+ ", note=" + note + "]";
	}
	
}
