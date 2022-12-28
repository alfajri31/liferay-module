package com.docs.guestbookweb.portlet;

import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GuestbookwebPortletKeys.GUESTBOOKWEB,
                "mvc.command.name=deleteAction"
        }
)
public class DeleteAction implements MVCActionCommand {


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
        long entryId = ParamUtil.get(actionRequest,"entryId",0);
        try {
            if(_entryService.getEntry(entryId)!=null) {
                _entryService.deleteEntry(entryId);
            }
        } catch (PortalException e) {}
        return false;
    }
}
