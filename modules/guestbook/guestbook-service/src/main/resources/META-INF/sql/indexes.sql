create index IX_EF6C0EEB on GBK_Entry (groupId, guestbookId);
create index IX_7ADC22DD on GBK_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_43D9BC9F on GBK_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BEBEDE72 on GBK_Guestbook (groupId);
create index IX_8D27992C on GBK_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4BDC7AAE on GBK_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F5591FB6 on GB_Entry (groupId, guestbookId);
create index IX_B5EF5128 on GB_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C1EA01AA on GB_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9294AD47 on GB_Guestbook (groupId);
create index IX_9314A9F7 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EDD4239 on GB_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_4D84930B on gbk_Entry (groupId, guestbookId);
create index IX_E91BB6FD on gbk_Entry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2F2FD8BF on gbk_Entry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_55DB3652 on gbk_Guestbook (groupId);
create index IX_EB401D4C on gbk_Guestbook (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_288786CE on gbk_Guestbook (uuid_[$COLUMN_LENGTH:75$], groupId);