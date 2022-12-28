<%@ include file="/init.jsp" %>


<%--<portlet:renderURL var="viewRender">--%>
<%--    <portlet:param name="mvcPortletName" value="/"/>--%>
<%--</portlet:renderURL>--%>

<%--<portlet:renderURL var="viewEntryUrl">--%>
<%--    <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>--%>
<%--</portlet:renderURL>--%>
<%--<aui:button-row>--%>
<%--    <aui:button onClick="<%= viewEntryUrl.toString() %>" value="View Entry">--%>
<%--    </aui:button>--%>
<%--</aui:button-row>--%>

<aui:form>
    <aui:model-context bean="${entry}" model="<%= com.docs.guestbook.model.Entry.class %>"/>
    <aui:input name="name"> </aui:input>
    <aui:input name="message"> </aui:input>
    <aui:input name="entryId" type="hidden"></aui:input>
    <a href="/">back to view </a>
<%--    direct to templat path / that mvcPortlet--%>
</aui:form>