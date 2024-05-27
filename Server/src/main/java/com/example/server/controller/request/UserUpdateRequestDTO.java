package com.example.server.controller.request;

import com.example.server.entity.UserEntity;
import com.example.server.entity.enums.RoleUser;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO{
    @NotNull
    String username;
    @NotNull
    String name;
    @NotNull
    String lastName;
    @NotNull
    String birthDate;
    @NotNull
    String address;
    @NotNull
    String phone;
    @NotNull
    String email;
    @NotNull
    String role;

    public RoleUser getRoleUser() {
        return RoleUser.valueOf(role);
    }

}
