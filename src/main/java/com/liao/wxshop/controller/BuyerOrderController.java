package com.liao.wxshop.controller;

import com.liao.wxshop.vo.ResultBean;
import com.liao.wxshop.converter.OrderForm2OrderDTOConverter;
import com.liao.wxshop.dto.OrderDTO;
import com.liao.wxshop.enums.ResultEnum;
import com.liao.wxshop.exception.OrderException;
import com.liao.wxshop.form.OrderForm;
import com.liao.wxshop.service.OrderService;
import com.liao.wxshop.util.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家端订单
 *
 * @author liao
 * @date 2019/3/14
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */

    @PostMapping("/create")
    public ResultBean<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = new OrderForm2OrderDTOConverter().convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new OrderException(ResultEnum.CART_IS_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>(16);
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtils.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultBean<List<OrderDTO>> list(@RequestParam("openid") String buyerOpenid,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(buyerOpenid)) {
            log.error("【查询订单列表】参数不正确,openid为空");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, pageRequest);
        return ResultVOUtils.success(orderDTOPage.getContent());
    }

    //订单详情

    //取消订单
}
