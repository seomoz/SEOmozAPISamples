package com.seomoz.api.constants;

/**
 * 
 * A constants class for Links Service
 * 
 * @author Radeep Solutions
 */
public class LinksConstants 
{
	public static final String LINKS_SCOPE_PAGE_TO_PAGE = "page_to_page";
	public static final String LINKS_SCOPE_PAGE_TO_SUBDOMAIN = "page_to_subdomain";
	public static final String LINKS_SCOPE_PAGE_TO_DOMAIN = "page_to_domain";
	public static final String LINKS_SCOPE_DOMAIN_TO_PAGE = "domain_to_page";
	public static final String LINKS_SCOPE_DOMAIN_TO_SUBDOMAIN = "domain_to_subdomain";
	public static final String LINKS_SCOPE_DOMAIN_TO_DOMAIN = "domain_to_domain";
	
	public static final String LINKS_SORT_PAGE_AUTHORITY = "page_authority";
	public static final String LINKS_SORT_DOMAIN_AUTHORITY = "domain_authority";
	public static final String LINKS_SORT_DOMAINS_LINKING_DOMAIN = "domains_linking_domain";
	public static final String LINKS_SORT_DOMAINS_LINKING_PAGE = "domains_linking_page";
	
	public static final String LINKS_FILTER_INTERNAL = "internal";
	public static final String LINKS_FILTER_EXTERNAL = "external";
	public static final String LINKS_FILTER_NOFOLLOW = "nofollow";
	public static final String LINKS_FILTER_FOLLOW = "follow";
	public static final String LINKS_FILTER_301 = "301";
	
	public static final long LINKS_COL_ALL = 0;
	public static final long LINKS_COL_TITLE = 1;
	public static final long LINKS_COL_URL = 4;
	public static final long LINKS_COL_SUBDOMAIN = 8;
	public static final long LINKS_COL_ROOT_DOMAIN = 16;
	public static final long LINKS_COL_EXTERNAL_LINKS = 32;
	public static final long LINKS_COL_SUBDMN_EXTERNAL_LINKS = 64;
	public static final long LINKS_COL_ROOTDMN_EXTERNAL_LINKS = 128;
	public static final long LINKS_COL_JUICE_PASSING_LINKS = 256;
	public static final long LINKS_COL_SUBDMN_LINKS = 512;
	public static final long LINKS_COL_ROOTDMN_LINKS = 1024;
	public static final long LINKS_COL_LINKS = 2048;
	public static final long LINKS_COL_SUBDMN_SUBDMN_LINKS = 4096;
	public static final long LINKS_COL_ROOTDMN_ROOTDMN_LINKS = 8192;
	public static final long LINKS_COL_MOZRANK = 16384;
	public static final long LINKS_COL_SUBDMN_MOZRANK = 32768;
	public static final long LINKS_COL_ROOTDMN_MOZRANK = 65536;
	public static final long LINKS_COL_MOZTRUST = 131072;
	public static final long LINKS_COL_SUBDMN_MOZTRUST = 262144;
	public static final long LINKS_COL_ROOTDMN_MOZTRUST = 524288;
	public static final long LINKS_COL_EXTERNAL_MOZRANK = 1048576;
	public static final long LINKS_COL_SUBDMN_EXTERNALDMN_JUICE = 2097152;
	public static final long LINKS_COL_ROOTDMN_EXTERNALDMN_JUICE = 4194304;
	public static final long LINKS_COL_SUBDMN_DOMAIN_JUICE = 8388608;
	public static final long LINKS_COL_ROOTDMN_DOMAIN_JUICE = 16777216;
	public static final long LINKS_COL_CANONICAL_URL = 268435456;
	public static final long LINKS_COL_HTTP_STATUS_CODE = 536870912;
	public static final long LINKS_COL_LINKS_TO_SUBDMN = 4294967296L;
	public static final long LINKS_COL_LINKS_TO_ROOTDMN = 8589934592L;
	public static final long LINKS_COL_ROOTDMN_LINKS_SUBDMN = 17179869184L;
	public static final long LINKS_COL_PAGE_AUTHORITY = 34359738368L;
	public static final long LINKS_COL_DOMAIN_AUTHORITY = 68719476736L;
}
