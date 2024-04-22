package com.james.api.common.component.interceptor;

import com.james.api.common.component.security.JwtProvider;
import com.james.api.user.model.User;
import com.james.api.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwt;
    private final UserRepository repo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwt.extractTokenFromheader(request);

        if (ObjectUtils.isEmpty(token)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String strId = jwt.getToken(token);
        Long id = Long.parseLong(strId);
        Optional<User> user = repo.findById(id);

        if (ObjectUtils.isEmpty(user)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }//

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
