package kr.sparta.deliveryapi.service;

import kr.sparta.deliveryapi.model.Delivery;
import kr.sparta.deliveryapi.model.Parcel;
import kr.sparta.deliveryapi.model.enumtype.DeliveryStatus;
import kr.sparta.deliveryapi.model.enumtype.ItemType;
import kr.sparta.deliveryapi.repository.DeliveryRepository;
import kr.sparta.deliveryapi.repository.ParcelRepository;
import org.springframework.stereotype.Service;

@Service
public class ParcelDeliveryService extends AbstractDeliveryService<Parcel, ParcelRepository> {

    public ParcelDeliveryService(DeliveryRepository deliveryRepository, ParcelRepository parcelRepository) {
        super(deliveryRepository, parcelRepository);
    }

    @Override
    public String getModelInfo(Parcel parcel) {
        return parcel.getDescription();
    }

    @Override
    public Delivery createDelivery(Parcel parcel, String trackingNo, String info) {
        return Delivery.builder()
                .trackingNumber(trackingNo)
                .itemType(ItemType.PARCEL)
                .status(DeliveryStatus.SHIPPED)
                .itemId(parcel.getId())
                .name(info)
                .build();
    }

}
