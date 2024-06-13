package kr.sparta.deliveryapi.service;

import kr.sparta.deliveryapi.model.Delivery;
import kr.sparta.deliveryapi.model.Food;
import kr.sparta.deliveryapi.model.enumtype.DeliveryStatus;
import kr.sparta.deliveryapi.model.enumtype.ItemType;
import kr.sparta.deliveryapi.repository.DeliveryRepository;
import kr.sparta.deliveryapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodDeliveryService extends AbstractDeliveryService<Food, FoodRepository> {

    public FoodDeliveryService(DeliveryRepository deliveryRepository, FoodRepository foodRepository) {
        super(deliveryRepository, foodRepository);
    }

    @Override
    public String getModelInfo(Food food) {
        return food.getName();
    }

    @Override
    public Delivery createDelivery(Food food, String trackingNo, String info) {
        return Delivery.builder()
                .trackingNumber(trackingNo)
                .itemType(ItemType.FOOD)
                .status(DeliveryStatus.SHIPPED)
                .itemId(food.getId())
                .name(info)
                .build();
    }

}
