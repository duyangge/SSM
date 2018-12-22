
package cn.jx.pxc.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.jx.pxc.ssm.controller.converter.validation.ValidationNameGroup;
import cn.jx.pxc.ssm.po.ItemsCustom;
import cn.jx.pxc.ssm.po.ItemsQueryVo;
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
@SuppressWarnings("all")
@Controller
@RequestMapping("/items") //窄化映射
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	
	/**
	 * 商品分类
	 * itemsTypes表示最终将方法返回值放在request中的key
	 * @return
	 */
	@ModelAttribute(value="itemsTypes")
	public Map<String,String> getItemsTypes() {
		Map<String,String> itemsTypes = new HashMap<String,String>();
		itemsTypes.put("101", "数码");
		itemsTypes.put("102", "运动");
		return itemsTypes;
	}
	
	
	/**
	 * 使用String类型返回值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItems")
	public String queryItems(Model model,ItemsQueryVo itemsQueryVo) throws Exception{
		
		//调用service查找数据库，查询商品列表，
		List<ItemsCustom> itemsList = (List<ItemsCustom>) itemsService.findItemsList(itemsQueryVo);
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
		//model.addAttribute("itemsCustom", itemsCustom);
		model.addAttribute("items", itemsCustom);//测试数据回显，避免默认pojo回显
		return "items/itemEdit";
	}
	
	/**修改商品信息后，提交修改
	 * @return
	 * @throws Exception
	 * 在需要校验的pojo前边添加@Validated,在需要校验的后边添加BindingResult bindingResult接口校验出错信息
	 * 注意：@Validated和BindingResult bindingResult时配对出现的，并且形参顺序时固定的（一前一后）
	 * value= {ValidationNameGroup.class}指定使用ValidationNameGroup分组的校验
	 * 1.数据回显：默认是默认对pojo数据进行回显。
	 *pojo数据传入controller方法后，springmvc。自动将pojo数据放到request域，key等于pojo类型（首字母小写）
	 * 2.数据回显：@ModelAttribute可以指定pojo回显到页面在request中的key
	 * 3.数据回显：  最简单使用model的addAttribute方法添加。
	 * MultipartFile items_pic :接收上传的文件
	 */
	@RequestMapping("/updateItemsSubmit")
	public String updateItemsSubmit(Model model, Integer id, @ModelAttribute(value="items") @Validated(value= {ValidationNameGroup.class}) ItemsCustom itemsCustom, 
			BindingResult bindingResult, MultipartFile items_pic ) throws Exception{

			List<ObjectError> allErrors ;
			//获取错误校验信息
			if (bindingResult.hasErrors()) {
				allErrors = bindingResult.getAllErrors();
				
				//输出错误信息
				/*for (ObjectError objectError : allErrors) {
					System.out.println(objectError.getDefaultMessage());
				}*/
				
				//将错误信息传递到页面中
				model.addAttribute("allErrors", allErrors);
				
				if(allErrors != null && allErrors.size()>0) 
					//model.addAttribute("items", "items");//3.直接使用model.addAttribute方法，数据回显
				return "items/itemEdit";
		}
		
		//存储图片的物理地址
		String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
		
		//得到图片的原始name
		String originalFileName = items_pic.getOriginalFilename();//
		
		//上传图片，判断上传的图片不能为空
		if (items_pic != null && originalFileName != null && originalFileName != "") {
			
			//新的图片名称
			String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			
			//新图片
			File newFile = new File(pic_path+newFileName);
			
			//将内存中的数据写入磁盘
			items_pic.transferTo(newFile);
			
			itemsCustom.setPic(newFileName);//将图片名称写入数据库中
		}
			
			
		/*
		 * 注意:页面中input的name和controller的pojo形参中的属性名称一致，将页面中的数据绑定到pojo
		 * 自定义参数绑定：1.比如日期类型，转换成和pojo一样类型。
		 * 2.需要向处理器适配器中注入自定义参数绑定
		 * */
		itemsService.updateItems(id, itemsCustom);
		
		//转发商品查询列表	
		return "forward:queryItems.action" ;

	}
	
	
	/**
	 * @param items_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems(ItemsQueryVo itemsQueryVo) throws Exception {
		itemsService.deleteItemsById(itemsQueryVo);
		return "forward:queryItems.action";
	}
	
	
	/**
	 * 批量修改商品信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/editItemsAll")
	public String editItemsAll(Model model,ItemsQueryVo itemsQueryVo) throws Exception {
		
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		model.addAttribute("itemsList", itemsList);
		
		return "items/editItemsAll";
	}
	
	/**批量提交修改后的商品信息
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
		for (ItemsCustom itemsCustom : itemsQueryVo.getItemsList()) {
			itemsService.updateItems(itemsCustom.getId(), itemsCustom);
		}
		return "forward:queryItems.action";
	}
	
	/**
	 * 添加商品
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addItems")
	public String addItems() throws Exception {
		//回到添加商品页面
		
		return "items/addItems";
	}
	
	
	/**
	 * 提交添加商品，保存
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addItemsSubmit")
	public String addItemsSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
		//添加商品的逻辑
		itemsService.addItems(itemsQueryVo);
		return "forward:queryItems.action";
		
	}
	//测试
/*	@RequestMapping("/queryItems")
	public String queryItems(Model model) throws Exception{
		
		//调用service查找数据库，查询商品列表，
		List<ItemsCustom> itemsList = (List<ItemsCustom>) itemsService.findItemsList(null);
		model.addAttribute("itemList", itemsList);
		
		return "items/itemList";
	}*/
	
	/*	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {
		ItemsQueryVo itemsQueryVo = new ItemsQueryVo();
		itemsQueryVo.setItems_id(items_id);
		itemsService.deleteItemsById(itemsQueryVo);
		
		return "forward:queryItems.action";
	}*/
	
	
	
	
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
