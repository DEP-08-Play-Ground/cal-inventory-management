package com.dulanga.cal.cal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO implements Serializable {

    @NotNull(message = "Item Code Can Not Be Empty!")
    private String itemCode;
    private String itemName;
    @NotNull(message = "Item Category Can Not Be Null!")
    private String itemCategory;
    private Integer itemAmount;

}
