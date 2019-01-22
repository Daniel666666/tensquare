package daniel.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authorization = request.getHeader("Authorization");
        if(authorization!=null){
            if(authorization.startsWith("daniel ")){
                String token = authorization.substring(7);
                if(!token.isEmpty()){
                    Claims claims = jwtUtil.parseJWT(token);
                    if(claims!=null){
                        if(claims.get("roles").equals("admin")){
                            request.setAttribute("admin_claims",claims);
                        }else if(claims.get("roles").equals("user")){
                            request.setAttribute("user_claims",claims);
                        }
                    }
                }
            }
        }
        return true;
    }
}
