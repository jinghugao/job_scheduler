package gaojh.job_scheduler.interceptor;

import com.google.gson.Gson;
import gao.commons.utility.StoreUserThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorConfig implements HandlerInterceptor {

    private final Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-FISH-TOKEN");
        StoreUserThreadLocal.storeUser("http://localhost:8081/fishsso/user/getUserInfo", token);
        return true;
    }
}
