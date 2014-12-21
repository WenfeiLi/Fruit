package org.fruit.blueberry.dto;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AFei on 2014/7/26.
 */
public class RegisterForm {
    private String username;
    private String password;
    private String password2;
    private String nickname;
    private String birthday;
    private String email;
    private Map errors = new HashMap();

//    @NotEmpty(message = "用户名不能为空！")
    @Pattern(regexp = "[A-Za-z]{3,10}", message = "用户名必须为3-10位字母！")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    @NotEmpty(message = "密码不能为空！")
    @Pattern(regexp ="\\d{3,8}", message = "密码必须为3-8位数字！")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "确认密码不能为空！")
    //"两次密码不一致！"
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @NotEmpty(message = "昵称不能为空！")
    @Pattern(regexp = "[\u4e00-\u9fa5]+", message = "昵称必须是中文！")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //"日期格式不正确！"
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @NotEmpty(message = "邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map getErrors() {
        return errors;
    }

    public void setErrors(Map errors) {
        this.errors = errors;
    }

    public boolean validate() {
        boolean isValid = true;

        if (this.username == null || this.username.trim().equals("")) {
            isValid = false;
            errors.put("username", "用户名不能为空！");
        } else if (!this.username.matches("[A-Za-z]{3,10}")) {
            isValid = false;
            errors.put("username", "用户名必须为3-10位字母！");
        }

        if (this.password == null || this.password.trim().equals("")) {
            isValid = false;
            errors.put("password", "密码不能为空！");
        } else if (!this.password.matches("\\d{3,8}")) {
            isValid = false;
            errors.put("password", "密码必须为3-8位数字！");
        }

        if (this.password2 == null || this.password2.trim().equals("")) {
            isValid = false;
            errors.put("password2", "确认密码不能为空！");
        } else if (!this.password.equals(this.password2)) {
            isValid = false;
            errors.put("password2", "两次密码不一致！");
        }

        if (this.email == null || this.email.trim().equals("")) {
            isValid = false;
            errors.put("email", "邮箱不能为空！");
        } else if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
            isValid = false;
            errors.put("email", "邮箱格式不正确！");
        }

        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter dlc = new DateLocaleConverter();
                dlc.convert(this.birthday, "yyyy-MM-dd");
            } catch (Exception e) {
                isValid = false;
                errors.put("birthday", "日期格式不正确！");
            }
        }

        if (this.nickname == null || this.nickname.trim().equals("")) {
            isValid = false;
            errors.put("nickname", "昵称不能为空！");
        } else if (!this.nickname.matches("[\u4e00-\u9fa5]+")) {
            isValid = false;
            errors.put("nickname", "昵称必须是中文！");
        }

        return isValid;
    }
}
