<%@ include file="/init.jsp" %>

<portlet:actionURL name="updateAction" var="saveEmployeeURL" >
</portlet:actionURL>

<h1>Edit Entry Details</h1>

<aui:form name="fm" action="${saveEmployeeURL}">
    <aui:model-context bean="${entry}" model="<%= com.docs.guestbook.model.Entry.class %>"/>
    <aui:input name="name"> </aui:input>
    <aui:input name="message"> </aui:input>
    <aui:input name="entryId" type="hidden"></aui:input>
    <aui:button-row>
        <aui:button cssClass="btn btn-primary" type="submit" />
    </aui:button-row>
</aui:form>
