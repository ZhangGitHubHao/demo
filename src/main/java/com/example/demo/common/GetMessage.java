package com.example.demo.common;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

/**
 * @author 860118060
 */
public final class GetMessage {
    public static String getMessage(final String messageId) {
        return getMessage(messageId, null);
    }

    public static String getMessage(final String messageId, final String[] param) {
        return createMessage(messageId, param);
    }

    private static String createMessage(final String messageId, final String[] param) {

        if (messageId == null) {
            System.err.println("Code不对。messageIdID为空");
        }

        String rowMessage = GetProperties.getProperty(GetProperties.EXCEPTION + "." + messageId);

        if (rowMessage == null) {
            System.err.println("不存在指定的Code值。");
        }

        // メッセージ生成
        String message = null;

        if ((param == null) || (param.length == 0)) {
            message = rowMessage;
        } else {

            StringBuilder builder = new StringBuilder();
            Formatter formatter = null;
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < param.length; i++) {
                list.add(param[i]);
            }

            while (true) {
                try {
                    formatter = new Formatter(builder, Locale.SIMPLIFIED_CHINESE);
                    formatter.format(rowMessage, (Object[]) list.toArray(new String[0]));
                    message = formatter.toString();
                    formatter.close();
                    if (message != null) {

                        break;
                    }
                } catch (MissingFormatArgumentException e) {

                    list.add("null");
                }
            }
        }

        return message;
    }
}
