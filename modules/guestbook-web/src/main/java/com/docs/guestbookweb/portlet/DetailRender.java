package com.docs.guestbookweb.portlet;

import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GuestbookwebPortletKeys.GUESTBOOKWEB,
                "mvc.command.name=/detail"
        }
)
public class DetailRender implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        long entryId = ParamUtil.getLong(renderRequest, "entryId", 0);
        if(entryId > 0) {
            try {
                renderRequest.setAttribute("entry", _entryService.getEntry(entryId));
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        return "/detail.jsp";
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
