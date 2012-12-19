/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.skolenie;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
public class ThemeDisplayChangeAction  extends Action {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
              Layout layout = themeDisplay.getLayout();
              if (themeDisplay.isSignedIn()) {
                  UnicodeProperties unicodeProperties = layout.getTypeSettingsProperties();
                  String col = unicodeProperties.getProperty("column-1");
                  unicodeProperties.setProperty("column-1", unicodeProperties.getProperty("column-2"));
                  unicodeProperties.setProperty("column-2", col);
                  layout.setTypeSettingsProperties(unicodeProperties);
              }
    }
}
