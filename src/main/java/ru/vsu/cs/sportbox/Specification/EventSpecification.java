package ru.vsu.cs.sportbox.Specification;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import ru.vsu.cs.sportbox.Data.Dto.EventFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Event;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventSpecification {
    public static Specification<Event> getEventByInventoryAndDate(EventFilterDto eventFilterDto) {
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                String inventoryType = eventFilterDto.getInventoryType();
                String startDate = eventFilterDto.getStartDate();
                String endDate = eventFilterDto.getEndDate();

                Join<Event, InventoryType> inventoryTypeJoin = root.join("inventoryType", JoinType.LEFT);

                if (StringUtils.isNotBlank(inventoryType))
                {
                    predicates.add(criteriaBuilder.equal(inventoryTypeJoin.get("type"), inventoryType));
                }
                if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate))
                {
                    Date sDate;
                    Date eDate;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        sDate = format.parse(startDate);
                        eDate = format.parse(endDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("startDate"), sDate));
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("endDate"), eDate));

                }

                return predicates.size() <= 0 ? null : criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
