<%@ include file="/init.jsp" %>

<portlet:actionURL name="addAction" var="saveActionUrl" >
</portlet:actionURL>

<h1>Create Entry Details</h1>

<aui:form name="fm" action="${saveActionUrl}">
  <aui:model-context bean="${entry}" model="<%= com.docs.guestbook.model.Entry.class %>"/>
  <aui:input name="name"> </aui:input>
  <aui:input name="message"> </aui:input>
  <aui:button-row>
    <aui:button cssClass="btn btn-primary" type="submit" />
  </aui:button-row>
</aui:form>
