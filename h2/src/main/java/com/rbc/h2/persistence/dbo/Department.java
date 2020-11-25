package com.rbc.h2.persistence.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rbc.h2.persistence.IH2DBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Department implements IH2DBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({ "department" })
//    private List<Employee> employeeList;
}
