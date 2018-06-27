package jsp_servlet;

import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class __showuser extends  weblogic.servlet.jsp.JspBase  implements weblogic.servlet.jsp.StaleIndicator {

    private static void _releaseTags(javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag t) {
        while (t != null) {
            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, t);
            if(t instanceof javax.servlet.jsp.tagext.Tag) {
                javax.servlet.jsp.tagext.Tag tmp = (javax.servlet.jsp.tagext.Tag)t;
                t = ((javax.servlet.jsp.tagext.Tag) t).getParent();
                try {
                    tmp.release();
                } catch(java.lang.Exception ignore) {}
            }
            else {
                t = ((javax.servlet.jsp.tagext.SimpleTag)t).getParent();
            }
        }
    }

    public boolean _isStale(){
        boolean _stale = _staticIsStale((weblogic.servlet.jsp.StaleChecker) getServletConfig().getServletContext());
        return _stale;
    }

    public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
        if (sci.isResourceStale("/ShowUser.jsp", 1529248907782L ,"10.3.6.0","Europe/Berlin")) return true;
        return false;
    }

    private static boolean _WL_ENCODED_BYTES_OK = true;
    private static final java.lang.String _WL_ORIGINAL_ENCODING = "ISO-8859-1".intern();

    private static byte[] _getBytes(java.lang.String block){
        try {
            return block.getBytes(_WL_ORIGINAL_ENCODING);
        } catch (java.io.UnsupportedEncodingException u){
            _WL_ENCODED_BYTES_OK = false;
        }
        return null;
    }

    private final static java.lang.String  _wl_block0 ="\r\n<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n<title>Informazioni utente salvate su file</title>\r\n</head>\r\n<body>\r\n<h1>Dati utente</h1>\r\n\r\n<p>Ecco le informazioni relative all\'utente inserito:</p>\r\n\r\n";
    private final static byte[]  _wl_block0Bytes = _getBytes( _wl_block0 );

    private final static java.lang.String  _wl_block1 ="\r\n";
    private final static byte[]  _wl_block1Bytes = _getBytes( _wl_block1 );

    private final static java.lang.String  _wl_block2 ="\r\n\r\n<table cellspacing=\"5\" cellpadding=\"5\" border=\"1\">\r\n  <tr>\r\n    <td align=\"right\">Nome:</td>\r\n    <td>";
    private final static byte[]  _wl_block2Bytes = _getBytes( _wl_block2 );

    private final static java.lang.String  _wl_block3 ="</td>\r\n  </tr>\r\n  <tr>\r\n    <td align=\"right\">Cognome:</td>\r\n    <td>";
    private final static byte[]  _wl_block3Bytes = _getBytes( _wl_block3 );

    private final static java.lang.String  _wl_block4 ="</td>\r\n  </tr>\r\n  <tr>\r\n    <td align=\"right\">Et:</td>\r\n    <td>";
    private final static byte[]  _wl_block4Bytes = _getBytes( _wl_block4 );

    private final static java.lang.String  _wl_block5 ="</td>\r\n  </tr>\r\n</table>\r\n\r\n<p>Per inserire un nuovo utente, cliccare sul pulsante Back <br />\r\ndel browser oppure sul pulsante Nuovo Inserimento <br />\r\nin basso.</p>\r\n\r\n<form action=\"index.html\" method=\"post\">\r\n  <input type=\"submit\" value=\"Nuovo Inserimento\" />\r\n</form>\r\n\r\n</body>\r\n</html>";
    private final static byte[]  _wl_block5Bytes = _getBytes( _wl_block5 );

    static private weblogic.jsp.internal.jsp.JspFunctionMapper _jspx_fnmap = weblogic.jsp.internal.jsp.JspFunctionMapper.getInstance();

    public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) 
    throws javax.servlet.ServletException, java.io.IOException {

        javax.servlet.ServletConfig config = getServletConfig();
        javax.servlet.ServletContext application = config.getServletContext();
        javax.servlet.jsp.tagext.JspTag _activeTag = null;
        java.lang.Object page = this;
        javax.servlet.jsp.PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true , 8192 , true );
        response.setHeader("Content-Type", "text/html; charset=ISO-8859-1");
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter)out;
        _bw.setInitCharacterEncoding(_WL_ORIGINAL_ENCODING, _WL_ENCODED_BYTES_OK);
        javax.servlet.jsp.JspWriter _originalOut = out;
        javax.servlet.http.HttpSession session = request.getSession( true );
        try {;
            response.setContentType("text/html; charset=ISO-8859-1");
            _bw.write(_wl_block0Bytes, _wl_block0);
            business.User user= null;synchronized(session){
                user=(business.User)pageContext.getAttribute("user",javax.servlet.jsp.PageContext.SESSION_SCOPE);
                if(user==null){
                    user=new business.User();
                    pageContext.setAttribute("user",user,javax.servlet.jsp.PageContext.SESSION_SCOPE);
                    _bw.write(_wl_block1Bytes, _wl_block1);

                }
            }
            _bw.write(_wl_block2Bytes, _wl_block2);
            out.print(String.valueOf(user.getNome()));
            _bw.write(_wl_block3Bytes, _wl_block3);
            out.print(String.valueOf(user.getCognome()));
            _bw.write(_wl_block4Bytes, _wl_block4);
            out.print(String.valueOf(user.getEta()));
            _bw.write(_wl_block5Bytes, _wl_block5);
        } catch (java.lang.Throwable __ee){
            if(!(__ee instanceof javax.servlet.jsp.SkipPageException)) {
                while ((out != null) && (out != _originalOut)) out = pageContext.popBody(); 
                _releaseTags(pageContext, _activeTag);
                pageContext.handlePageException(__ee);
            }
        }
    }
}
