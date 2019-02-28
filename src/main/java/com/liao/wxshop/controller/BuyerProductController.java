package com.liao.wxshop.controller;

import com.liao.wxshop.VO.ProductInfoVO;
import com.liao.wxshop.VO.ProductVO;
import com.liao.wxshop.VO.ResultBean;
import com.liao.wxshop.dataobject.ProductCategory;
import com.liao.wxshop.dataobject.ProductInfo;
import com.liao.wxshop.service.CategoryService;
import com.liao.wxshop.service.ProductService;
import com.liao.wxshop.util.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO..
 *
 * @author liao
 * @date 2019/2/20
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultBean list() {
        //1.所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.拼接数据
        List<ProductVO> data = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoList(productInfoVOList);
            data.add(productVO);
        }
        return ResultVOUtils.success(data);
    }
}
