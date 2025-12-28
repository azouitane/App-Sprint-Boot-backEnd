package com.helpdesktech.helpdesk.entity;

import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    private String model;

    @Column(nullable = false,length = 100)
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;


    @Column(nullable = false,unique = true, length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus status;

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiry;

    @Column(nullable = false,length = 500)
    private String specifications;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /* ================== Relations ================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "device",
            fetch = FetchType.LAZY
    )
    private List<Ticket> tickets;

    /* ================== Audit ================== */

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
