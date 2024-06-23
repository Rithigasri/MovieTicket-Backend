//package com.example.movie.mov;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
//
//        if (isLoggedIn != null && isLoggedIn) {
//            return true;
//        } else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Login to access the application.");
//            return false;
//        }
//    }
//}
