package com.liao.wxshop.service.impl;

import com.liao.wxshop.dataobject.ProductInfo;
import com.liao.wxshop.dto.CartDTO;
import com.liao.wxshop.enums.ProductStatusEnum;
import com.liao.wxshop.enums.ResultEnum;
import com.liao.wxshop.exception.ProductInfoException;
import com.liao.wxshop.repository.ProductInfoRepository;
import com.liao.wxshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品
 *
 * @author liao
 * @date 2019/2/20
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findProductInfoListByIds(List<String> productIds) {
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new ProductInfoException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new ProductInfoException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductInfoException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }
}
