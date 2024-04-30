package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // Item == clazz
        // Item == supItemClass
    }

    @Override
    public void validate(Object target, Errors errors) {

        Item item = (Item) target;

        // 검증 로직
        // itemName
        // ValidationUtils 는 아래에 있는 if 문의 촘더 편의한 버전 입니다.
//        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }

        // price - 유효범위: 1000 ~ 1000000
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{"1,000", "1,000,000"}, null);
        }

        // quantity - 유효범위: null X 그리고 최대 9999
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.rejectValue("quantity", "max", new Object[]{"9,999"}, null);
        }

        // 특정 필드가 아닌 복합 룰 검증
        // 유효범위: price * quantity >= 10000
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{"10,000", resultPrice}, null);
            }
        }
    }
}
