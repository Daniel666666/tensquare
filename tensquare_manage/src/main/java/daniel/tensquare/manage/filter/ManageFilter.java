package daniel.tensquare.manage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManageFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        if(request.getRequestURI().indexOf("/login")>0){
            return null;
        }
        String authorization = request.getHeader("Authorization");
        if(!authorization.isEmpty()){
            if(authorization.startsWith("daniel ")){
                String token = authorization.substring(7);
                Claims claims = jwtUtil.parseJWT(token);
                if(claims!=null&&claims.get("roles").equals("admin")){
                    requestContext.addZuulRequestHeader("Authorization",authorization);
                    return null;
                }
            }
        }

        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
