<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/login/init.jsp" %>



<h1>Moja zmena</h1>


<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon()) {
			signedInAs = "<a href=\"" + HtmlUtil.escape(themeDisplay.getURLMyAccount().toString()) + "\">" + signedInAs + "</a>";
		}
		%>

		<%= LanguageUtil.format(pageContext, "you-are-signed-in-as-x", signedInAs, false) %>
	</c:when>
	<c:otherwise>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		String login = LoginUtil.getLogin(request, "login", company);
		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>

		<portlet:actionURL secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
			<portlet:param name="saveLastPath" value="0" />
			<portlet:param name="struts_action" value="/login/login" />
			<portlet:param name="doActionAfterLogin" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
		</portlet:actionURL>

		<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<c:choose>
				<c:when test='<%= SessionMessages.contains(request, "user_added") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "user_added");
					String userPassword = (String)SessionMessages.get(request, "user_added_password");
					%>

					<div class="portlet-msg-success">
						<c:choose>
							<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
								<%= LanguageUtil.get(pageContext, "thank-you-for-creating-an-account") %>

								<c:if test="<%= company.isStrangersVerify() %>">
									<%= LanguageUtil.format(pageContext, "your-email-verification-code-has-been-sent-to-x", userEmailAddress) %>
								</c:if>
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.format(pageContext, "thank-you-for-creating-an-account.-your-password-is-x", userPassword, false) %>
							</c:otherwise>
						</c:choose>

						<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
							<%= LanguageUtil.format(pageContext, "your-password-has-been-sent-to-x", userEmailAddress) %>
						</c:if>
					</div>
				</c:when>
				<c:when test='<%= SessionMessages.contains(request, "user_pending") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "user_pending");
					%>

					<div class="portlet-msg-success">
						<%= LanguageUtil.format(pageContext, "thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved", userEmailAddress) %>
					</div>
				</c:when>
			</c:choose>

			<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-login-because-the-maximum-number-of-users-has-been-reached" />
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
			<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
			<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserLockoutException.class %>" message="this-account-has-been-locked" />
			<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="authentication-failed" />

			<aui:fieldset>

				<%
				String loginLabel = null;

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					loginLabel = "email-address";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					loginLabel = "screen-name";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					loginLabel = "id";
				}
				%>

				<aui:input label="<%= loginLabel %>" name="login" showRequiredLabel="<%= false %>" type="text" value="<%= login %>">
					<aui:validator name="required" />
				</aui:input>

				<aui:input name="password" showRequiredLabel="<%= false %>" type="password" value="<%= password %>">
					<aui:validator name="required" />
				</aui:input>

				<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

				<c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
					<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" />
				</c:if>
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="sign-in" />
			</aui:button-row>
		</aui:form>

		<liferay-util:include page="/html/portlet/login/navigation.jsp" />

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />login);
			</aui:script>
		</c:if>

		<aui:script use="aui-base">
			var password = A.one('#<portlet:namespace />password');

			if (password) {
				password.on(
					'keypress',
					function(event) {
						Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
					}
				);
			}
		</aui:script>
	</c:otherwise>
</c:choose>