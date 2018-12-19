/**
 * 
 */
package cn.jx.pxc.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jx.pxc.ssm.mapper.ItemsCustomMapper;
import cn.jx.pxc.ssm.mapper.ItemsMapper;
import cn.jx.pxc.ssm.po.Items;
import cn.jx.pxc.ssm.po.ItemsCustom;
import cn.jx.pxc.ssm.po.ItemsQueryVo;
import cn.jx.pxc.ssm.service.ItemsService;

/**
 *<p> Title:  ItemsServiceImpl</p>
 *<p> Description:  ItemsService的实现接口</p>
 * @package   cn.jx.pxc.ssm.service.impl
 * @author    黄信胜
 * @date      2018年12月19日上午9:19:38
 * @version 版本号
 */
@SuppressWarnings("all")
public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapper itemsMapper;//相当于dao层接口
	
	@Autowired
	private ItemsCustomMapper itemsCustomMapper;//itemsCustomMapper
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.ssm.service.ItemsService#findItemsList(cn.jx.pxc.ssm.po.ItemsQueryVo)
	 */
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		
		return itemsCustomMapper.findItemsList(itemsQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.ssm.service.ItemsService#findItemsById(java.lang.Integer)
	 */
	@Override
	public ItemsCustom findItemsById(Integer id) {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//中间对商品信息进行业务处理
		//....
		//返回ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		//将items的内容拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.ssm.service.ItemsService#updateItems(java.lang.Integer, cn.jx.pxc.ssm.po.ItemsCustom)
	 */
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口时，对关键参数进行校验
		//校验id是否为空，如果为空，抛出异常
		
		//更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括大文本字段
		//updateByPrimaryKeyWithBLOBs,要求必须传入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
