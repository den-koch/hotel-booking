package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "checkin_date", nullable = false)
    private Date checkinDate;

    @Column(name = "checkout_date", nullable = false)
    private Date checkoutDate;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "room_number", referencedColumnName = "room_number"),
            @JoinColumn(name = "room_building", referencedColumnName = "room_building")
    })
    private Room room;

}
