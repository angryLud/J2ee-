package ticket.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jiayiwu on 17/1/31.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class CheckLoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //不过�? URL
        String [] notFilter = new String[]{"/page/login.html","/index.html","/","/page/userrig.html","/page/hotelrig.html","/page/hotelsearch.html"};
        List <String> list = Arrays.asList(notFilter);
        String url = httpServletRequest.getRequestURI();


        String param = httpServletRequest.getQueryString();
        String isconfirm = httpServletRequest.getParameter("isconfirm");



    if(!list.contains(url)){

           HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("user") != null || session.getAttribute("hotel")!=null || session.getAttribute("manager") != null){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }else {
                if (isconfirm!= null && isconfirm.equals("true")){
                    System.out.println("已经拦截!");
                    httpServletResponse.sendRedirect("/page/login.html?"+param);
                }else {
                    httpServletResponse.sendRedirect("/page/login.html");
                }

            }
        }else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }
}
