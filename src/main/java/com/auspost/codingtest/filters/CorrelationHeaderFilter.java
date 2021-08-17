package com.auspost.codingtest.filters;

import com.auspost.codingtest.util.RequestCorrelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * CorrelationHeaderFilter
 */
public class CorrelationHeaderFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationHeaderFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {

        LOGGER.info("Correlation Filter has been initialized");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String currentCorrId = httpServletRequest.getHeader(RequestCorrelation.CORRELATION_ID_HEADER);
        String requestUri = httpServletRequest.getRequestURI();
        if (currentCorrId == null) {
            currentCorrId = UUID.randomUUID().toString();
            LOGGER.info("No correlationId found in Header. Generated : " + currentCorrId + "\n requested for "+requestUri);
        } else {
            LOGGER.info("Found correlationId in Header : " + currentCorrId + " requested for "+requestUri);
        }

        RequestCorrelation.setId(currentCorrId);

        filterChain.doFilter(httpServletRequest, servletResponse);
        RequestCorrelation.logResponse(LOGGER, "SUCCESS");
    }


    @Override
    public void destroy() {
    }

}
