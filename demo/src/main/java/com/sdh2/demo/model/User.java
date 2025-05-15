package com.sdh2.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_data")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;


}
