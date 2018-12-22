/**
 * 
 */
package cn.jx.pxc.ssm.po;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.jx.pxc.ssm.controller.converter.validation.ValidationNameGroup;

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
	/*校验名称在1到30字符中间
	 *message时提示校验出错显示的信息
	 *group:此校验属于哪个分组，group可以定义多个分组
	*/
	@Size(min=1,max=30,message="{items.name.length.error}",groups= {ValidationNameGroup.class})
	private String name;//商品名称
	private Double price;//商品价格
	private String detail;//商品描述
	private String pic;//商品图片
	//非空校验
	@NotNull(message="{items.createtime.isNull}")
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
