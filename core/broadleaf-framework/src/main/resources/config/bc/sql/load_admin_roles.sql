--
-- Creates the out of box admin roles and associates them with out of box permissions
--

--
-- Create BLC ROLES (These roles are required for the admin)
--
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-1,'Admin Master Access','ROLE_ADMIN', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-2,'Merchandiser','ROLE_MERCHANDISE_MANAGER', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-3,'Promotion Manager','ROLE_PROMOTION_MANAGER', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-4,'CSR','ROLE_CUSTOMER_SERVICE_REP', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-5,'CMS Editor','ROLE_CONTENT_EDITOR', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-6,'CMS Approver','ROLE_CONTENT_APPROVER', CURRENT_TIMESTAMP);
INSERT INTO BLC_ADMIN_ROLE (ADMIN_ROLE_ID, DESCRIPTION, NAME, DATE_UPDATED) VALUES (-7,'CMS Designer','ROLE_CONTENT_DESIGNER', CURRENT_TIMESTAMP);

--
--
-- Mapping from Roles to permissions
--
-- Super user
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-101);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-103);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-105);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-107);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-109);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-111);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-115);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-119);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-121);
-- INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-127);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-131);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-141);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-161);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-181);


-- This permission grants access to "Permission" management to admin.    Since this can be harmful,
-- it is not provided by default
-- INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-151);


-- These are the permissions that grant access to SystemProperty. This is somewhat advanced and can be harmful,
-- so they are not enabled by default.
-- INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-110);
-- INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-1,-112);

-- Merchandiser
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-2,-101);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-2,-103);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-2,-105);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-2,-111);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-2,-131);

-- Promotions
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-3,-107);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-3,-131);

-- CSR
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-4,-119);

-- CMS Editor
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-5,-109);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-5,-111);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-5,-131);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-5,-161);

-- CMS Approver
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-6,-109);
INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF (ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES (-6,-111);