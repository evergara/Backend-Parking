package co.com.ceiba.parking.parking.infrastructure.entity;

import java.util.Date;
import javax.persistence.*;

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
    @TableGenerator(name = "nameRegister", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator  = "nameRegister")
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
