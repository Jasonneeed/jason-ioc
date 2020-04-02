package com.jason;

import com.jason.bean.Data;
import com.jason.bean.Handler;
import com.jason.bean.Param;
import com.jason.bean.View;
import com.jason.helper.BeanHelper;
import com.jason.helper.ConfigHelper;
import com.jason.helper.ControllerHelper;
import com.jason.utils.CodeUtil;
import com.jason.utils.JsonUtil;
import com.jason.utils.ReflectionUtil;
import com.jason.utils.StreamUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 15:06
 * @modify 2020/4/2 15:06
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        //注册JSP的servlet
        ServletRegistration jspRegistration = servletContext.getServletRegistration("jsp");
        jspRegistration.addMapping(ConfigHelper.getJspPath() + "*");
        //注册静态资源默认的servlet
        ServletRegistration defaultRegistration = servletContext.getServletRegistration("default");
        defaultRegistration.addMapping(ConfigHelper.getAssetPath() + "*");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod().toUpperCase();
        String path = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(method, path);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            String body = CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtils.isNotEmpty(body)) {
                String[] params = StringUtils.split(body, "&");
                if (ArrayUtils.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtils.split(param, "=");
                        if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                            String key = array[0];
                            String value = array[1];
                            paramMap.put(key, value);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            Method requestMethod = handler.getRequestMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, requestMethod, param);
            if (result instanceof View) {
                View view = (View) result;
                String reqPath = view.getPath();
                if (StringUtils.isNotEmpty(reqPath)) {
                    resp.sendRedirect(req.getContextPath() + reqPath);
                } else {
                    Map<String, Object> model = view.getModel();
                    for (Map.Entry<String, Object> entry : model.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(ConfigHelper.getJspPath() + reqPath).forward(req, resp);
                }
            } else if (result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter printWriter = resp.getWriter();
                    String json = JsonUtil.ToJson(model);
                    printWriter.write(json);
                    printWriter.flush();
                    printWriter.close();
                }

            }
        }
    }
}
