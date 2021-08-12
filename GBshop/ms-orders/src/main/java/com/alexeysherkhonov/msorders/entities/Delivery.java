package com.alexeysherkhonov.msorders.entities;

import com.alexeysherkhonov.msorders.entities.enums.DeliveryStatuses;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_status_id")
    private Long deliveryStatusID;

    @Column(name = "status")
    private Enum<DeliveryStatuses> deliveryStatusesEnum;
}
