/**
 * 
 */
package cn.jx.pxc.ssm.po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//商品id的数组
	private Integer[] items_id;
	
	//使用list批量提交修改商品
	private List<ItemsCustom> itemsList;
	
	//使用map提交修改
	private Map<Integer,ItemsCustom> itemsMap = new HashMap<Integer, ItemsCustom>();
	
	public Map<Integer, ItemsCustom> getItemsMap() {
		return itemsMap;
	}

	public void setItemsMap(Map<Integer, ItemsCustom> itemsMap) {
		this.itemsMap = itemsMap;
	}

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	public Integer[] getItems_id() {
		return items_id;
	}

	public void setItems_id(Integer[] items_id) {
		this.items_id = items_id;
	}

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
