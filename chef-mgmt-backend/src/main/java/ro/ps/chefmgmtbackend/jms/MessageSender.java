package ro.ps.chefmgmtbackend.jms;

import ro.ps.chefmgmtbackend.dto.mail.SendingStatus;

public interface MessageSender<Request> {

    SendingStatus sendMessage(Request request);
}
