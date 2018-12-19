/**
 * 
 */
package cn.jx.pxc.ssm.po;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title: Items</p>
 *<p>Description: 商品类</p>
 * @package  cn.jx.pxc.pojo
 * @author   黄信胜
 * @date     2018年12月2日下午12:51:40
 * @version 版本号
 */
@SuppressWarnings("serial")
public class Items implements Serializable{
	private Integer id;//商品id
	private String name;//商品名称
	private Double price;//商品价格
	private String detail;//商品描述
	private String pic;//商品图片
	private Date createtime;//生产日期
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", detail=" + detail + ", pic=" + pic
				+ ", createtime=" + createtime + "]";
	}
	
}
