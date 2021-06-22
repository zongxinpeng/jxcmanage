package com.jxc.jxcmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer id;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userDesc;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
}
