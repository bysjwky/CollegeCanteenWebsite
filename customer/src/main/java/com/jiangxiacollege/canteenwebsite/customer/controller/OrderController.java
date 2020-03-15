package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.AddressService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderDetailService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;

import com.jiangxiacollege.canteenwebsite.customer.table.Orders;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import com.jiangxiacollege.canteenwebsite.customer.utils.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;



@Controller
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;
    @Resource
    private AddressService addressService;
    @Resource
    private OrderDetailService orderDetailService;

    @RequestMapping("/user_orderlist")
    public String orderList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        List<OrderVo> orderList = (List) orderService.orderInfo(userId).getData();
        model.addAttribute("orderList", orderList);
        return "user_orderlist";
    }

    @RequestMapping("/user_order")
    public String order(Model model,String orderId){
       List<OrderVo> list = (List<OrderVo>) orderService.orderDetail(orderId).getData();
        model.addAttribute("orderDetail",list);
       return "user_order" ;
    }

    @RequestMapping("/confirm_order")
    public  String address(Model model, HttpServletRequest request,String jsonPram){
        String[] sum = request.getParameterValues("sum");
        String[] checkProduct = request.getParameterValues("checkProduct");
        String[] allCarts = request.getParameterValues("sum1");
        List productIdList = new ArrayList();
        for(String checkCartId : checkProduct){
            for(String cart : allCarts){
                if(checkCartId.equals(cart.split(":")[0])){
                    productIdList.add(cart.split(",")[1]);
                }
            }
        }
        List<Product> listProduct = productService.getList(productIdList);
        List<OrderVo> orderVoList = new ArrayList<>();

        for(String cart2 : allCarts) {
            for (Product product : listProduct) {
                Long productId = Long.parseLong(cart2.split(",")[1]);
                if(productId.compareTo(product.getId())==0){
                    OrderVo orderVo = new OrderVo();
                    orderVo.setProductName(product.getName());
                    orderVo.setNumber(Integer.parseInt(cart2.split(":")[1].split(",")[0]));
                    orderVo.setPrice(product.getPrice());
                    orderVo.setSellerId(product.getSellerId());
                    orderVo.setProductId(product.getId());
                    orderVo.setCartId(Long.parseLong(cart2.split(":")[0]));
                    orderVoList.add(orderVo);
                }
            }
        }

        HttpSession session = request.getSession();
        String  userId =  session.getAttribute("userId").toString();
        List<Address> addressList = (List) addressService.addressInfo(userId).getData();
        model.addAttribute("addressInfo", addressList);
        model.addAttribute("orderVoList", orderVoList);
        return "confirm_order";
    }

    @RequestMapping("/commitOrder")
    @ResponseBody
    public ResponseBase commitOrder(HttpServletRequest request,@RequestBody Map<String, Object> map) {
        Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("userId")));
        Long addressId = Long.parseLong(String.valueOf(map.get("checkVal")));
        String remark = String.valueOf(map.get("bz"));
        List<Map> list = (List<Map>) map.get("list");
        Orders order =new Orders();
        List<OrderDetail> listOrderTail =  new ArrayList<>();
        List cartIdList = new ArrayList();
        int money = 0;

        Long id =Long.parseLong( SnowflakeIdWorker.getUUID());


        order.setCustomerId(userId);
        order.setId(id);
        order.setAddressId(addressId);
        order.setEdit(remark);


        for (Map proMap : list){
            order.setSellerId( Long.parseLong(String.valueOf(proMap.get("sellerId"))));
            int price = Integer.parseInt(String.valueOf(proMap.get("price")));
            int number = Integer.parseInt(String.valueOf(proMap.get("number")));
            money+=price*number;

            cartIdList.add(Long.parseLong(String.valueOf(proMap.get("cartId"))));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(id);
            orderDetail.setNumber(number);
            orderDetail.setProductId(Long.parseLong(String.valueOf(proMap.get("productId"))));
            listOrderTail.add(orderDetail);
        }
        order.setMoney(new BigDecimal(String.valueOf(money)));
        order.setTime( new Date());
        return  orderService.addOrder(order,listOrderTail,cartIdList);

    }

//取消订单
    @RequestMapping("/delOrder")
    @ResponseBody
    public  ResponseBase delOrder( @RequestBody String orderId){
        Map map = JsonUtils.jsonToMap(orderId);
        String Id = String.valueOf(map.get("orderId"));
         return orderService.delOrder(Id);
    }

    //订单送达
    @RequestMapping("/updateOrder")
    @ResponseBody
    public  ResponseBase updateOrder( @RequestBody String orderId){
        Map map = JsonUtils.jsonToMap(orderId);
        String Id = String.valueOf(map.get("orderId"));
        return orderService.updateOrder(Id);
    }
//订单评价显示
    @RequestMapping("/comment")
    public String comment(Model model,String orderId){
        List<OrderVo> list = (List<OrderVo>) orderService.orderDetail(orderId).getData();
        model.addAttribute("orderDetail",list);
        return "comment" ;
    }




}