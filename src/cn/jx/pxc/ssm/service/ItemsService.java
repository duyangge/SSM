/**
 * 
 */
package cn.jx.pxc.ssm.service;

import java.util.List;

import cn.jx.pxc.ssm.po.ItemsCustom;
import cn.jx.pxc.ssm.po.ItemsQueryVo;

/**
 *<p> Title:  ItemsService.java</p>
 *<p> Description:  Items的service接口</p>
 * @package   cn.jx.pxc.ssm.service
 * @author    黄信胜
 * @date      2018年12月19日上午9:15:18
 * @version 1.0
 */
public interface ItemsService {

	/**
	 * 商品查询
	 * @param itemsQueryVo 商品信息的查询条件的包装类
	 * @return 商品信息的扩展类
	 * @throws Exception
	 */
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	

	/**
	 * 通过id查询商品信息
	 * @param id 查询的id
	 * @return
	 * @throws Exception
	 */
	ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 * 通过id，修改商品信息
	 * @param id 修改的id
	 * @param itemsCustom items的扩展类
	 * @throws Exception
	 */
	void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
	
	
	/**
	 * 根据商品id，删除商品
	 * @param itemsQueryVo items的包装类
	 * @throws Exception
	 */
	void deleteItemsById(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**添加商品
	 * @param itemsQueryVo items的包装类
	 * @throws Exception
	 */
	void addItems(ItemsCustom itemsCustom ) throws Exception;

}
