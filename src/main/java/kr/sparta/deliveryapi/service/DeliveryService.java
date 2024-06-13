package kr.sparta.deliveryapi.service;

import kr.sparta.deliveryapi.model.enumtype.DeliveryStatus;

import java.util.List;

public interface DeliveryService<T> {

    public abstract DeliveryStatus track(String trackingNumber);

    public abstract List<T> getAll();

}
