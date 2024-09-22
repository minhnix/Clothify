package com.clothify.domain.constants;

import java.util.UUID;

public interface AppConstants {
  UUID SYSTEM = UUID.fromString("00000000-0000-0000-0000-000000000000");
  long MAIL_VERIFY_EXPIRED_TIME_MINUTES = 5;
}
