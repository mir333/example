/* ===========================================================================
 * IBA CZ Confidential
 *
 * (c) Copyright IBA CZ 2011 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.skolenie;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;

/**
 * @author Miroslav Ligas <miroslav.ligas@ibacz.eu>
 */
public class TestModelListener extends BaseModelListener<User> {
    private static Log _log = LogFactoryUtil.getLog(
                TestModelListener.class);

    @Override
    public void onAfterCreate(User model) throws ModelListenerException {
        super.onAfterCreate(model);

        _log.error("zmenaaa!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }
}
