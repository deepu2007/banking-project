package com.easy.bankingApp.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + getParameters(request));



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public String getParameters(HttpServletRequest request){
        System.out.println("getParam");
        StringBuffer buffer = new StringBuffer();
        buffer.append("?");
        Enumeration<String> enumeration = request.getParameterNames();
       // String current= enumeration.nextElement();
        ArrayList<String> inputList = Collections.list(enumeration);
       // arrayList.stream().forEach(current->buffer.append(current));

        StringBuffer  output = new StringBuffer();
        ArrayList<String> outputList = (ArrayList<String>) inputList
                .stream()
                 .map(current ->current+ "&")
                .filter(current-> {
                    output.append("&");
                            if(current.contains("pass")){
                                output.append(current).append("=").append("****");
                                return true;
                                }else{
                                output.append(current).append("=").append( request.getParameter(current));
                            }
                            return false;
                        }

                )
                //.map(current->"****")
                .collect(Collectors.toList());

      /*  ArrayList<String> otherParams=(ArrayList<String>)arrayList
                .stream()
                .filter(current->(!current.contains("pass")))
                        .collect(Collectors.toList()); */

        if(output!=null){
            buffer.append(output);
        }
        return buffer.toString();
    }
}
