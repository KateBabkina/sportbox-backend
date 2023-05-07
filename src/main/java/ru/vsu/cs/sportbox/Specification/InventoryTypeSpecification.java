package ru.vsu.cs.sportbox.Specification;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import ru.vsu.cs.sportbox.Data.Dto.InventoryTypeFilterDto;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InventoryTypeSpecification {

    public static Specification<InventoryType> getInventoryTypeByNameAndPrice(InventoryTypeFilterDto inventoryTypeFilterDto) {
        return new Specification<InventoryType>() {
            @Override
            public Predicate toPredicate(Root<InventoryType> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                String inventoryType = inventoryTypeFilterDto.getInventoryType();
                int minPrice = inventoryTypeFilterDto.getMinPrice();
                int maxPrice = inventoryTypeFilterDto.getMaxPrice();

                if (StringUtils.isNotBlank(inventoryType))
                {
                    predicates.add(criteriaBuilder.equal(root.get("type"), inventoryType));
                    predicates.add(criteriaBuilder.isNotEmpty(root.get("inventories")));
                }
                if (minPrice != 0)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
                }
                if (maxPrice != 0)
                {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
                }

                return predicates.size() <= 0 ? null : criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
