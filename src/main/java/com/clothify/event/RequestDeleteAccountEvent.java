package com.clothify.event;

import com.clothify.payload.dto.PreUserDTO;

public record RequestDeleteAccountEvent(PreUserDTO user) {}