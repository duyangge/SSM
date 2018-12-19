/**
 * 
 */
package cn.jx.pxc.ssm.po;

/**
 *<p> Title:  ItemsQueryVo</p>
 *<p> Description:  Items的查询包装对象</p>
 * @package   cn.jx.pxc.ssm.po
 * @author    黄信胜
 * @date      2018年12月19日上午12:17:30
 * @version 1.0
 */
@SuppressWarnings("all")
public class ItemsQueryVo {
	
	//商品信息
	
	private Items items;
	
	//扩展的商品信息
	private ItemsCustom itemsCustom;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
	

}
