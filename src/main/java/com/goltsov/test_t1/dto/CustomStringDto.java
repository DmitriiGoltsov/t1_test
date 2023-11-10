package com.goltsov.test_t1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomStringDto {

    @NotBlank(message = "Your string cannot be blank")
    @Pattern(
            regexp = "^[a-zA-Z0-9_.-]*$",
            message = "Your string may contain only letters, digits, underscores, dashes and dots"
    )
    @Size(min = 1, max = 255, message = "Your string has to have from 1 to 255 characters")
    private String customString;

    public CustomStringDto(String customString) {
        this.customString = customString;
    }

    public CustomStringDto() {
    }

    public String getCustomString() {
        return customString;
    }

    public void setCustomString(String customString) {
        this.customString = customString;
    }
}
