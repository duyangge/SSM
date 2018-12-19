package cn.jx.pxc.ssm.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>@Title User.java</p>
 *<p> @description 用户实体类，存储用户的临时信息</p>
 * @package cn.jx.pxc.po
 * @author 黄信胜
 * @date 2018年11月28日下午7:09:04
 * @version 版本号
 * PO(Persistence Object)持久化类
 * 7个规范
 * 1.公有类
 * 2.公有无参数
 * 3.私有属性
 * 4.公有的getter/setter
 * 5.实现java.io.Serializable接口(活化与钝化，序列化，session)
 * 6.不能用final修饰
 * 7.如果是基础类类型，要使用它的包装类
 * 为了将缓存数据取出执行反序列化操作，应为二级缓存数据存储介质多种多样，不一样在内存。
 */
@SuppressWarnings("all")
public class User implements Serializable{
	private Integer uid;
	private String username;//用户名
	private Date birthday;//密码
	private Character sex;//性别
	private String address;//邮箱
	private List<Orders> orders;//关联的订单
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", birthday=" + birthday + ", sex=" + sex + ", address="
				+ address + ", orders=" + orders + "]";
	}
	
}
