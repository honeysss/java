package com.wcl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor //有参构造方法
@NoArgsConstructor  //无参构造方法
@Component
public class User implements Serializable {
    private int userId;
    private String username;
    private String password;


}


