package com.dulanga.cal.cal.api;

import com.dulanga.cal.cal.dto.ItemDTO;
import com.dulanga.cal.cal.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@Slf4j
@CrossOrigin()
public class ItemsController {

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO createItem (@RequestBody @Validated ItemDTO item, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        return itemService.createItem(item);
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getItem () {
        return itemService.getItem();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateItem (@RequestBody @Validated ItemDTO itemDTO, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        itemService.updateItem(itemDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem (@PathVariable String itemCode) {
        itemService.deleteItem(itemCode);
    }

}
