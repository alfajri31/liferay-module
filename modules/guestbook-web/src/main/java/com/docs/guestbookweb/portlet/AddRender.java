package com.docs.guestbookweb.portlet;

import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;
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
                "mvc.command.name=/add_entry"
        }
)
public class AddRender implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        return "/add_entry.jsp";
    }

}
