package com.docs.guestbookweb.portlet;

import com.docs.guestbookweb.constants.GuestbookwebPortletKeys;

import com.docs.guestbook.service.EntryLocalService;
import com.docs.guestbook.service.GuestbookLocalService;
import com.docs.guestbookweb.model.Entry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author igtraden2672
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Guestbookweb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GuestbookwebPortletKeys.GUESTBOOKWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GuestbookwebPortlet extends MVCPortlet {

    public void addEntry(ActionRequest request, ActionResponse response)
            throws PortalException {

        try {
            PortletPreferences prefs = request.getPreferences();
            String[] guestbookEntries = prefs.getValues("guestbook-entries",
                    new String[1]);
            ArrayList<String> entries = new ArrayList<String>();
            if (guestbookEntries[0] != null) {
                entries = new ArrayList<String>(Arrays.asList(prefs.getValues(
                        "guestbook-entries", new String[1])));
            }
            String userName = ParamUtil.getString(request, "name");
            String message = ParamUtil.getString(request, "message");
            String entry = userName + "^" + message;
            entries.add(entry);
            String[] array = entries.toArray(new String[entries.size()]);
            prefs.setValues("guestbook-entries", array);

            try {
                prefs.store();
            }
            catch (IOException ex) {
                Logger.getLogger(GuestbookwebPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            catch (ValidatorException ex) {
                Logger.getLogger(GuestbookwebPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        catch (ReadOnlyException ex) {
            Logger.getLogger(GuestbookwebPortlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

//        ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
//
//        String userName = ParamUtil.getString(request, "name");
//        String email = ParamUtil.getString(request, "email");
//        String message = ParamUtil.getString(request, "message");
//        long guestbookId = ParamUtil.getLong(request, "guestbookId");
//        long entryId = ParamUtil.getLong(request, "entryId");
//        if (entryId > 0) {
//            try {
//                _entryService.updateEntry(
//                        serviceContext.getUserId(), guestbookId, entryId, userName,
//                        email, message, serviceContext);
//                SessionMessages.add(request, "entryAdded");
//                response.setRenderParameter(
//                        "guestbookId", Long.toString(guestbookId));
//            }
//            catch (Exception e) {
//                System.out.println(e);
//                SessionErrors.add(request, e.getClass().getName());
//                PortalUtil.copyRequestParameters(request, response);
//                response.setRenderParameter(
//                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
//            }
//        }
//        else {
//            try {
//                _entryService.addEntry(
//                        serviceContext.getUserId(), guestbookId, userName, email,
//                        message, serviceContext);
//                SessionMessages.add(request, "entryAdded");
//                response.setRenderParameter(
//                        "guestbookId", Long.toString(guestbookId));
//            }
//            catch (Exception e) {
//                SessionErrors.add(request, e.getClass().getName());
//
//                PortalUtil.copyRequestParameters(request, response);
//                response.setRenderParameter(
//                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
//            }
//        }
    }

//	@Override
//	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
//		PortletPreferences prefs = renderRequest.getPreferences();
//		String[] guestbookEntries = prefs.getValues("guestbook-entries", new
//				String[1]);
//		if (guestbookEntries[0] != null) {
//			List<Entry> entries = parseEntries(guestbookEntries);
//			renderRequest.setAttribute("entries",entries);
//		}
//		super.render(renderRequest, renderResponse);
//	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		List<?> entries = _entryService.getAllEntry();
		renderRequest.setAttribute("entries",entries);
		super.doView(renderRequest, renderResponse);
	}

	private List<com.docs.guestbookweb.model.Entry> parseEntries(String[] guestbookEntries) {
		List<com.docs.guestbookweb.model.Entry> entries = new ArrayList<com.docs.guestbookweb.model.Entry>();
		for (String entry : guestbookEntries) {
			String[] parts = entry.split("\\^", 2);
			com.docs.guestbookweb.model.Entry gbEntry = new Entry(parts[0], parts[1]);
			entries.add(gbEntry);
		}
		return entries;
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