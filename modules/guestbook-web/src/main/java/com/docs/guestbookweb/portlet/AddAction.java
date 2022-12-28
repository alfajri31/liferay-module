package com.docs.guestbookweb.portlet;

import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GuestbookwebPortletKeys.GUESTBOOKWEB,
                "mvc.command.name=addAction"
        }
)
public class AddAction implements MVCActionCommand {
    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String name = ParamUtil.get(actionRequest, "name", "");
        String message = ParamUtil.get(actionRequest, "message", "");
        _entryService.addEntrySpecified(name,message);
        return false;
    }

    @Reference(unbind = "-")
    protected void setEntryService(EntryLocalService entryService) {
        _entryService = entryService;
    }
    @Reference(unbind = "-")
    protected void setGuestbookService(GuestbookLocalService
                                               guestbookService) {
        _guestbookService = guestbookService;
    }
    private EntryLocalService _entryService;
    private GuestbookLocalService _guestbookService;
}
