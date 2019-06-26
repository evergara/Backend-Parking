package co.com.ceiba.parking.parking.infrastructure.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGISTRY")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String vehicleType;
    @Column
    private String licensePlate;
    @Column
    private String displacement;
    @Column
    private Date dateArrival;
    @Column
    private Date dateDeparture;
    @Column
    private long value;

}
