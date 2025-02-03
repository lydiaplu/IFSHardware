package com.intlfoodsource.IFSHardware.mapper;

import com.intlfoodsource.IFSHardware.model.Inventory;
import com.intlfoodsource.IFSHardware.request.InventoryRequest;
import com.intlfoodsource.IFSHardware.response.InventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface InventoryMapper {
    // model -> response
    InventoryResponse toInventoryResponse(Inventory inventory);

    // request -> model
    void toInventory(@MappingTarget Inventory inventory, InventoryRequest inventoryRequest);
}
