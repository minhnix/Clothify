package com.clothify.payload.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeDTO {
    @NotNull
    private String name;
    @NotNull
    private String value;
}
