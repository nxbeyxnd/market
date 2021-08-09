package com.alexeyshekhonov.gbshop.models.entities;

import com.alexeyshekhonov.gbshop.models.entities.enums.PaymentStatuses;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "payment")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_status_id")
    private int paymentStatusId;

    @Column(name = "status")
    private Enum<PaymentStatuses> paymentStatusesEnum;
}
