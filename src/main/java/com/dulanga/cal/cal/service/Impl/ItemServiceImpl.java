package com.dulanga.cal.cal.service.Impl;

import com.dulanga.cal.cal.dto.ItemDTO;
import com.dulanga.cal.cal.entity.Item;
import com.dulanga.cal.cal.repository.ItemRepository;
import com.dulanga.cal.cal.service.ItemService;
import com.dulanga.cal.cal.util.EntityDTOTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final EntityDTOTransformer transformer;

    public ItemServiceImpl(ItemRepository itemRepository, EntityDTOTransformer transformer) {
        this.itemRepository = itemRepository;
        this.transformer = transformer;
    }

    @Override
    public ItemDTO createItem(ItemDTO item) {
        Item savedItem = itemRepository.save(transformer.getItemEntity(item));
        if (savedItem == null) {
            log.error("Can Not Create the Item!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can Not Create the Item!");
        }
        return transformer.getItemDTO(savedItem);
    }

    @Override
    public List<ItemDTO> getItem() {
        List<Item> allItem = itemRepository.getAllItems();
        return allItem.stream().map(item -> transformer.getItemDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        Optional<Item> itemByItemCode = itemRepository.getItemByItemCode(itemDTO.getItemCode());
        if (!itemByItemCode.isPresent()) {
            log.error("Can Not Find the Item!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can Not Find the Item!");
        }
        Item item = itemByItemCode.get();
        item.setItemCategory(itemDTO.getItemCategory());
        item.setItemAmount(itemDTO.getItemAmount());
        item.setItemName(itemDTO.getItemName());
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(String itemCode) {
        Item itemByItemCode = itemRepository.findItemByItemCode(itemCode);
        if (itemByItemCode == null) {
            log.error("Invalid Item Code!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Item Code!");
        }
        itemRepository.deleteItemByItemCode(itemCode);
    }

    @Override
    public List<ItemDTO> getItemByCategory(String category) {
        return itemRepository.findItemByItemCategory(category).stream().map(item -> transformer.getItemDTO(item)).collect(Collectors.toList());
    }
}
