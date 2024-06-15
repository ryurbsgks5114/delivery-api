package kr.sparta.deliveryapi.service;

import com.sun.nio.sctp.IllegalReceiveException;
import kr.sparta.deliveryapi.model.Delivery;
import kr.sparta.deliveryapi.model.enumtype.DeliveryStatus;
import kr.sparta.deliveryapi.repository.DeliveryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class AbstractDeliveryService<T, U extends JpaRepository<T, Long>> implements DeliveryService<T> {
    private final DeliveryRepository deliveryRepository;
    private final U repository;

    public AbstractDeliveryService(DeliveryRepository deliveryRepository, U repository) {
        this.deliveryRepository = deliveryRepository;
        this.repository = repository;
    }

    @Override
    public DeliveryStatus track(String trackingNumber) {
        return deliveryRepository.findById(trackingNumber)
                .map(Delivery::getStatus)
                .orElseThrow(IllegalReceiveException::new);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Delivery deliver(Long modelId) {
        final T model = repository.findById(modelId)
                .orElseThrow(IllegalArgumentException::new);

        final String trackingNo = generateTrackingNo(getModelInfo(model));
        final Delivery delivery = createDelivery(model, trackingNo, getModelInfo(model));

        deliveryRepository.save(delivery);

        return delivery;
    }

    public String generateTrackingNo(String description) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
                + String.valueOf(description.hashCode()).substring(0, 4);
    }

    public abstract String getModelInfo(T model);

    public abstract Delivery createDelivery(T model, String trackingNo, String info);

}
