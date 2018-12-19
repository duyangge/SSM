
package cn.jx.pxc.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import cn.jx.pxc.ssm.po.ItemsCustom;
import cn.jx.pxc.ssm.service.ItemsService;



/**
 *<p> Title:  ItemsController.java</p>
 *<p> Description:  Handler</p>
 * @package   cn.jx.pxc.ssm.controller
 * @author    黄信胜
 * @date      2018年12月18日下午5:47:54
 * @version 1.0
 */
//使用controller标识他是一个控制器
@Controller
@RequestMapping("/items") //窄化映射
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
		
	/**
	 * 使用String类型返回值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItems")
	public String queryItems(Model model) throws Exception{
		
		//调用service查找数据库，查询商品列表，
		List<ItemsCustom> itemsList = (List<ItemsCustom>) itemsService.findItemsList(null);
		model.addAttribute("itemList", itemsList);
		
		return "items/itemList";
	}
	
	/**
	 * 修改商品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST}) 
	public String editItems(Model model,@RequestParam(value="id",required=true) Integer items_id) throws Exception{
		//通过defaultValue可以设置默认值，如果id参数没有传入，将默认值与形参绑定
		
		//调用service查找数据库，查询商品列表，
		ItemsCustom itemsCustom  =  itemsService.findItemsById(items_id);
		
		//通过形参中model将model数据传到页面
		//相当于modelAndView，addObject方法
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "items/itemEdit";
	}
	
	/**修改商品信息后，提交修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateItemsSubmit")
	public String updateItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception{
		/*
		 * 注意:页面中input的name和controller的pojo形参中的属性名称一致，将页面中的数据绑定到pojo
		 * 自定义参数绑定：1.比如日期类型，转换成和pojo一样类型。
		 * 2.需要向处理器适配器中注入自定义参数绑定
		 * */
		itemsService.updateItems(id, itemsCustom);
		//转发商品查询列表	
		return "forward:queryItems.action" ;

	}
	
	
	
	
	
	
	/**商品查询
	 *  这个@RequestMapping实现对queryItemsAll方法和url进行映射，一个方法对应一个url
	 *  一般建议将url和方法写成一样的
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception{
		
				List<ItemsCustom> itemsList = itemsService.findItemsList(null);
				//返回ModelAndView
				ModelAndView modelAndView = new ModelAndView();
				
				//相当于request的setAttribute,在jsp页面中通过ItemsList获取数据
				modelAndView.addObject("itemList", itemsList);
				//指定视图
				//modelAndView.setViewName("/WEB-INF/jsp/items/itemList.jsp");
				
				//在springmvc.xml中的视图解析器中配置jsp的前缀和后缀后，简写为
				modelAndView.setViewName("items/itemList");
				
				
				return modelAndView;
		
	}*/
	
	//测试页面转发是否request是否可以共享
/*	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception{
				System.out.println(request.getAttribute("id"));
				List<ItemsCustom> itemsList = itemsService.findItemsList(null);
				//返回ModelAndView
				ModelAndView modelAndView = new ModelAndView();
				
				//相当于request的setAttribute,在jsp页面中通过ItemsList获取数据
				modelAndView.addObject("itemList", itemsList);
				//指定视图
				//modelAndView.setViewName("/WEB-INF/jsp/items/itemList.jsp");
				
				//在springmvc.xml中的视图解析器中配置jsp的前缀和后缀后，简写为
				modelAndView.setViewName("items/itemList");
				
				
				return modelAndView;
		
	}*/
	
	
	/**
	 * controller方法返回值
	 * 1.ModelAndView
	 *  定义ModelAndView ,将model和view分别进行设置
	 * 2.String 
	 * 表示逻辑视图名；
	 * 真正视图（jsp路径）=前缀+逻辑视图名+后缀
	 *   redirect:地址栏发生两次变化，request无法共享
	 *   forward：地址栏不变，request可以共享。
	 * 3.void
	 */
	
	
	
	
	
	
	/**
	 * 修改商品信息
	 * @return
	 * @throws Exception
	 */
	//限制http请求映射
/*	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST}) 
	public ModelAndView editItems() throws Exception{
		
		//调用service查找数据库，查询商品列表，
		//List<ItemsCustom> itemsList = (List<ItemsCustom>) itemsService.findItemsList(null);
		ItemsCustom itemsCustom  =  itemsService.findItemsById(1);
		
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//相当于request的setAttribute,在jsp页面中通过ItemsList获取数据
		modelAndView.addObject("itemsCustom", itemsCustom);
		
		//指定视图
		//modelAndView.setViewName("/WEB-INF/jsp/items/itemList.jsp");
		
		//在springmvc.xml中的视图解析器中配置jsp的前缀和后缀后，简写为
		modelAndView.setViewName("items/itemEdit");
		
		return modelAndView;
	}*/
	

	
	/**修改商品信息后，提交修改
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("/updateItemsSubmit")
	public ModelAndView updateItemsSubmit() throws Exception{
		
		//调用service查找数据库，查询商品列表，
		List<ItemsCustom> itemsList = (List<ItemsCustom>) itemsService.findItemsList(null);
		
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//相当于request的setAttribute,在jsp页面中通过ItemsList获取数据
		modelAndView.addObject("itemList", itemsList);
		
		//指定视图
		//modelAndView.setViewName("/WEB-INF/jsp/items/itemList.jsp");
		
		//在springmvc.xml中的视图解析器中配置jsp的前缀和后缀后，简写为
		modelAndView.setViewName("items/itemEdit");
		
		return modelAndView;
	}*/
	

	
	//测试页面转发
/*	@RequestMapping("/updateItemsSubmit")
	public String updateItemsSubmit(HttpServletRequest request) throws Exception{
		//重定向
		return "redirect:queryItems.action" ;
	}
	*/
	

}
