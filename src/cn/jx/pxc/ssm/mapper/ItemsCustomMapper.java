/**
 * 
 */
package cn.jx.pxc.ssm.mapper;

import java.util.List;

import cn.jx.pxc.ssm.po.ItemsCustom;
import cn.jx.pxc.ssm.po.ItemsQueryVo;

/**
 *<p> Title:  ItemsCustom.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.ssm.mapper
 * @author    黄信胜
 * @date      2018年12月19日下午12:04:48
 * @version 版本号
 */
public interface ItemsCustomMapper {

	
	/**
	 * 商品查询
	 * @param itemsQueryVo 商品信息的查询条件的包装类
	 * @return 商品信息的扩展类
	 * @throws Exception
	 */
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**根据商品id删除商品
	 * @param itemsQueryVo 包装了商品id的集合
	 * @throws Exception
	 */
	void  deleteItemsById(ItemsQueryVo itemsQueryVo) throws Exception;
	
	
	/**
	 * 修改商品信息
	 * @param itemsQueryVo
	 * @throws Exception
	 */
	/*void updateItems(ItemsQueryVo itemsQueryVo) throws Exception;*/
	
	/**
	 * 添加商品信息
	 * @param itemsQueryVo
	 * @throws Exception
	 */
	void addItems(ItemsQueryVo itemsQueryVo) throws Exception;
	
}
