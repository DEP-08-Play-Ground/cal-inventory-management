package com.dulanga.cal.cal.service;

import com.dulanga.cal.cal.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO item);
    List<ItemDTO> getItem();

    void updateItem(ItemDTO itemDTO);
    void deleteItem(String itemCode);
}
