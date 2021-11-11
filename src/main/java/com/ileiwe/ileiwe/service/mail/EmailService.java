package com.ileiwe.ileiwe.service.mail;

import com.sun.istack.NotNull;

public interface EmailService {
    MailResponse send(@NotNull Message message);
}
