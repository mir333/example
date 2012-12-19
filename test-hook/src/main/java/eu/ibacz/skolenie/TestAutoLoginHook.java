/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.skolenie;

import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
public class TestAutoLoginHook implements AutoLogin {
    public String[] login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AutoLoginException {
        String[] credentials = null;

        String param = httpServletRequest.getParameter("loginName");
        if (param != null) {

            try {
                User user = UserLocalServiceUtil.
                        getUserByScreenName(PortalUtil.getDefaultCompanyId(), param);
                credentials = new String[3];
                credentials[0] = String.valueOf(user.getUserId());
                credentials[1] = user.getPassword();
                credentials[2] = Boolean.FALSE.toString();
            } catch (Exception e) {
                throw new AutoLoginException(e);
            }
        }

        return credentials;
    }
}
