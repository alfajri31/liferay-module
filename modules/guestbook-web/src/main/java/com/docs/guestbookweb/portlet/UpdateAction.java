package com.docs.guestbookweb.portlet;

import com.docs.guestbook.model.Entry;
import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(immediate = true, property =
        {
                "javax.portlet.name=" + GuestbookwebPortletKeys.GUESTBOOKWEB,
                "mvc.command.name=updateAction"
        },
        service = MVCActionCommand.class)
public class UpdateAction extends BaseMVCActionCommand{

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


    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String name = ParamUtil.get(actionRequest, "name", "");
        String message = ParamUtil.get(actionRequest, "message", "");
        long entryId = ParamUtil.get(actionRequest, "entryId", 0);
        try {
            _entryService.updateEntrySpecified(name,message,entryId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        String name = ParamUtil.get(actionRequest, "name", "");
        String message = ParamUtil.get(actionRequest, "message", "");
        long entryId = ParamUtil.getLong(actionRequest, "entryId", 0);
        _entryService.updateEntrySpecified(name,message,entryId);
    }
}
