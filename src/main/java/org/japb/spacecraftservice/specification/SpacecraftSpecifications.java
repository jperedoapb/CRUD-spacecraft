package org.japb.spacecraftservice.specification;

import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
public class SpacecraftSpecifications {

    public static Specification<Spacecraft> nameContainsIgnoreCase(String name) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(name)) {
                return criteriaBuilder.conjunction();
            }
            String namePattern = "%" + name.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), namePattern);
        };
    }
}
