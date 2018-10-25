package com.deem.zkui.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpsGeneratorUtil {
    public static String generateHttpsString(HttpServletRequest req, String path) {
        String requestReferer = req.getHeader("referer");
        String url;
        String trimmed;

        if (requestReferer == null) {
            url = req.getServerName() + ":" + String.valueOf(req.getServerPort()) + path;
        } else if (requestReferer.lastIndexOf("proxy/") != -1) {
            trimmed = requestReferer.substring(0,
                    requestReferer.lastIndexOf("proxy/") + "proxy/".length());
            url = trimmed + path;
        } else if (requestReferer.lastIndexOf(req.getRequestURI()) != -1) {
            trimmed = requestReferer.substring(0, requestReferer.lastIndexOf(req.getRequestURI()));
            url = trimmed + path;
        } else {
            trimmed = requestReferer.substring(0, requestReferer.lastIndexOf("/"));
            url = trimmed + path;
        }

        return url;
    }
}
