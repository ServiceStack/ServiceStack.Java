package net.servicestack.idea;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

/**
 * Helper messages for common add reference dialog errors.
 */
public class DialogErrorMessages {
    public static StringBuilder appendInvalidEnpoint(StringBuilder errorMessage,String addressUrl, Exception e) {
        errorMessage.append(e.getClass().getName()).append(" - Invalid ServiceStack endpoint provided - ").append(addressUrl);
        return errorMessage;
    }

    public static StringBuilder appendReadResponseError(StringBuilder errorMessage,String addressUrl, Exception e) {
        errorMessage.append(e.getClass().getName()).append(" - Failed to read response - ").append(addressUrl);
        Notification notification = new Notification(
                "ServiceStackIDEA",
                "Add ServiceStack Reference failed to read response",
                errorMessage.toString() + "\n" + e.getMessage(),
                NotificationType.ERROR);
        Notifications.Bus.notify(notification);
        return errorMessage;
    }
}
