<%@ include file="/init.jsp" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<p>
	<b><liferay-ui:message key="employeecontroller.caption"/></b>
</p>

<portlet:renderURL var="addRenderVar">
    <portlet:param name="mvcRenderCommandName" value="/add_entry"></portlet:param>
</portlet:renderURL>
<aui:button-row>
    <aui:button onClick="<%= addRenderVar.toString() %>" value="Add Entry">
    </aui:button>
</aui:button-row>

<liferay-ui:search-container delta="10" emptyResultsMessage="no-entries-were-found" total="${totalEmployees}">
	<liferay-ui:search-container-results  results="${entries}"/>
	<liferay-ui:search-container-row
			className="com.docs.guestbook.model.Entry"
			keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">
		<liferay-ui:search-container-column-text property="name" />
		<liferay-ui:search-container-column-text property="message" />
		<liferay-ui:search-container-column-text name="Action">
			<portlet:renderURL var="editRender">
				<portlet:param name="mvcRenderCommandName" value="/create/edit"/>
				<portlet:param name="entryId" value="${entry.entryId}"/>
			</portlet:renderURL>
			<a href="${editRender}" > Edit </a>
			<a> | </a>
			<portlet:actionURL name="deleteAction" var="deleteActionUrl" >
				<portlet:param name="entryId" value="${entry.entryId}"/>
			</portlet:actionURL>
			<a href="${deleteActionUrl}" > Delete </a>
			<a> | </a>
			<portlet:renderURL var="detailVar">
				<portlet:param name="mvcRenderCommandName" value="/detail"/>
				<portlet:param name="entryId" value="${entry.entryId}"/>
			</portlet:renderURL>
			<a href="${detailVar}" > Detail </a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>