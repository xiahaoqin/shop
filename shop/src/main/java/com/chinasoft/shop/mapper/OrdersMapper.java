package com.chinasoft.shop.mapper;

import com.chinasoft.shop.pojo.Orders;

public interface OrdersMapper {
    int insert(Orders record);

    int insertSelective(Orders record);
}