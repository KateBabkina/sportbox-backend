package ru.vsu.cs.sportbox.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sportbox.Data.Dto.InventoryTypeFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Data.Repository.InventoryTypeRepository;
import ru.vsu.cs.sportbox.Responses.InventoryResponse;
import ru.vsu.cs.sportbox.Responses.InventoryTypeResponse;
import ru.vsu.cs.sportbox.Specification.InventoryTypeSpecification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryTypeServiceImpl implements ru.vsu.cs.sportbox.Service.InventoryTypeService {
    private InventoryTypeRepository inventoryTypeRepository;

    @Override
    @Transactional
    public List<InventoryType> filterInventoryType(InventoryTypeFilterDto inventoryTypeFilterDto) {
        List<InventoryType> result = inventoryTypeRepository.findAll(InventoryTypeSpecification.getInventoryTypeByNameAndPrice(inventoryTypeFilterDto));

        String startDate = inventoryTypeFilterDto.getStartDate();
        String endDate = inventoryTypeFilterDto.getEndDate();
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            Date sDate;
            Date eDate;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sDate = format.parse(startDate);
                eDate = format.parse(endDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            for (InventoryType inventoryType : new ArrayList<InventoryType>(result)) {
                int inventorySize = inventoryType.getInventories().size();
                for (Inventory inventory : inventoryType.getInventories()) {
                    for (Booking booking : inventory.getBookings()) {
                        if (sDate.equals(booking.getStartDate()) || eDate.equals(booking.getEndDate())
                                || sDate.before(booking.getStartDate()) && eDate.after(booking.getEndDate())
                                || sDate.after(booking.getStartDate()) && eDate.before(booking.getEndDate())) {
                            inventorySize--;
                            break;
                        }
                    }
                    if (inventorySize < 1) {
                        result.remove(inventoryType);
                    }
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<InventoryType> getAllInventoryTypes() {
        return inventoryTypeRepository.findAll();
    }

    @Override
    @Transactional
    public InventoryTypeResponse getInventoryTypeById(int id) {
        InventoryType inventoryType = inventoryTypeRepository.findById(id);
        if (inventoryType == null) {
            return new InventoryTypeResponse("Инвентаря с указанным идентификатором не существует.", false, null);
        }
        return new InventoryTypeResponse("Инвентарь был успешно найден.", true, inventoryType);
    }
}
