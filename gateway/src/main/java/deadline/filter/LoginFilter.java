package deadline.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /*
         * 判断用户的请求中有没有 access-token 参数，请求有效，则放行
         * */
        //获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取 request
        HttpServletRequest request = ctx.getRequest();
        // 获取请求的参数
        String token = request.getParameter("access-token");
        // 判断是否存在
        if (StringUtils.isBlank(token)) {
            ctx.setSendZuulResponse(false);
            // 返回403
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        // 不存在，未登陆，则拦截
        return null;
    }
}
