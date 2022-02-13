package com.example.kddgmn.model;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole")
    private Integer idRole;
    @Column(name = "rolename")
    private String roleName;

    public Role() {
    }

    public Role(Integer idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
