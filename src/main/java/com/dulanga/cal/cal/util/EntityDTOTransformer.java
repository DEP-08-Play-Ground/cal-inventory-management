package com.dulanga.cal.cal.util;

import com.dulanga.cal.cal.dto.ItemDTO;
import com.dulanga.cal.cal.entity.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOTransformer {

    private final ModelMapper modelMapper;

    public EntityDTOTransformer(@Qualifier("modelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Item getItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    public ItemDTO getItemDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }


}
