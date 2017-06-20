package com.chinasoft.shop.mapper;

import com.chinasoft.shop.pojo.OrdersDetails;

public interface OrdersDetailsMapper {
    int insert(OrdersDetails record);

    int insertSelective(OrdersDetails record);

	void add(OrdersDetails od);
}